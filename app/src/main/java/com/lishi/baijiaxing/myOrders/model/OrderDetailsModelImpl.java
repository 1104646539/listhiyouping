package com.lishi.baijiaxing.myOrders.model;

import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.myOrders.OrderDetailsCallback;
import com.lishi.baijiaxing.myOrders.network.MyOrderService;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;
import com.lishi.baijiaxing.utils.LoginUtil;
import com.lishi.baijiaxing.utils.Status;
import com.orhanobut.logger.Logger;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/13.
 */

public class OrderDetailsModelImpl extends BaseModel implements OrderDetailsModel {
    private MyOrderService mMyOrderService;

    public OrderDetailsModelImpl() {
        mMyOrderService = (MyOrderService) getRetrofitManager().getHomeService(MyOrderService.class);
    }

    @Override
    public void loadOrderDetails(final OrderDetailsCallback callback, String oid) {
        if (!LoginUtil.getInstance().isLogin()) {
            callback.loadOrderDetailsFailed("未登录");
            return;
        }

        mMyOrderService.getOrderDetails(LoginUtil.getInstance().getLogin().getData().getUid(), LoginUtil.getInstance().getLogin().getData().getToken()
                , LoginUtil.getInstance().getLogin().getData().getType(), oid).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderDetails>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.loadOrderDetailsFailed(e.toString());
                    }

                    @Override
                    public void onNext(OrderDetails orderDetails) {
                        if (orderDetails.getStatus().equals(Status.STATUS_GETUSERINFO_SUCCESS)) {
                            callback.loadOrderDetailsSuccess(orderDetails);
                        } else {
                            callback.loadOrderDetailsFailed(orderDetails.getMsg());
                        }
                    }
                });

    }

    @Override
    public void changeOrderStatus(final OrderDetailsCallback callback, String orderNumber, String status) {
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
