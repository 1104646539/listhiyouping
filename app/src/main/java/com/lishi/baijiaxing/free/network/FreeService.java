package com.lishi.baijiaxing.free.network;

import com.lishi.baijiaxing.free.model.FreeBean;
import com.lishi.baijiaxing.home.model.HomeBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 网络请求
 * Created by Administrator on 2016/9/29.
 */
public interface FreeService {
    @GET("/")
    Observable<FreeBean> loadData(@Query("app") String app, @Query("phone") String phone, @Query("appkey") String appkey,
                                  @Query("sign") String sign, @Query("format") String format);

    @GET("/")
    Observable<FreeBean> changeData(@Query("app") String app, @Query("phone") String phone, @Query("appkey") String appkey,
                                    @Query("sign") String sign, @Query("format") String format);
}
