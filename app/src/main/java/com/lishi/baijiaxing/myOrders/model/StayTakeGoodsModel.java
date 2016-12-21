package com.lishi.baijiaxing.myOrders.model;

import com.lishi.baijiaxing.myOrders.StayEvaluateCallback;
import com.lishi.baijiaxing.myOrders.StayPaymentCallback;
import com.lishi.baijiaxing.myOrders.StayTakeGoodsCallback;

/**
 * Created by Administrator on 2016/12/12.
 */

public interface StayTakeGoodsModel {
    void loadOrderList(StayTakeGoodsCallback callback,int page);

    void changeOrderStatus(StayTakeGoodsCallback callback, String orderNumber, String status);
}
