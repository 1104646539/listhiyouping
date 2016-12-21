package com.lishi.baijiaxing.myOrders.model;

import com.lishi.baijiaxing.myOrders.OrderDetailsCallback;
import com.lishi.baijiaxing.myOrders.ReturnedGoodsCallback;

/**
 * Created by Administrator on 2016/12/13.
 */

public interface OrderDetailsModel {
    void loadOrderDetails(OrderDetailsCallback callback, String oid);
    void changeOrderStatus(OrderDetailsCallback callback, String orderNumber, String status);
}
