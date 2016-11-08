package com.lishi.baijiaxing.myOrders.model;

import com.lishi.baijiaxing.myOrders.presenter.OrdersPresenter;

/**
 * Created by Administrator on 2016/8/19.
 */
public interface OrdersModel {
    void loadData(OrdersPresenter presenter,String url);
    
}
