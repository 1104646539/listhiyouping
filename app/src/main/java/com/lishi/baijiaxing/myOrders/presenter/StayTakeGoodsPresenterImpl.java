package com.lishi.baijiaxing.myOrders.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.myOrders.ReturnedGoodsCallback;
import com.lishi.baijiaxing.myOrders.StayTakeGoodsCallback;
import com.lishi.baijiaxing.myOrders.model.MyOrderList;
import com.lishi.baijiaxing.myOrders.model.StayPaymentModelImpl;
import com.lishi.baijiaxing.myOrders.model.StayTakeGoodsModelImpl;
import com.lishi.baijiaxing.myOrders.view.StayPaymentView;
import com.lishi.baijiaxing.myOrders.view.StayTakeGoodsView;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/12.
 */

public class StayTakeGoodsPresenterImpl extends BasePresenter implements StayTakeGoodsPresenter, StayTakeGoodsCallback {
    private StayTakeGoodsModelImpl mStayTakeGoodsModel;
    private StayTakeGoodsView mStayTakeGoodsView;

    public StayTakeGoodsPresenterImpl(BaseView baseView) {
        super(baseView);
        this.mStayTakeGoodsModel = new StayTakeGoodsModelImpl();
        this.mStayTakeGoodsView = (StayTakeGoodsView) baseView;
    }

    @Override
    public void loadOrderListSuccess(MyOrderList orderList) {
        mStayTakeGoodsView.closeDialog();
        if (orderList.getData().getOrderList() == null) {
            orderList.getData().setOrderList(new ArrayList<MyOrderList.DataBean.OrderListBean>());
        }
        mStayTakeGoodsView.loadOrderListSuccess(orderList.getData().getOrderList());
    }

    @Override
    public void loadOrderListFailed(String error) {
        mStayTakeGoodsView.closeDialog();
        mStayTakeGoodsView.loadOrderListFailed(error);
    }

    @Override
    public void onLastPage(String status) {
        mStayTakeGoodsView.onLastPage(status);
    }
    @Override
    public void changeOrderStatusSuccess(SCOperation orderList) {
        mStayTakeGoodsView.closeDialog();
        mStayTakeGoodsView.changeOrderStatusSuccess(orderList);
    }

    @Override
    public void changeOrderStatusFailed(String error) {
        mStayTakeGoodsView.closeDialog();
        mStayTakeGoodsView.changeOrderStatusFailed(error);
    }

    @Override
    public void loadOrderList(int page) {
        mStayTakeGoodsView.showDialog();
        mStayTakeGoodsModel.loadOrderList(this, page);
    }

    @Override
    public void changeOrderStatus(String orderNumber, String status) {
        mStayTakeGoodsView.showDialog();
        mStayTakeGoodsModel.changeOrderStatus(this, orderNumber, status);
    }
}
