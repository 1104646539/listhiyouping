package com.lishi.baijiaxing.wxapi.model;

import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.pay.model.WxPrepay;
import com.lishi.baijiaxing.utils.LoginUtil;
import com.lishi.baijiaxing.utils.Status;
import com.lishi.baijiaxing.wxapi.WxPayCallback;
import com.lishi.baijiaxing.wxapi.network.WxPayService;
import com.orhanobut.logger.Logger;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/14.
 */

public class WxPayModelImpl extends BaseModel implements WxPayModel {
    private WxPayService mWxPayService;

    public WxPayModelImpl() {
        mWxPayService = (WxPayService) getRetrofitManager().getHomeService(WxPayService.class);
    }

    @Override
    public void getPrepayInfo(final WxPayCallback callback, String orderNumber) {
        if (!LoginUtil.getInstance().isLogin()) {
            callback.getPrepayInfoFailed("未登录");
            return;
        }
        mWxPayService.getPrepayInfo(LoginUtil.getInstance().getLogin().getData().getUid(), LoginUtil.getInstance().getLogin().getData().getToken()
                , LoginUtil.getInstance().getLogin().getData().getType(), orderNumber).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WxPrepay>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.getPrepayInfoFailed(e.toString());
                        Logger.d(e.toString());
                    }

                    @Override
                    public void onNext(WxPrepay wxPrepay) {
                        Logger.d("statue:"+wxPrepay.getStatus()+"msg:"+wxPrepay.getMsg()+"data:"+wxPrepay.getData().toString());
                        if (wxPrepay.getStatus().equals(Status.STATUS_GETUSERINFO_SUCCESS)) {
                            callback.getPrepayInfoSuccess(wxPrepay);
                        } else {
                            callback.getPrepayInfoFailed(wxPrepay.getMsg());
                        }
                    }
                });
    }
}
