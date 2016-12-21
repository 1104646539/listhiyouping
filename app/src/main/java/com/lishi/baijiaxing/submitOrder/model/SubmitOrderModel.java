package com.lishi.baijiaxing.submitOrder.model;

import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.submitOrder.SubmitOrderCallback;
import com.lishi.baijiaxing.submitOrder.presenter.SubmitOrderPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 提交订单
 * Created by Administrator on 2016/8/22.
 */
public interface SubmitOrderModel {
    void loadOrderData(SubmitOrderCallback callback, String from);

    void submitOrderData(SubmitOrderCallback callback, SubmitOrderBean submitOrderBean);
}
