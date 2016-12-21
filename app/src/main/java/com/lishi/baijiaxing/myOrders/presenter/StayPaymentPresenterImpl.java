package com.lishi.baijiaxing.myOrders.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.myOrders.ReturnedGoodsCallback;
import com.lishi.baijiaxing.myOrders.StayPaymentCallback;
import com.lishi.baijiaxing.myOrders.model.MyOrderList;
import com.lishi.baijiaxing.myOrders.model.ReturnedGoodsModelImpl;
import com.lishi.baijiaxing.myOrders.model.StayEvaluateModelImpl;
import com.lishi.baijiaxing.myOrders.model.StayPaymentModelImpl;
import com.lishi.baijiaxing.myOrders.view.ReturnedGoodsView;
import com.lishi.baijiaxing.myOrders.view.StayEvaluateView;
import com.lishi.baijiaxing.myOrders.view.StayPaymentView;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/12.
 */

public class StayPaymentPresenterImpl extends BasePresenter implements StayPaymentPresenter, StayPaymentCallback {
    private StayPaymentModelImpl mStayPaymentModel;
    private StayPaymentView mStayPaymentView;

    public StayPaymentPresenterImpl(BaseView baseView) {
        super(baseView);
        this.mStayPaymentModel = new StayPaymentModelImpl();
        this.mStayPaymentView = (StayPaymentView) baseView;
    }

    @Override
    public void loadOrderListSuccess(MyOrderList orderList) {
        mStayPaymentView.closeDialog();
        if (orderList.getData().getOrderList() == null) {
            orderList.getData().setOrderList(new ArrayList<MyOrderList.DataBean.OrderListBean>());
        }
        mStayPaymentView.loadOrderListSuccess(orderList.getData().getOrderList());
    }

    @Override
    public void loadOrderListFailed(String error) {
        mStayPaymentView.closeDialog();
        mStayPaymentView.loadOrderListFailed(error);
    }

    @Override
    public void onLastPage(String status) {
        mStayPaymentView.onLastPage(status);
    }
    @Override
    public void changeOrderStatusSuccess(SCOperation scOperation) {
        mStayPaymentView.closeDialog();
        mStayPaymentView.changeOrderStatusSuccess(scOperation);
    }

    @Override
    public void changeOrderStatusFailed(String error) {
        mStayPaymentView.closeDialog();
        mStayPaymentView.changeOrderStatusFailed(error);
    }

    @Override
    public void loadOrderList(int page) {
        mStayPaymentView.showDialog();
        mStayPaymentModel.loadOrderList(this, page);
    }

    @Override
    public void changeOrderStatus(String orderNumber, String status) {
        mStayPaymentView.showDialog();
        mStayPaymentModel.changeOrderStatus(this, orderNumber, status);
    }
}
