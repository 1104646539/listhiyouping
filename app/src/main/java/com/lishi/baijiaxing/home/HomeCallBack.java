package com.lishi.baijiaxing.home;

import com.lishi.baijiaxing.base.BaseRequestCallBack;
import com.lishi.baijiaxing.bean.HomeRecommendBean;
import com.lishi.baijiaxing.home.model.AdList;
import com.lishi.baijiaxing.home.model.Commodity;
import com.lishi.baijiaxing.home.model.Festival;
import com.lishi.baijiaxing.home.model.Seckill;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */
public interface HomeCallBack extends BaseRequestCallBack {
    void pullToRefreshSuccess(List<Commodity.DataBean.CommodityListBean> data);

    void pullToRefreshFailed(String error);

    void getAdListSuccess(List<AdList.DataBean> adLists);

    void getFestivalSuccess(Festival.DataBean festivals);

    void getSeckillSuccess(Seckill.DataBean seckillBeen);

    void getCommodityListSuccess(List<Commodity.DataBean.CommodityListBean> commodities);

    void getAdListFailed(String error);

    void getFestivalFailed(String error);

    void getSeckillFailed(String error);

    void getCommodityListFailed(String error);

    void onLastPage(String status);
}
