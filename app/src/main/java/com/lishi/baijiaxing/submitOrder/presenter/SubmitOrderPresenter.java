package com.lishi.baijiaxing.submitOrder.presenter;

import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrderBean;

import java.util.ArrayList;

/**
 * 提交订单
 * Created by Administrator on 2016/8/22.
 */
public interface SubmitOrderPresenter {
    void loadOrderData(ArrayList<CommodityBean> commodityBeen);

    void onLoadOrderDataSuccess(SubmitOrderBean submitOrderBean);

    void onLoadOrderDataFailed();
}
