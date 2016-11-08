package com.lishi.baijiaxing.home.network;

import com.lishi.baijiaxing.bean.HomeRecommendBean;
import com.lishi.baijiaxing.home.model.HomeBean;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 网络请求
 * Created by Administrator on 2016/9/29.
 */
public interface HomeService {
    @GET("/")
    Observable<HomeBean> loadData(@Query("app") String app, @Query("phone") String phone, @Query("appkey") String appkey,
                                  @Query("sign") String sign, @Query("format") String format);

    @GET("/")
    Observable<HomeBean> pullToRefresh(@Query("app") String app, @Query("phone") String phone, @Query("appkey") String appkey,
                                                           @Query("sign") String sign, @Query("format") String format);
}
