package com.lishi.baijiaxing.myOrders.model;

import com.lishi.baijiaxing.myOrders.ReturnedGoodsCallback;
import com.lishi.baijiaxing.myOrders.StayEvaluateCallback;

/**
 * Created by Administrator on 2016/12/12.
 */

public interface ReturnedGoodsModel {
    void loadOrderList(ReturnedGoodsCallback callback,int page);

    void changeOrderStatus(ReturnedGoodsCallback callback, String orderNumber, String status);
}
