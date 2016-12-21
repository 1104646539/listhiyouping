package com.lishi.baijiaxing.shoppingCart.model;

import com.lishi.baijiaxing.bean.HomeRecommendBean;
import com.lishi.baijiaxing.shoppingCart.ShoppingCartCallback;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Field;

/**
 * Created by Administrator on 2016/8/17.
 */
public interface ShoppingCartModel {
    void loadCommodityList(ShoppingCartCallback callback);

    void loadRecommendList(ShoppingCartCallback callback);

    void removeCommodity(ShoppingCartCallback callback, List<String> deleteIds);

    void pullDownLoad(ShoppingCartCallback cartCallback);

    void changeCommodity(ShoppingCartCallback cartCallback, SCCommodityList.DataBean dataBean, String number);

    void upCommodityInfo(ShoppingCartCallback cartCallback, SCCommodityList.DataBean dataBean);
}
