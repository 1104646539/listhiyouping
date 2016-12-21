package com.lishi.baijiaxing.myOrders.view;

import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.myOrders.model.MyOrderList;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;

import java.util.List;

/**
 * Created by Administrator on 2016/8/19.
 */
public interface StayTakeGoodsView extends BaseView {
    void loadData();

    void loadOrderListSuccess(List<MyOrderList.DataBean.OrderListBean> dataBeens);

    void loadOrderListFailed(String error);

    void changeOrderStatusSuccess(SCOperation orderList);

    void changeOrderStatusFailed(String error);    void onLastPage(String status);
}
