package com.lishi.baijiaxing.myOrders.presenter;

import android.widget.ImageView;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.myOrders.ReturnedGoodsCallback;
import com.lishi.baijiaxing.myOrders.model.MyOrderList;
import com.lishi.baijiaxing.myOrders.model.ReturnedGoodsModelImpl;
import com.lishi.baijiaxing.myOrders.view.ReturnedGoodsView;
import com.lishi.baijiaxing.retrievePassword.presenter.RetrievePresenter;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/12.
 */

public class ReturnedGoodsPresenterImpl extends BasePresenter implements ReturnedGoodsPresenter, ReturnedGoodsCallback {
    private ReturnedGoodsModelImpl mReturnedGoodsModel;
    private ReturnedGoodsView mReturnedGoodsView;

    public ReturnedGoodsPresenterImpl(BaseView baseView) {
        super(baseView);
        this.mReturnedGoodsModel = new ReturnedGoodsModelImpl();
        this.mReturnedGoodsView = (ReturnedGoodsView) baseView;
    }

    @Override
    public void loadOrderListSuccess(MyOrderList orderList) {
        mReturnedGoodsView.closeDialog();
        if (orderList.getData().getOrderList() == null) {
            orderList.getData().setOrderList(new ArrayList<MyOrderList.DataBean.OrderListBean>());
        }
        mReturnedGoodsView.loadOrderListSuccess(orderList.getData().getOrderList());
    }

    @Override
    public void loadOrderListFailed(String error) {
        mReturnedGoodsView.closeDialog();
        mReturnedGoodsView.loadOrderListFailed(error);
    }

    @Override
    public void changeOrderStatusSuccess(SCOperation orderList) {
        mReturnedGoodsView.closeDialog();
        mReturnedGoodsView.changeOrderStatusSuccess(orderList);
    }

    @Override
    public void changeOrderStatusFailed(String error) {
        mReturnedGoodsView.closeDialog();
        mReturnedGoodsView.changeOrderStatusFailed(error);
    }

    @Override
    public void loadOrderList(int page) {
        mReturnedGoodsView.showDialog();
        mReturnedGoodsModel.loadOrderList(this, page);
    }

    @Override
    public void onLastPage(String status) {
        mReturnedGoodsView.onLastPage(status);
    }
    @Override
    public void changeOrderStatus(String orderNumber, String status) {
        mReturnedGoodsView.showDialog();
        mReturnedGoodsModel.changeOrderStatus(this, orderNumber, status);
    }
}
