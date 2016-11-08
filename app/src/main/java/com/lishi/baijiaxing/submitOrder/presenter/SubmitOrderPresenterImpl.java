package com.lishi.baijiaxing.submitOrder.presenter;

import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrderBean;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrderModelImpl;
import com.lishi.baijiaxing.submitOrder.view.SubmitOrderView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/22.
 */
public class SubmitOrderPresenterImpl implements SubmitOrderPresenter {
    private SubmitOrderModelImpl mSumbitOrderModel;
    private SubmitOrderView mSumbitOrderView;

    public SubmitOrderPresenterImpl(SubmitOrderView sumbitOrderView) {
        this.mSumbitOrderView = sumbitOrderView;
        mSumbitOrderModel = new SubmitOrderModelImpl();
    }

    @Override
    public void loadOrderData(ArrayList<CommodityBean> commodityBeen) {
        mSumbitOrderModel.loadOrderData(this, commodityBeen);
        mSumbitOrderView.loadOrderData();
    }


    @Override
    public void onLoadOrderDataSuccess(SubmitOrderBean submitOrderBean) {
        mSumbitOrderView.onLoadOrderDataSuccess(submitOrderBean);
    }


    @Override
    public void onLoadOrderDataFailed() {
        mSumbitOrderView.onLoadOrderDataFailed();
    }
}
