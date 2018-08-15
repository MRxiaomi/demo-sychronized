package com.example.demo.encode;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by liuyumeng on 2018/7/12.
 */
public class UrlEncode {
    public static void main(String[] args) throws UnsupportedEncodingException {


        String zw = "中文";
        System.out.println(URLEncoder.encode(zw,"utf-8"));
        System.out.println(URLEncoder.encode(URLEncoder.encode(zw,"utf-8"),"utf-8"));

        System.out.println(URLEncoder.encode(zw,"utf-8"));
    }
}
