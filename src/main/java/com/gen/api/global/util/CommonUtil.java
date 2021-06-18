package com.gen.api.global.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by jinwoo.
 * User: jwoh
 * Date: 2020-04-22
 * Time: 오후 2:42
 */
@Slf4j
public class CommonUtil {

    private CommonUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String checkDefaultNull(Object obj, String defaultVal) {
        String str = "";
        if (obj == null) {
            return defaultVal;
        } else {
            try {
                str = obj.toString();
            } catch (Exception ne) {
                return defaultVal;
            }
            return str;
        }
    }

    public static String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        log.debug("client_ip : {}",ip);

        return ip;
    }

    public static LocalDateTime stringToLocalDateTime(String date) {

        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
    }
    public static LocalDate stringToLocalDate(String date) {

        return  LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
    }

}
