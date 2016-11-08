package com.lishi.baijiaxing.home;

import com.lishi.baijiaxing.base.BaseRequestCallBack;
import com.lishi.baijiaxing.bean.HomeRecommendBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/29.
 */
public interface HomeCallBack extends BaseRequestCallBack {
    void pullToRefreshSuccess(ArrayList<HomeRecommendBean> data);

    void pullToRefreshFailed(String error);
}
