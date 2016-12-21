package com.lishi.baijiaxing.myOrders;

import com.lishi.baijiaxing.myOrders.model.MyOrderList;

/**
 * Created by Administrator on 2016/12/13.
 */

public interface AllOrderCallback {
    void loadOrderListSuccess(MyOrderList orderList);

    void loadOrderListFailed(String error);
    void onLastPage(String status);
}
