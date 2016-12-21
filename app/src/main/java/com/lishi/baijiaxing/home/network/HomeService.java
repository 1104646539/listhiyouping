package com.lishi.baijiaxing.home.network;

import com.lishi.baijiaxing.home.model.AdList;
import com.lishi.baijiaxing.home.model.Commodity;
import com.lishi.baijiaxing.home.model.Festival;
import com.lishi.baijiaxing.home.model.HomeBean;
import com.lishi.baijiaxing.home.model.Seckill;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 网络请求
 * Created by Administrator on 2016/9/29.
 */
public interface HomeService {
    @GET("/index_api.php?m=index")
    Observable<Commodity> pullToRefresh(@Query("a") String type, @Query("page") String page);

    @GET("/index_api.php?m=index")
    Observable<AdList> getAdList(@Query("a") String type);

    @GET("/index_api.php?m=index")
    Observable<Festival> getFestival(@Query("a") String type);

    @GET("/index_api.php?m=index")
    Observable<Seckill> getSeckilBean(@Query("a") String type);

    @GET("/index_api.php?m=index")
    Observable<Commodity> getCommodity(@Query("a") String type);
}
