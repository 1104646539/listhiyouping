package com.lishi.baijiaxing.myOrders.presenter;

import com.lishi.baijiaxing.myOrders.StayEvaluateCallback;

/**
 * Created by Administrator on 2016/12/12.
 */

public interface StayEvaluatePresenter {
    void loadOrderList(int page);

    void changeOrderStatus(String orderNumber, String status);
    void deleteOrder(String oid);
}
