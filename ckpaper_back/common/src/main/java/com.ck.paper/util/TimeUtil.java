package com.ck.paper.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @param
 * @author ck
 * @description
 * @date 2020/3/19 16:03
 * @return
 */
public class TimeUtil {
    public static LocalDateTime getLocalDateAndTime(){
        Date date = new Date();
        Instant instant = date.toInstant();
        //系统默认的时区
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        return localDateTime;
    }
}
