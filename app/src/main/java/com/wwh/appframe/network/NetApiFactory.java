package com.wwh.appframe.network;

/**
 * Created by wwh on 2016/11/29.
 */

public class NetApiFactory {

    public static boolean isDebug = true;
    public static final int PAGE_SIZE = 10;

    protected static final Object monitor = new Object();
    static NetApi sNetApiSingleton = null;

    public static NetApi getNetApiSingleton() {
        synchronized (monitor) {
            if (sNetApiSingleton == null) {
                sNetApiSingleton = new NetRetrofit().getNetApi();
            }
            return sNetApiSingleton;
        }
    }
}
