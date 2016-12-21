package com.lishi.baijiaxing.free.network;

import com.lishi.baijiaxing.free.model.FreeDetails;
import com.lishi.baijiaxing.free.model.FreeList;
import com.lishi.baijiaxing.free.model.FreeAdList;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrder;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 网络请求
 * Created by Administrator on 2016/9/29.
 */
public interface FreeService {
    @FormUrlEncoded
    @POST("/index_api.php?m=freeCollar&a=commodityList")
    Observable<FreeList> getFreeListNotLogin(@Field("status") String status);

    @FormUrlEncoded
    @POST("/index_api.php?m=freeCollar&a=commodityList")
    Observable<FreeList> getFreeListLogin(@Field("uid") String uid, @Field("token") String token, @Field("type") String type
            , @Field("status") String status);

    @FormUrlEncoded
    @POST("/index_api.php?m=goods&a=goodsDetails")
    Observable<FreeDetails> getFreeDetailsLogin(@Field("uid") String uid, @Field("token") String token, @Field("type") String type
            , @Field("zid") String zid, @Field("gid") String gid, @Field("form") String form);

    @FormUrlEncoded
    @POST("/index_api.php?m=goods&a=goodsDetails")
    Observable<FreeDetails> getFreeDetailsNotLogin(@Field("zid") String zid, @Field("gid") String gid, @Field("form") String form);

    @GET("/index_api.php?m=freeCollar&a=adList")
    Observable<FreeAdList> getFreeAdList();

    @FormUrlEncoded
    @POST("/index_api.php?m=order&a=inOrder")
    Observable<SubmitOrder> submitApply(@Field("uid") String uid, @Field("token") String token, @Field("type") String type
            , @Field("data") String commodityArray, @Field("aid") String aid);
}
