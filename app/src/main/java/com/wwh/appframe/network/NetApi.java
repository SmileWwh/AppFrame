package com.wwh.appframe.network;

import com.wwh.appframe.model.UserDetailData;
import com.wwh.appframe.model.UserData;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wwh on 2016/11/29.
 */

public interface NetApi {

    //获取用户数据
    @GET("data/user/")
    Observable<UserData> getUserData(
            @Query("id") String id);

    //获取用户详情数据
    @GET("data/user/detail/")
    Observable<UserDetailData> getUserDetailData(
            @Query("id") String id);


    //查询用户数据
    @GET("data/search/")
    Observable<List<UserData>> searchUser(
            @Query("key") String key);
}
