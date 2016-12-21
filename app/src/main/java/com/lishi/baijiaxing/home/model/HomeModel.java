package com.lishi.baijiaxing.home.model;

import com.lishi.baijiaxing.home.HomeCallBack;

/**
 * Created by Administrator on 2016/8/17.
 */
public interface HomeModel {
    void pullRecommendData(HomeCallBack callBack);

    void getAdList(HomeCallBack callBack);

    void getFestival(HomeCallBack callBack);

    void getSeckill(HomeCallBack callBack);

    void getCommodityList(HomeCallBack callBack);
}
