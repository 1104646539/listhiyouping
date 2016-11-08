package com.lishi.baijiaxing.classify.network;

import com.lishi.baijiaxing.classify.model.OneClassify;
import com.lishi.baijiaxing.home.model.HomeBean;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/29.
 */
public interface ClassifySerVice {
    @GET("/")
    Observable<HomeBean> loadData(@Query("app") String app, @Query("phone") String phone, @Query("appkey") String appkey,
                                  @Query("sign") String sign, @Query("format") String format);
}
