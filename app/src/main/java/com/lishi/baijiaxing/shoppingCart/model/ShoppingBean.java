package com.lishi.baijiaxing.shoppingCart.model;

import com.lishi.baijiaxing.bean.HomeRecommendBean;

import java.util.ArrayList;

/**
 * 购物车初始化数据
 * Created by Administrator on 2016/10/8.
 */
public class ShoppingBean {
    ArrayList<CommodityBean> commodityBeen;
    ArrayList<HomeRecommendBean> mHomeRecommendBeen;

    public ArrayList<CommodityBean> getCommodityBeen() {
        return commodityBeen;
    }

    public void setCommodityBeen(ArrayList<CommodityBean> commodityBeen) {
        this.commodityBeen = commodityBeen;
    }

    public ArrayList<HomeRecommendBean> getHomeRecommendBeen() {
        return mHomeRecommendBeen;
    }

    public void setHomeRecommendBeen(ArrayList<HomeRecommendBean> homeRecommendBeen) {
        mHomeRecommendBeen = homeRecommendBeen;
    }

    public ShoppingBean(ArrayList<CommodityBean> commodityBeen, ArrayList<HomeRecommendBean> homeRecommendBeen) {
        this.commodityBeen = commodityBeen;
        mHomeRecommendBeen = homeRecommendBeen;
    }
}
