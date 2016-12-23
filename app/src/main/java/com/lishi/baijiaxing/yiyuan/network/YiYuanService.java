package com.lishi.baijiaxing.yiyuan.network;

import com.lishi.baijiaxing.yiyuan.model.HotList;
import com.lishi.baijiaxing.yiyuan.model.LotteryList;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/22.
 */

public interface YiYuanService {
    @FormUrlEncoded
    @POST("/index_api.php?m=oneGoods&a=commodityList")
    Observable<HotList> getHotList(@Field("uid") String uid, @Field("token") String token, @Field("type") String type, @Field("status") String status);

    @FormUrlEncoded
    @POST("/index_api.php?m=oneGoods&a=commodityList")
    Observable<LotteryList> getLotteryList(@Field("uid") String uid, @Field("token") String token, @Field("type") String type
            , @Field("status") String status, @Field("page") String page);

    @FormUrlEncoded
    @POST("/index_api.php?m=oneGoods&a=commodityList")
    Observable<HotList> getHotListNotLogin(@Field("status") String status);

    @FormUrlEncoded
    @POST("/index_api.php?m=oneGoods&a=commodityList")
    Observable<LotteryList> getLotteryListNotLogin(@Field("status") String status, @Field("page") String page);
}
