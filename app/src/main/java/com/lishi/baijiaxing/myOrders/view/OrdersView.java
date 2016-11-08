package com.lishi.baijiaxing.myOrders.view;

import com.lishi.baijiaxing.myOrders.model.MyOrderFormBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/19.
 */
public interface OrdersView {
    void loadData();

    void onSuccess(ArrayList<MyOrderFormBean> myOrderFormBeen);

    void onFailed();
}
