package com.ck.paper.feign.config;

import com.alibaba.fastjson.JSONObject;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义的请求头处理类，处理服务发送时的请求头；
 * 将服务接收到的请求头中的uniqueId和token字段取出来，并设置到新的请求头里面去转发给下游服务
 * 比如A服务收到一个请求，请求头里面包含uniqueId和token字段，A处理时会使用Feign客户端调用B服务
 * 那么uniqueId和token这两个字段就会添加到请求头中一并发给B服务；
 *
 * @author Curise
 * @create 2018/11/20
 * @since 1.0.0
 */
@Configuration
public class FeignHeadConfiguration implements RequestInterceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attrs != null;
        HttpServletRequest request = attrs.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        //requestTemplate.header("requestedSessionId",request.getRequestedSessionId());
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String value = request.getHeader(name);
                if (!("host".equalsIgnoreCase(name))) {
                    requestTemplate.header(name,value);
                }
            }
        }
    }
}
