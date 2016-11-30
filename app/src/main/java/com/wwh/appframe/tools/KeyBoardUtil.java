package com.wwh.appframe.tools;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 软键盘相关工具类
 * <p>
 * Created by wwh on 2016/11/29.
 */

public class KeyBoardUtil {

    private KeyBoardUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");

    }

    private static void check(Context context) {
        if (context == null) {
            throw new NullPointerException(
                    "KeyBoardUtil：Context Not Null");
        }
    }

    /**
     * 打开软键盘
     *
     * @param mEditText 输入框
     * @param context   上下文
     */
    public static void openKeybord(EditText mEditText, Context context) {
        check(context);
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 关闭软键盘
     *
     * @param mEditText 输入框
     * @param context   上下文
     */
    public static void closeKeybord(EditText mEditText, Context context) {
        check(context);
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }


}
