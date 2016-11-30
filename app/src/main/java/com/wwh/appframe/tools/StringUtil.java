package com.wwh.appframe.tools;

/**
 * Created by wwh on 2016/11/29.
 */

public class StringUtil {

    private StringUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    //验证字符串是否有内容
    public static boolean verify(String s){
        return s != null && !s.isEmpty();
    }
}
