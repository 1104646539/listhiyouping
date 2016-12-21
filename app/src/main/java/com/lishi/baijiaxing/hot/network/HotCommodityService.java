package com.lishi.baijiaxing.hot.network;

import com.lishi.baijiaxing.hot.model.HotCommodity;
import com.lishi.baijiaxing.hot.model.HotCommodityModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/12.
 */

public interface HotCommodityService {
    @GET("/index_api.php?m=bao&a=hotList")
    Observable<HotCommodity> getHotCommodityList();
}
