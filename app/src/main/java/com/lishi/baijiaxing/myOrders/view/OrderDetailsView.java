package com.lishi.baijiaxing.myOrders.view;

import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.myOrders.model.OrderDetails;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;

/**
 * Created by Administrator on 2016/12/13.
 */

public interface OrderDetailsView extends BaseView {
    void loadOrderDetailsSuccess(OrderDetails orderDetails);

    void loadOrderDetailsFailed(String error);

    void changeOrderStatusSuccess(SCOperation orderList);

    void changeOrderStatusFailed(String error);
}
