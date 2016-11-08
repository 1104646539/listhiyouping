package com.lishi.baijiaxing.home.view;

import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.bean.HomeRecommendBean;
import com.lishi.baijiaxing.home.model.HomeBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/17.
 */
public interface HomeView extends BaseView<HomeBean> {
    void onRecommendonPullSuccess(ArrayList<HomeRecommendBean> datas);

    void onPullloadFailed();
}
