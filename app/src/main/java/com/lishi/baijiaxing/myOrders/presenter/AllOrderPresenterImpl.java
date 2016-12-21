package com.lishi.baijiaxing.myOrders.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.myOrders.AllOrderCallback;
import com.lishi.baijiaxing.myOrders.ReturnedGoodsCallback;
import com.lishi.baijiaxing.myOrders.model.AllOrderModel;
import com.lishi.baijiaxing.myOrders.model.AllOrderModelImpl;
import com.lishi.baijiaxing.myOrders.model.MyOrderList;
import com.lishi.baijiaxing.myOrders.view.AllOrderView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/12.
 */

public class AllOrderPresenterImpl extends BasePresenter implements AllOrderPresenter, AllOrderCallback {
    private AllOrderModelImpl mAllOrderModel;
    private AllOrderView mAllOrderView;

    public AllOrderPresenterImpl(BaseView baseView) {
        super(baseView);
        this.mAllOrderModel = new AllOrderModelImpl();
        this.mAllOrderView = (AllOrderView) baseView;
    }

    @Override
    public void loadOrderListSuccess(MyOrderList orderList) {
        mAllOrderView.closeDialog();
        if (orderList.getData().getOrderList() == null) {
            orderList.getData().setOrderList(new ArrayList<MyOrderList.DataBean.OrderListBean>());
        }
        mAllOrderView.loadOrderListSuccess(orderList.getData().getOrderList());
    }

    @Override
    public void loadOrderListFailed(String error) {
        mAllOrderView.closeDialog();
        mAllOrderView.loadOrderListFailed(error);
    }

    @Override
    public void onLastPage(String status) {
        mAllOrderView.onLastPage(status);
    }

    @Override
    public void loadOrderList(int page) {
        mAllOrderView.showDialog();
        mAllOrderModel.loadOrderList(this, page);
    }
}
