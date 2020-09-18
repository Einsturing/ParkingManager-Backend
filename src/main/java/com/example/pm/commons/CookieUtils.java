package com.example.pm.commons;

import org.springframework.stereotype.Component;

/**
 * Created by qhong on 2018/10/15 15:46
 **/
@Component
public class CookieUtils {

    public static boolean CookieConfirm(String cookie) {
        if(ConstantUtils.userLoginMap.get(cookie) == null) {
            return false;
        }
        else {
            return true;
        }
    }
}
