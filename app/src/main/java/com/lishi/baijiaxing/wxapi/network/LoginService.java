package com.lishi.baijiaxing.wxapi.network;

import com.lishi.baijiaxing.base.BaseRequestCallBack;
import com.lishi.baijiaxing.wxapi.model.Login;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * 登录
 * Created by Administrator on 2016/11/29.
 */

public interface LoginService {
    @FormUrlEncoded
    @POST("/index_api.php?m=user")
    Observable<Login> wxChatLogin(@Field("a") String userLogin,@Field("openid") String openid, @Field("nickname") String nickname, @Field("sex") String sex,
                                  @Field("language") String language, @Field("city") String city, @Field("province") String province,
                                  @Field("country") String country, @Field("headimgurl") String headimgurl, @Field("unionid") String unionid,
                                  @Field("type") String weChat);
    @FormUrlEncoded
    @POST("/index_api.php?m=user")
    Observable<Login> qqLogin(@Field("a")String userLogin,@Field("openid") String openid, @Field("nickname") String nickname, @Field("gender") String gender,
                              @Field("province") String province, @Field("city") String city, @Field("figureurl") String figureurl_qq_2,
                              @Field("type") String qq);

    @FormUrlEncoded
    @POST("/index_api.php?m=user")
    Observable<Login> localLogin(@Field("a")String userLogin,@Field("name") String name, @Field("paw") String paw, @Field("type") String type);
    
}
