package com.lishi.baijiaxing.shoppingCart.view;

import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.bean.HomeRecommendBean;
import com.lishi.baijiaxing.shoppingCart.model.SCCommodityList;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;
import com.lishi.baijiaxing.shoppingCart.model.SCRecommendList;
import com.lishi.baijiaxing.shoppingCart.model.ShoppingBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/17.
 */
public interface ShoppingCartView extends BaseView {
    void onPullLoadDataSuccess(SCRecommendList recommendList);

    void onPullLoadDataFailed(String error);

    void onRemoveDataSuccess(SCOperation operation);

    void onRemoveDataFailed(String error);

    void onLoadCommoditySuccess(SCCommodityList scCommodityList);

    void onLoadRecommendSuccess(SCRecommendList scRecommendList);

    void onLoadCommodityFailed(String error);

    void onLoadRecommendFailed(String error);

    void onChangeCommoditySuccess(SCOperation scOperation);

    void onChangeCommodityFailed(String error);

    void upCommodityInfoSuccess(SCCommodityList scCommodityList);

    void upCommodityInfoFailed(String error);
}
