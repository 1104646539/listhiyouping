package com.lishi.baijiaxing.shoppingCart.model;

import com.lishi.baijiaxing.bean.HomeRecommendBean;
import com.lishi.baijiaxing.shoppingCart.ShoppingCartCallback;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/17.
 */
public interface ShoppingCartModel {

    void loadData(ShoppingCartCallback cartCallback);

    void addStore(ShoppingCartCallback cartCallback, ArrayList<CommodityBean> commodityBeen);

    void changeStore(ShoppingCartCallback cartCallback, ArrayList<CommodityBean> commodityBeen);

    void pullLoad(ShoppingCartCallback cartCallback, ArrayList<HomeRecommendBean> homeRecommend);

    void deleteStore(ShoppingCartCallback cartCallback, ArrayList<CommodityBean> commodityBeen);
}
