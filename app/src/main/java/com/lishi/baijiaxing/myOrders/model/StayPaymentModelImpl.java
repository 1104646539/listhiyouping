package com.lishi.baijiaxing.myOrders.model;

import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.myOrders.StayEvaluateCallback;
import com.lishi.baijiaxing.myOrders.StayPaymentCallback;
import com.lishi.baijiaxing.myOrders.network.MyOrderService;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;
import com.lishi.baijiaxing.utils.LoginUtil;
import com.lishi.baijiaxing.utils.Status;
import com.orhanobut.logger.Logger;
import com.tencent.open.weiyun.IDownLoadFileCallBack;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 代付款
 * Created by Administrator on 2016/12/12.
 */

public class StayPaymentModelImpl extends BaseModel implements StayPaymentModel {
    public MyOrderService mMyOrderService;
    private int mPage = 0;
    private boolean isFirst = false;

    public StayPaymentModelImpl() {
        mMyOrderService = (MyOrderService) getRetrofitManager().getHomeService(MyOrderService.class);
    }

    @Override
    public void loadOrderList(final StayPaymentCallback callback, final int page) {
        if (!LoginUtil.getInstance().isLogin()) {
            callback.loadOrderListFailed("未登录");
            return;
        }
        final String sPage;
        if (page == -1) {
            sPage = mPage + 1 + "";//上拉加载
        } else if (page == 0) {
            sPage = mPage + "";//刷新(延续上次的页数)
        } else {
            sPage = "1";////第一次加载，刷新
            isFirst =true;
        }
        Logger.d("spage:"+sPage);
        mMyOrderService.getMyOrderList(LoginUtil.getInstance().getLogin().getData().getUid(), LoginUtil.getInstance().getLogin().getData().getToken()
                , LoginUtil.getInstance().getLogin().getData().getType(), "1",sPage, "0").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyOrderList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.loadOrderListFailed(e.toString());
                        Logger.d(e.toString());
                    }

                    @Override
                    public void onNext(MyOrderList orderList) {
                        if (orderList.getStatus().equals(Status.STATUS_GETUSERINFO_SUCCESS)) {
                            if (page == -1||isFirst) {if (orderList.getData().getPageNum().equals(String.valueOf(mPage))) {
                                isFirst = false;
                            }else {
                                mPage= mPage+1;
                                isFirst = false;
                            }}
                            callback.loadOrderListSuccess(orderList);
                            if (orderList.getData().getPage().equals(orderList.getData().getPageNum())) {
                                callback.onLastPage(sPage);
                            }
                        } else {
                            callback.loadOrderListFailed(orderList.getMsg());
                        }
                    }
                });
    }

    @Override
    public void changeOrderStatus(final StayPaymentCallback callback, String orderNumber, final String status) {
        if (!LoginUtil.getInstance().isLogin()) {
            callback.changeOrderStatusFailed("未登录");
            return;
        }
        mMyOrderService.changeOrderStatus(LoginUtil.getInstance().getLogin().getData().getUid(), LoginUtil.getInstance().getLogin().getData().getToken()
                , LoginUtil.getInstance().getLogin().getData().getType(), orderNumber, status).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<SCOperation>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.changeOrderStatusFailed(e.toString());
                Logger.d(e.toString());
            }

            @Override
            public void onNext(SCOperation scOperation) {
                Logger.d(scOperation.getStatus() + scOperation.getMsg());
                if (scOperation.getStatus().equals("204")) {
                    callback.changeOrderStatusSuccess(scOperation);
                } else {
                    callback.changeOrderStatusFailed(scOperation.getMsg());
                }
            }
        });
    }
}
