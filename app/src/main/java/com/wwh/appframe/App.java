package com.wwh.appframe;

import android.app.Application;
import android.content.Context;

import com.wwh.appframe.tools.ToastUtil;

/**
 * Created by wwh on 2016/11/28.
 */

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return getInstance().getApplicationContext();
    }
}
