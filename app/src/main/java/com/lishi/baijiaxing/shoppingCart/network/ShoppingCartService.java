package com.lishi.baijiaxing.shoppingCart.network;

import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.bean.HomeRecommendBean;
import com.lishi.baijiaxing.shoppingCart.model.SCCommodityList;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;
import com.lishi.baijiaxing.shoppingCart.model.SCRecommendList;

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
public interface ShoppingCartService {
    //删除商品
    @FormUrlEncoded
    @POST("/index_api.php?m=cart&a=delGoods")
    Observable<SCOperation> removeCommodity(@Field("uid") String uid, @Field("token") String token, @Field("type") String type, @Field("cid") String cid);

    //获取购物车列表
    @FormUrlEncoded
    @POST("/index_api.php?m=cart&a=commodityList")
    Observable<SCCommodityList> getCommodityList(@Field("uid") String uid, @Field("token") String token, @Field("type") String type);

    //获取为你推荐列表
    @GET("/index_api.php?m=cart&a=recommendList")
    Observable<SCRecommendList> getRecommendList();

    //更改数量
    @FormUrlEncoded
    @POST("/index_api.php?m=cart&a=upGoodsNumber")
    Observable<SCOperation> changeCommodity(@Field("uid") String uid, @Field("token") String token, @Field("type") String type, @Field("cid") String cid, @Field("number") String number);

    //获取单个信息
    @FormUrlEncoded
    @POST("/index_api.php?m=cart&a=cartGoodsInfo")
    Observable<SCCommodityList> upCommodityInfo(@Field("uid") String uid, @Field("token") String token, @Field("type") String type, @Field("cid") String cid);

}
