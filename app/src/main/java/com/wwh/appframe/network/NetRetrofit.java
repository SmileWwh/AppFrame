package com.wwh.appframe.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wwh.appframe.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by wwh on 2016/11/29.
 */

public class NetRetrofit {

    final NetApi netApi;

    // @formatter:off
    final static Gson mGson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .serializeNulls()
            .create();
    // @formatter:on


    NetRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (NetApiFactory.isDebug) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }
        httpClient.connectTimeout(12, TimeUnit.SECONDS);
        OkHttpClient client = httpClient.build();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(mGson));
        Retrofit netRest = builder.build();
        builder.baseUrl(BuildConfig.BASE_URL);
        netApi = netRest.create(NetApi.class);
    }

    public NetApi getNetApi() {
        return netApi;
    }
}
