package com.lishi.baijiaxing.home.model;

import com.lishi.baijiaxing.home.HomeCallBack;

/**
 * Created by Administrator on 2016/8/17.
 */
public interface HomeModel {
    void loadData(HomeCallBack callBack);

    void pullRecommendData(HomeCallBack callBack);
}
