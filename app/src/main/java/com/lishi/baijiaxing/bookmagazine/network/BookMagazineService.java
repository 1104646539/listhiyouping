package com.lishi.baijiaxing.bookmagazine.network;

import com.lishi.baijiaxing.hot.model.HotCommodity;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/12.
 */

public interface BookMagazineService {
    @GET("/index_api.php?m=bao&a=bookList")
    Observable<HotCommodity> getBookMagazineList();
}
