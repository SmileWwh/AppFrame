package com.wwh.appframe.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast封装工具类
 * 使用需要在Application的onCreate方法中注册
 * <p>
 * Created by wwh on 2016/11/28.
 */

public class ToastUtil {
    
    private ToastUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }


    private static void check(Context context) {
        if (context == null) {
            throw new NullPointerException(
                    "ToastUtil：Context Not Null");
        }
    }


    public static void showShort(Context context, int resId) {
        check(context);
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }


    public static void showShort(Context context, String message) {
        check(context);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


    public static void showLong(Context context, int resId) {
        check(context);
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }


    public static void showLong(Context context, String message) {
        check(context);
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    //自定义Toast显示时间
    public static void show(Context context, int resId, int duration) {
        check(context);
        Toast.makeText(context, resId, duration).show();
    }


    public static void show(Context context, String message, int duration) {
        check(context);
        Toast.makeText(context, message, duration).show();
    }

}
