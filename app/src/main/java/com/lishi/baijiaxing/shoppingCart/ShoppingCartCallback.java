package com.lishi.baijiaxing.shoppingCart;

import com.lishi.baijiaxing.base.BaseRequestCallBack;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.bean.HomeRecommendBean;
import com.lishi.baijiaxing.shoppingCart.model.SCCommodityList;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;
import com.lishi.baijiaxing.shoppingCart.model.SCRecommendList;

import java.util.ArrayList;
import java.util.List;

/**
 * View回调
 * Created by Administrator on 2016/10/8.
 */
public interface ShoppingCartCallback extends BaseRequestCallBack {
    void removeCommoditySuccess(SCOperation scOperation);

    void removeCommodityFailed(String error);

    void loadCommodityListSuccess(SCCommodityList commodityList);

    void loadRecommendListSuccess(SCRecommendList commodityList);

    void loadCommodityListFailed(String error);

    void loadRecommendListFailed(String error);

    void pullDownloadSuccess(SCRecommendList recommendLists);

    void pullDownloadFailed(String error);

    void changeCommodityFailed(String error);

    void changeCommoditySuccess(SCOperation scOperation);

    void upCommodityInfoSuccess(SCCommodityList scCommodityList);

    void upCommodityInfoFailed(String error);
}
