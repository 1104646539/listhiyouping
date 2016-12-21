package com.lishi.baijiaxing.wxapi.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.pay.model.WxPrepay;
import com.lishi.baijiaxing.wxapi.WxPayCallback;
import com.lishi.baijiaxing.wxapi.model.WxPayModelImpl;
import com.lishi.baijiaxing.wxapi.view.WxPayView;

/**
 * Created by Administrator on 2016/12/14.
 */

public class WxPayPresenterImpl extends BasePresenter implements WxPayPresenter, WxPayCallback {
    private WxPayModelImpl mWxPayModel;
    private WxPayView mWxPayView;

    public WxPayPresenterImpl(BaseView baseView) {
        super(baseView);
        this.mWxPayModel = new WxPayModelImpl();
        this.mWxPayView = (WxPayView) baseView;
    }

    @Override
    public void getPrepayInfoSuccess(WxPrepay prepay) {
        mWxPayView.closeDialog();
        mWxPayView.getPrepayInfoSuccess(prepay);
    }

    @Override
    public void getPrepayInfoFailed(String error) {
        mWxPayView.closeDialog();
        mWxPayView.getPrepayInfoFailed(error);
    }

    @Override
    public void getPrepayInfo(String orderNumber) {
        mWxPayView.showDialog();
        mWxPayModel.getPrepayInfo(this, orderNumber);
    }
}
