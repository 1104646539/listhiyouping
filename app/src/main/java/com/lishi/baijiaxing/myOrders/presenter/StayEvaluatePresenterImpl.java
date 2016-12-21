package com.lishi.baijiaxing.myOrders.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.myOrders.StayEvaluateCallback;
import com.lishi.baijiaxing.myOrders.model.MyOrderList;
import com.lishi.baijiaxing.myOrders.model.StayEvaluateModelImpl;
import com.lishi.baijiaxing.myOrders.view.StayEvaluateView;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/12.
 */

public class StayEvaluatePresenterImpl extends BasePresenter implements StayEvaluatePresenter, StayEvaluateCallback {
    private StayEvaluateModelImpl mStayEvaluateModel;
    private StayEvaluateView mStayEvaluateView;


    public StayEvaluatePresenterImpl(BaseView baseView) {
        super(baseView);
        this.mStayEvaluateModel = new StayEvaluateModelImpl();
        this.mStayEvaluateView = (StayEvaluateView) baseView;
    }

    @Override
    public void loadOrderListSuccess(MyOrderList orderList) {
        mStayEvaluateView.closeDialog();
        if (orderList.getData().getOrderList() == null) {
            orderList.getData().setOrderList(new ArrayList<MyOrderList.DataBean.OrderListBean>());
        }
        mStayEvaluateView.loadOrderListSuccess(orderList.getData().getOrderList());
    }

    @Override
    public void loadOrderListFailed(String error) {
        mStayEvaluateView.closeDialog();
        mStayEvaluateView.loadOrderListFailed(error);
    }

    @Override
    public void changeOrderStatusSuccess(SCOperation orderList) {
        mStayEvaluateView.closeDialog();
        mStayEvaluateView.changeOrderStatusSuccess(orderList);
    }

    @Override
    public void onLastPage(String status) {
        mStayEvaluateView.onLastPage(status);
    }
    @Override
    public void changeOrderStatusFailed(String error) {
        mStayEvaluateView.closeDialog();
        mStayEvaluateView.changeOrderStatusFailed(error);
    }

    @Override
    public void deleteOrderStatusSuccess(SCOperation orderList) {
        mStayEvaluateView.deleteOrderStatusSuccess(orderList);
    }

    @Override
    public void deleteOrderStatusFailed(String error) {
        mStayEvaluateView.deleteOrderStatusFailed(error);
    }

    @Override
    public void loadOrderList(int page) {
        mStayEvaluateView.showDialog();
        mStayEvaluateModel.loadOrderList(this, page);
    }

    @Override
    public void changeOrderStatus(String orderNumber, String status) {
        mStayEvaluateModel.changeOrderStatus(this, orderNumber, status);
    }

    @Override
    public void deleteOrder(String oid) {
        mStayEvaluateModel.deleteOrder(this, oid);
    }
}
