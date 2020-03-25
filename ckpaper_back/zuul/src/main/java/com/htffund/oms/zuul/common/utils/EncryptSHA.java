package com.htffund.oms.zuul.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 接口校验加密公用方法
 * @author Li chang xiao
 * @Date   2019年08月08日
 */
@Component
public class EncryptSHA {

	private static Logger logger = LoggerFactory.getLogger(EncryptSHA.class);
	
	/**
	 * 校验加密
	 * @author Li chang xiao
	 * @Date   2019年08月08日
	 */
	public static Map<String,Object> checkEncrypt(String url, String key, String token) {
		Map<String,Object> ret = new HashMap<>();
		logger.info("key======>" + key);
		logger.info("url======>" + url);
		logger.info("token======>" + token);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
		Date now = new Date();
		String formatterTime = formatter.format(now);
		logger.debug("nowTime=======>" + formatterTime);
		Date front = now;
		Date after = now;
		String[] arrTime = new String[21];
		arrTime[0] = formatterTime;
		for (int i = 1; i <= 10; i++) {
			front = addDate(front,0,0,0,0,-1,0,0);
			after = addDate(after,0,0,0,0,1,0,0);
			arrTime[i] = formatter.format(front);
			arrTime[i+10] = formatter.format(after);
		}
		for (int j = 0; j < arrTime.length; j++) {
			String str = getSha1(url + key + String.valueOf(arrTime[j]));
			logger.debug("arrTime[" + j + "]=======>" + arrTime[j] + "==============>" + str);
			if(token.equals(str)){
				ret.put("code", "0");
				ret.put("msg", "token校验成功");
				logger.info("token校验成功");
				return ret;
			}
		}
		ret.put("code", "10");
		ret.put("msg", "token校验失败");
		return ret;
	}
	
	
	
	public static Date addDate(Date date, int year, int month, int day, int hour, int minute, int second, int millisecond) {
        return addDate(date, year, month, day, 0, hour, minute, second, millisecond);
    }
	public static Date addDate(Date date, int year, int month, int day, int week, int hour, int minute, int second, int millisecond) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, year);//加减年数
        c.add(Calendar.MONTH, month);//加减月数
        c.add(Calendar.DATE, day);//加减天数
        c.add(Calendar.WEEK_OF_YEAR, week);//加减周数
        c.add(Calendar.HOUR, hour);//加减小时数
        c.add(Calendar.MINUTE, minute);//加减分钟数
        c.add(Calendar.SECOND, second);//加减秒
        c.add(Calendar.MILLISECOND, millisecond);//加减毫秒数

        return c.getTime();
    }
	
	/**
	 * SHA1加密
	 * 
	 * @author Li chang xiao
	 * @Date   2019年08月08日
	 */
	public static String getSha1(String str) {
		if (null == str || 0 == str.length()) {
			return null;
		}
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("UTF-8"));

			byte[] md = mdTemp.digest();
			int j = md.length;
			char[] buf = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
