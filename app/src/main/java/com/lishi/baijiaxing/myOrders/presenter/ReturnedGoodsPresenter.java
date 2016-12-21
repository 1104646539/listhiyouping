package com.lishi.baijiaxing.myOrders.presenter;

/**
 * Created by Administrator on 2016/12/12.
 */

public interface ReturnedGoodsPresenter {
    void loadOrderList(int page);

    void changeOrderStatus(String orderNumber, String status);
}
