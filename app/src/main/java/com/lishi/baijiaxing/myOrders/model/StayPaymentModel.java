package com.lishi.baijiaxing.myOrders.model;

import com.lishi.baijiaxing.myOrders.StayEvaluateCallback;
import com.lishi.baijiaxing.myOrders.StayPaymentCallback;

/**
 * Created by Administrator on 2016/12/12.
 */

public interface StayPaymentModel {
    void loadOrderList(StayPaymentCallback callback,int page);

    void changeOrderStatus(StayPaymentCallback callback, String orderNumber, String status);
}
