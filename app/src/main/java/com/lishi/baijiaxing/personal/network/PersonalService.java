package com.lishi.baijiaxing.personal.network;

import com.lishi.baijiaxing.bean.UserBean;
import com.lishi.baijiaxing.personal.model.LocalUserInfo;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @FormUrlEncoded
    @POST("/index_api.php?m=user")
    Observable<LocalUserInfo> getUserInfo(@Field("a") String showInfo, @Field("uid") String uid
            , @Field("token") String token, @Field("type") String type);
}
