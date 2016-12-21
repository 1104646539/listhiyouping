package com.lishi.baijiaxing.details.network;

import com.lishi.baijiaxing.details.model.CommodityDetails;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/25.
 */

public interface DetailsService {
    @GET("/index_api.php?m=goods")
    Observable<CommodityDetails> getCommodityDetails(@Query("a") String type, @Query("gid") String gid);

    //添加商品
    @FormUrlEncoded
    @POST("/index_api.php?m=cart&a=inCart")
    Observable<SCOperation> addCommodity(@Field("uid") String uid, @Field("token") String token, @Field("type") String type
            , @Field("gid") String gid, @Field("number") String number);
}
