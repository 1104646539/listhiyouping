package com.lishi.baijiaxing.myOrders.presenter;

/**
 * Created by Administrator on 2016/12/13.
 */

public interface OrderDetailsPresenter {
    void loadOrderDetails(String oid);

    void changeOrderStatus(String orderNumber, String status);
}
