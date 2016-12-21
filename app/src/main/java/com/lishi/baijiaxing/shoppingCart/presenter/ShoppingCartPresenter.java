package com.lishi.baijiaxing.shoppingCart.presenter;

import com.lishi.baijiaxing.shoppingCart.ShoppingCartCallback;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.bean.HomeRecommendBean;
import com.lishi.baijiaxing.shoppingCart.model.SCCommodityList;
import com.lishi.baijiaxing.shoppingCart.model.SCRecommendList;

import java.util.ArrayList;
import java.util.List;

/**
 * View调用
 * Created by Administrator on 2016/8/17.
 */
public interface ShoppingCartPresenter {
    void loadCommodity();

    void loadRecommend();

    void removeCommodity( List<String> deleteIds);

    void pullDownLoad();

    void changeCommodity(SCCommodityList.DataBean dataBean,String number);

    void upCommodityInfo(SCCommodityList.DataBean dataBean);
}
