package com.lishi.baijiaxing.shoppingCart.network;

import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.bean.HomeRecommendBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 网络请求
 * Created by Administrator on 2016/9/29.
 */
public interface ShoppingCartService {
    @GET("/")
    Observable<CommodityBean> loadData(@Query("app") String app, @Query("phone") String phone, @Query("appkey") String appkey,
                                       @Query("sign") String sign, @Query("format") String format);

    @GET("/")
    Observable<CommodityBean> changeStore(@Query("app") String app, @Query("phone") String phone, @Query("appkey") String appkey,
                                         @Query("sign") String sign, @Query("format") String format);

    @GET("/")
    Observable<CommodityBean> addStore(@Query("app") String app, @Query("phone") String phone, @Query("appkey") String appkey,
                                      @Query("sign") String sign, @Query("format") String format);

    @GET("/")
    Observable<CommodityBean> removeStore(@Query("app") String app, @Query("phone") String phone, @Query("appkey") String appkey,
                                         @Query("sign") String sign, @Query("format") String format);

    @GET("/")
    Observable<HomeRecommendBean> pullLoad(@Query("app") String app, @Query("phone") String phone, @Query("appkey") String appkey,
                                                      @Query("sign") String sign, @Query("format") String format);
}
