package com.lishi.baijiaxing.myOrders.presenter;

import com.lishi.baijiaxing.myOrders.model.MyOrderFormBean;
import com.lishi.baijiaxing.myOrders.model.OrdersModelImpl;
import com.lishi.baijiaxing.myOrders.view.OrdersView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/19.
 */
public class OrdersPresenterImpl implements OrdersPresenter {
    private OrdersModelImpl mOrdersModelImpl;
    private OrdersView mOrdersView;

    public OrdersPresenterImpl(OrdersView ordersView) {
        this.mOrdersView = ordersView;
        mOrdersModelImpl = new OrdersModelImpl();
    }

    @Override
    public void LoadData(String url) {
        mOrdersModelImpl.loadData(this, url);
        mOrdersView.loadData();
    }

    @Override
    public void onSuccess(ArrayList<MyOrderFormBean> myOrderFormBeen) {
        mOrdersView.onSuccess(myOrderFormBeen);

    }

    @Override
    public void onFailed() {
        mOrdersView.onFailed();
    }
}
