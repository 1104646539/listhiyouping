package com.lishi.baijiaxing.submitOrder.presenter;

import android.view.inputmethod.InputMethodSubtype;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.submitOrder.SubmitOrderCallback;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrder;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrderBean;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrderModelImpl;
import com.lishi.baijiaxing.submitOrder.model.WriteOrder;
import com.lishi.baijiaxing.submitOrder.view.SubmitOrderView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/22.
 */
public class SubmitOrderPresenterImpl extends BasePresenter implements SubmitOrderPresenter, SubmitOrderCallback {
    private SubmitOrderModelImpl mSumbitOrderModel;
    private SubmitOrderView mSumbitOrderView;

    public SubmitOrderPresenterImpl(BaseView baseView) {
        super(baseView);
        this.mSumbitOrderView = (SubmitOrderView) baseView;
        mSumbitOrderModel = new SubmitOrderModelImpl();
    }

    @Override
    public void onLoadOrderDataSuccess(WriteOrder writeOrder) {
        mSumbitOrderView.onLoadOrderDataSuccess(writeOrder);
    }

    @Override
    public void onLoadOrderDataFailed(String error) {
        mSumbitOrderView.onLoadOrderDataFailed(error);
    }

    @Override
    public void onSubmitOrderSuccess(SubmitOrder submitOrder) {
        mSumbitOrderView.onSubmitOrderSuccess(submitOrder);
    }

    @Override
    public void onSubmitOrderFailed(String error) {
        mSumbitOrderView.onSubmitOrderFailed(error);
    }

    @Override
    public void loadOrderData(String form) {
        mSumbitOrderModel.loadOrderData(this,form);
    }

    @Override
    public void submitOrderData(SubmitOrderBean submitOrderBean) {
        mSumbitOrderModel.submitOrderData(this, submitOrderBean);
    }

}