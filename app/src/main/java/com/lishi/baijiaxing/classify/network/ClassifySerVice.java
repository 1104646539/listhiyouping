package com.lishi.baijiaxing.classify.network;

import com.lishi.baijiaxing.classify.model.ClassAd;
import com.lishi.baijiaxing.classify.model.ClassList;
import com.lishi.baijiaxing.classify.model.ClassOne;
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
    @GET("/index_api.php?m=category")
    Observable<ClassList> loadClassList(@Query("a") String categoryNameList);

    @GET("/index_api.php?m=category")
    Observable<ClassAd> loadAd(@Query("a") String adUrl);

    @GET("/index_api.php?m=category")
    Observable<ClassOne> loadClassOne(@Query("a") String categoryGoodsList, @Query("gid") String classId);
}
