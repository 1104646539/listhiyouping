package com.lishi.baijiaxing.personal.network;

import com.lishi.baijiaxing.bean.UserBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 网络请求
 * Created by Administrator on 2016/9/29.
 */
public interface PersonalService {
    @GET("/")
    Observable<UserBean> loadData(@Query("app") String app, @Query("phone") String phone, @Query("appkey") String appkey,
                                  @Query("sign") String sign, @Query("format") String format);

    @GET("/")
    Observable<UserBean> login(@Query("app") String app, @Query("phone") String phone, @Query("appkey") String appkey,
                                   @Query("sign") String sign, @Query("format") String format);

    @GET("/")
    Observable<UserBean> isLogin(@Query("app") String app, @Query("phone") String phone, @Query("appkey") String appkey,
                                     @Query("sign") String sign, @Query("format") String format);

}
