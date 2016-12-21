package com.lishi.baijiaxing.myOrders.view;

import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.myOrders.model.MyOrderList;

import java.util.List;

/**
 * Created by Administrator on 2016/8/19.
 */
public interface AllOrderView extends BaseView {
    void loadData();

    void loadOrderListSuccess(List<MyOrderList.DataBean.OrderListBean> dataBeens);

    void loadOrderListFailed(String error);
    void onLastPage(String status);
}
