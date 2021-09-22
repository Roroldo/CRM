package com.roroldo.crm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 密码加密工具类
 * @author 落霞不孤
 */
public class BCryptPasswordEncoderUtils {
    public static BCryptPasswordEncoder bCryptPasswordEncoder;

    static {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public static String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String s = encodePassword("root");
        System.out.println(s);
    }
}
