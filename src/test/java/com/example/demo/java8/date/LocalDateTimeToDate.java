package com.example.demo.java8.date;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by liuyumeng on 2018/7/26.
 */
public class LocalDateTimeToDate {
    public static void main(String[] args) {
        Date date = Date.from(LocalDateTime.of(2018,1,1,0,0)
                .atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(date);
    }
}
