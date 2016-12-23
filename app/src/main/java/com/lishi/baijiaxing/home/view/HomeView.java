package com.lishi.baijiaxing.home.view;

import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.bean.HomeRecommendBean;
import com.lishi.baijiaxing.home.model.AdList;
import com.lishi.baijiaxing.home.model.Commodity;
import com.lishi.baijiaxing.home.model.Festival;
import com.lishi.baijiaxing.home.model.HomeBean;
import com.lishi.baijiaxing.home.model.Seckill;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/17.
 */
public interface HomeView extends BaseView<HomeBean> {
    void onPullSuccess(List<Commodity.DataBean.CommodityListBean> datas);

    void onPullloadFailed(String error);

    void getAdListSuccess(List<AdList.DataBean> adLists);

    void getFestivalSuccess(Festival.DataBean festival);

    void getSeckillSuccess(Seckill.DataBean seckill);

    void getCommodityListSuccess(List<Commodity.DataBean.CommodityListBean> commodities);

    void getAdListFailed(String error);

    void getFestivalFailed(String error);

    void getSeckillFailed(String error);

    void getCommodityListFailed(String error);

    void onLastPage(String status);
}
