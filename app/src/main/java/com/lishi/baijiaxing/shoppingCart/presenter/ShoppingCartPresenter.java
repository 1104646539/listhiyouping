package com.lishi.baijiaxing.shoppingCart.presenter;

import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.bean.HomeRecommendBean;

import java.util.ArrayList;

/**
 * View调用
 * Created by Administrator on 2016/8/17.
 */
public interface ShoppingCartPresenter {
    void loadData();

    void addStore(ArrayList<CommodityBean> commodityBeen);

    void removeStore(ArrayList<CommodityBean> commodityBeen);

    void pullLoad(ArrayList<HomeRecommendBean> homeRecommends);

    void changeStore(ArrayList<CommodityBean> commodityBeen);
}
