package com.lishi.baijiaxing.wxapi.network;

import com.lishi.baijiaxing.pay.model.WxPrepay;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/14.
 */

public interface WxPayService {
    @FormUrlEncoded
    @POST("/index_api.php?m=WeChat&a=jsApiCall")
    Observable<WxPrepay> getPrepayInfo(@Field("uid") String uid, @Field("token") String token, @Field("type") String type
            , @Field("orderNumber") String orderNumber);
}
