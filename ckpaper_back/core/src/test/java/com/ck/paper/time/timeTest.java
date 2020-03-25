package com.ck.paper.time;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @param
 * @author ck
 * @description
 * @date 2020/3/19 15:59
 * @return
 */
public class timeTest {
    @Test
    public void dateToLocalDateTime(){
        Date date = new Date();
        Instant instant = date.toInstant();
        //系统默认的时区
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        System.out.println(localDateTime);
    }
}
