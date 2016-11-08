package com.lishi.baijiaxing.shoppingCart.view;

import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.bean.HomeRecommendBean;
import com.lishi.baijiaxing.shoppingCart.model.ShoppingBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/17.
 */
public interface ShoppingCartView extends BaseView<ShoppingBean> {
    void onAddDataSuccess(ArrayList<CommodityBean> commodityBeen);

    void onAddDataFailed(String error);

    void onPullLoadDataSuccess(ArrayList<HomeRecommendBean> homeRecommend);

    void onPullLoadDataFailed(String error);

    void onDeleteDataSuccess(ArrayList<CommodityBean> commodityBeen);

    void onDeleteDataFailed(String error);

    void onChangeStoreSuccess(ArrayList<CommodityBean> commodityBeen);

    void onChangeStoreFailed(String error);
}
