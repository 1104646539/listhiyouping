package com.lishi.baijiaxing.myOrders.model;

import com.lishi.baijiaxing.myOrders.ReturnedGoodsCallback;
import com.lishi.baijiaxing.myOrders.StayEvaluateCallback;
import com.lishi.baijiaxing.myOrders.StayPaymentCallback;

/**
 * Created by Administrator on 2016/12/12.
 */

public interface StayEvaluateModel {
    void loadOrderList(StayEvaluateCallback callback, int page);

    void changeOrderStatus(StayEvaluateCallback callback, String oid, String status);

    void deleteOrder(StayEvaluateCallback callback, String oid);
}
