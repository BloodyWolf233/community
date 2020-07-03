package com.github.bloodywolf.community.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author BloodyWolf233
 * @version 0.1
 * @date 2020/6/18 16:01
 */
public class CookieUitl {
    public static String getValue(HttpServletRequest request, String name) {
        if (request == null || name == null) {
            throw new IllegalArgumentException("参数为空!");
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) return cookie.getValue();
            }
        }
        return null;
    }
}
