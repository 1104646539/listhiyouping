package com.lishi.baijiaxing.home.model;

import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;

/**
 * 首页商品的信息
 * Created by Administrator on 2016/6/3.
 */
public class HomeCommodityBean {
    private CommodityBean mCommodityBean;

    public HomeCommodityBean() {

    }

    public HomeCommodityBean(CommodityBean commodityBean) {
        mCommodityBean = commodityBean;
    }

    public CommodityBean getCommodityBean() {
        return mCommodityBean;
    }

    public void setCommodityBean(CommodityBean commodityBean) {
        mCommodityBean = commodityBean;
    }
}
