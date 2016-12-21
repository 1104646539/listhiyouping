package com.lishi.baijiaxing.search.network;

import com.lishi.baijiaxing.search.model.HotSearch;
import com.lishi.baijiaxing.search.model.SearchList;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/19.
 */

public interface SearchService {
    @FormUrlEncoded
    @POST("/index_api.php?m=search&a=searchGoods")
    Observable<SearchList> searchCommodity(@Field("like") String commodityName, @Field("page") String page);

    @GET("/index_api.php?m=search&a=hotSearchList")
    Observable<HotSearch> getHotSearchCommodity();
}
