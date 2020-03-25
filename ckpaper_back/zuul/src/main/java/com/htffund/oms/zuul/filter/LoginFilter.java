package com.htffund.oms.zuul.filter;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.htffund.oms.common.annotation.RedisUtil;
import com.htffund.oms.zuul.common.utils.JWTUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 登录鉴权Filter
 *
 * @author wjx
 * @date 2019/06/05
 */
public class LoginFilter extends ZuulFilter {

	private Logger logger = LoggerFactory.getLogger(LoginFilter.class);
	
    @Override
    public String filterType() {
        // 类型为 PRE_TYPE
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    private static final Set<String> SWAGGER_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/api/swagger-ui.html","/api/webjars/springfox-swagger-ui/springfox.css",
            		"/api/webjars/springfox-swagger-ui/swagger-ui.css","/api/webjars/springfox-swagger-ui/swagger-ui-bundle.js",
            		"/api/webjars/springfox-swagger-ui/swagger-ui-standalone-preset.js",
            		"/api/webjars/springfox-swagger-ui/springfox.js",
            		"/api/swagger-resources/configuration/ui",
            		"/api/swagger-resources/configuration/security",
            		"/api/swagger-resources",
            		"/api/v2/api-docs",
            		"/api/","/api/webjars/springfox-swagger-ui/fonts/titillium-web-v6-latin-700.woff2",
            		"/api/webjars/springfox-swagger-ui/fonts/open-sans-v15-latin-regular.woff2",
            		"/api/webjars/springfox-swagger-ui/fonts/source-code-pro-v7-latin-300.woff2",
            		"/api/webjars/springfox-swagger-ui/fonts/open-sans-v15-latin-700.woff2","/api/csrf")));
    
    @Override
    public boolean shouldFilter() {
        
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        logger.info("url--------->"+request.getRequestURI());
        //忽略大小写，返回true则拦截，进入run方法
        if ("/auth/authservice/baseinfo/logo".equals(request.getRequestURI())) {
            return false;
        }
        if ("/auth/authservice/login".equals(request.getRequestURI())) {
            return false;
        }
        if ("/auth/authservice/logout".equals(request.getRequestURI())) {
            return false;
        }
        if ("/auth/authservice/error".equals(request.getRequestURI())) {
            return false;
        }
        if ("/api/auth/token".equals(request.getRequestURI())) {
            return false;
        }
        boolean checkPath = SWAGGER_PATHS.contains(request.getRequestURI());
        if (checkPath) {
            return false;
        }
        return true;
    }


    private static final Set<String> CHECK_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList(
                    "/api/sales-agent/arg-modify"
                    ,"/api/sales-agent/ref"
                    ,"/api/sales-agent/test-notify"
                    ,"/api/seats/anti-rental"
                    ,"/api/seats/feeRate"
                    ,"/api/seats/rental"
                    ,"/api/seats/permissionRate"
                    ,"/api/fundInit/synchronization"
                    ,"/api/fundInit/redis")));

    @Override
    public Object run() throws ZuulException {
        //JWT 校验
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        HttpServletResponse response = requestContext.getResponse();
        //token对象
        String token = request.getHeader("X-Auth-Token");

        //如果token字符串为空
        if (token == null || "".equals(token)) {
            token = request.getParameter("X-Auth-Token");
        }
        logger.info("token========>" + token);
        boolean checkPath = CHECK_PATHS.contains(request.getRequestURI());
        if(checkPath) {
        	Map<String, Object> repsone = new HashMap<>();
        	boolean flag = true;
        	/*从缓存里获取系统初始化配置参数*/
        	String adminParam = RedisUtil.getString("OMS_API_ADMINPARAM");
        	JSONArray arr = JSONArray.parseArray(adminParam);
        	for (int i = 0; i < arr.size(); i++) {
        		JSONObject obj = (JSONObject) arr.get(i);
        		String paramValue = (String) obj.get("PARAM_VALUE");
        		/*以下校验支持缓存里token失效、不同部门token校验*/
        		if(paramValue.equals(request.getRequestURI())) {
        			String paramName = (String) obj.get("PARAM_NAME");
        			String chachToken = RedisUtil.getString("OMS_API_TOKEN_"+paramName);
        			if(StringUtils.isEmpty(chachToken)) {
        				repsone.put("returnCode", 20);
    	                repsone.put("returnMsg", "token已经失效");
    	                String ret = JSONObject.toJSONString(repsone);
    	                logger.info("RESPONSE:" + ret);
    	                resultMsg((HttpServletResponse)response, ret, 401, "");
    	                requestContext.setSendZuulResponse(false);
        			}
        			if(!chachToken.equals(token)) {
        				repsone.put("returnCode", 401);
        				repsone.put("returnMsg", "token校验失败");
    	                String ret = JSONObject.toJSONString(repsone);
    	                logger.info("RESPONSE:" + ret);
    	                resultMsg((HttpServletResponse)response, ret, 401, "");
    	                requestContext.setSendZuulResponse(false);
        			}
        			flag = false;
        		}
    		}
        	if(flag) {
        		repsone.put("returnCode", 401);
				repsone.put("returnMsg", "token校验失败");
                String ret = JSONObject.toJSONString(repsone);
                logger.info("RESPONSE:" + ret);
                resultMsg((HttpServletResponse)response, ret, 401, "");
                requestContext.setSendZuulResponse(false);
        	}
        }else {
        	 //登录校验逻辑
            if (token == null || "".equals(token)) {
                //设置为false则不往下走(不调用api接口)
                requestContext.setSendZuulResponse(false);
                //响应一个状态码：401
                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            } else {
                // 解密获得loginName，用于和数据库进行对比
                String loginName = JWTUtil.getLoginName(token);
                if (loginName == null || !JWTUtil.verify(token, loginName)) {
                    //设置为false则不往下走(不调用api接口)
                    requestContext.setSendZuulResponse(false);
                    //响应一个状态码：401
                    requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
                }
            }
        }
        
        return null;
    }
    
    /**
     * 返回信息
     * @auther Li chang xiao
     * @date   2019年08月08日
     * @param response
     * @param state    状态
     * @param returnStr
     */
    @SuppressWarnings("deprecation")
	public static void resultMsg(HttpServletResponse response,String returnStr,int state,String stateMsg){
        response.setStatus(state, stateMsg);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.write(returnStr);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            try {
                OutputStream output=response.getOutputStream();
                output.write(returnStr.getBytes("UTF-8"));
                output.flush();
                output.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    
}
