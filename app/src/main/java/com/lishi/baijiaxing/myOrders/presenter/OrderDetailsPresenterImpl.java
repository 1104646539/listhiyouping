package com.lishi.baijiaxing.myOrders.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.myOrders.OrderDetailsCallback;
import com.lishi.baijiaxing.myOrders.model.OrderDetails;
import com.lishi.baijiaxing.myOrders.model.OrderDetailsModelImpl;
import com.lishi.baijiaxing.myOrders.view.OrderDetailsView;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;

/**
 * Created by Administrator on 2016/12/13.
 */

public class OrderDetailsPresenterImpl extends BasePresenter implements OrderDetailsPresenter, OrderDetailsCallback {
    private OrderDetailsView mOrderDetailsView;
    private OrderDetailsModelImpl mOrderDetailsModel;

    public OrderDetailsPresenterImpl(BaseView baseView) {
        super(baseView);
        this.mOrderDetailsModel = new OrderDetailsModelImpl();
        this.mOrderDetailsView = (OrderDetailsView) baseView;

    }

    @Override
    public void loadOrderDetailsSuccess(OrderDetails orderDetails) {
        mOrderDetailsView.closeDialog();
        mOrderDetailsView.loadOrderDetailsSuccess(orderDetails);
    }

    @Override
    public void loadOrderDetailsFailed(String error) {
        mOrderDetailsView.closeDialog();
        mOrderDetailsView.loadOrderDetailsFailed(error);
    }

    @Override
    public void changeOrderStatusSuccess(SCOperation orderList) {
        mOrderDetailsView.closeDialog();
        mOrderDetailsView.changeOrderStatusSuccess(orderList);
    }

    @Override
    public void changeOrderStatusFailed(String error) {
        mOrderDetailsView.closeDialog();
        mOrderDetailsView.changeOrderStatusFailed(error);
    }


    @Override
    public void loadOrderDetails(String oid) {
        mOrderDetailsView.showDialog();
        mOrderDetailsModel.loadOrderDetails(this, oid);
    }

    @Override
    public void changeOrderStatus(String orderNumber, String status) {
        mOrderDetailsView.showDialog();
        mOrderDetailsModel.changeOrderStatus(this, orderNumber, status);
    }
}
