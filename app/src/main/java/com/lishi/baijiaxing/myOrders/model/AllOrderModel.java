package com.lishi.baijiaxing.myOrders.model;

import com.lishi.baijiaxing.myOrders.AllOrderCallback;
import com.lishi.baijiaxing.myOrders.ReturnedGoodsCallback;

/**
 * Created by Administrator on 2016/12/13.
 */

public interface AllOrderModel {
    void loadOrderList(AllOrderCallback callback,int page);
}
