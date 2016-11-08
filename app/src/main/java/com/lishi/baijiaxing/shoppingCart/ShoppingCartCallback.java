package com.lishi.baijiaxing.shoppingCart;

import com.lishi.baijiaxing.base.BaseRequestCallBack;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.bean.HomeRecommendBean;

import java.util.ArrayList;

/**
 * View回调
 * Created by Administrator on 2016/10/8.
 */
public interface ShoppingCartCallback extends BaseRequestCallBack {
    void addStoreSuccess(ArrayList<CommodityBean> commodityBeen);

    void addStoreFailed(String error);

    void removeStoreSuccess(ArrayList<CommodityBean> commodityBeen);

    void removeStoreFailed(String error);

    void changeStoreSuccess(ArrayList<CommodityBean> commodityBeen);

    void changeStoreFailed(String error);

    void pullloadSuccess(ArrayList<HomeRecommendBean> homeRecommends);

    void pullloadFailed(String error);
}
