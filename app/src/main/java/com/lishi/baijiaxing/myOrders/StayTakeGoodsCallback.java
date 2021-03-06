package com.lishi.baijiaxing.myOrders;

import com.lishi.baijiaxing.myOrders.model.MyOrderList;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;

/**
 * Created by Administrator on 2016/12/12.
 */

public interface StayTakeGoodsCallback {
    void loadOrderListSuccess(MyOrderList orderList);

    void loadOrderListFailed(String error);
    void changeOrderStatusSuccess(SCOperation orderList);

    void changeOrderStatusFailed(String error);   void onLastPage(String status);
}
