package com.lishi.baijiaxing.orderAddressManage.model;

import android.util.Log;

import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.bean.DeliveryAddressBean;
import com.lishi.baijiaxing.classify.model.ClassOne;
import com.lishi.baijiaxing.orderAddressManage.AddressCallback;
import com.lishi.baijiaxing.orderAddressManage.network.AddressService;
import com.lishi.baijiaxing.orderAddressManage.presenter.AddressPresenter;
import com.lishi.baijiaxing.utils.LoginUtil;
import com.lishi.baijiaxing.utils.Status;
import com.lishi.baijiaxing.utils.UserUtil;
import com.lishi.baijiaxing.wxapi.model.Login;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/8/22.
 */
public class AddressModelImpl extends BaseModel implements AddressModel {
    private AddressService mAddressServicel;

    public AddressModelImpl() {
        mAddressServicel = (AddressService) getRetrofitManager().getHomeService(AddressService.class);
    }

    @Override
    public void loadAddressListData(final AddressCallback callback) {
        if (!LoginUtil.getInstance().isLogin()) {
            callback.onChangeAddressFailed("未登录");
            return;
        }
        Log.i("发送过去的", "uid:" + LoginUtil.getInstance().getLogin().getData().getUid() + "token:" + LoginUtil.getInstance().getLogin().getData().getToken()
                + "type" + LoginUtil.getInstance().getLogin().getData().getType());
        mAddressServicel.getAddressList("addList", LoginUtil.getInstance().getLogin().getData().getUid(), LoginUtil.getInstance().getLogin().getData().getToken(),
                LoginUtil.getInstance().getLogin().getData().getType()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AddressList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("loadAddressListData", "获取收货地址失败:" + e.toString());
                        callback.onLoadAddressListFailed(e.toString());
                    }

                    @Override
                    public void onNext(AddressList addressList) {
                        Log.i("loadAddressListData", "获取收货地址成功:" + addressList.getStatus() + "addressList.getData().size()" + addressList.getData().size());
                        if (addressList.getStatus().equals(Status.STATUS_GETUSERINFO_SUCCESS)) {
                            callback.onLoadAddressListSuccess(addressList);
                        } else {
                            callback.onLoadAddressListFailed(addressList.getMsg());
                        }
                    }
                });
    }

    @Override
    public void changeAddress(final AddressCallback callback, AddressList.DataBean dataBean) {
        if (!LoginUtil.getInstance().isLogin()) {
            callback.onChangeAddressFailed("未登录");
            return;
        }

        mAddressServicel.changeAddress("upAdd", LoginUtil.getInstance().getLogin().getData().getType(), LoginUtil.getInstance().getLogin().getData().getUid()
                , LoginUtil.getInstance().getLogin().getData().getToken(), dataBean.getAid(), dataBean.getConsigneeName(), dataBean.getProvince(), dataBean.getCity()
                , dataBean.getDistrict(), dataBean.getDetails(), dataBean.getConsigneeNumber(), dataBean.getIsDefalut()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<UpAddress>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("onNext", "status:" + e.toString());
                callback.onAddAddressFailed(e.toString());
            }

            @Override
            public void onNext(UpAddress upAddress) {
                Log.i("onNext", "status:" + upAddress.getStatus() + "msg:" + upAddress.getMsg());
                if (upAddress.getStatus().equals(Status.STATUS_OPERATION_SUCCESS)) {
                    callback.onAddAddressSuccess(upAddress);
                } else {
                    callback.onAddAddressFailed("操作失败");
                }
            }
        });
    }

    @Override
    public void deleteAddress(AddressCallback callback, AddressList.DataBean dataBean) {

    }

    @Override
    public void addAddress(final AddressCallback callback, AddressList.DataBean dataBean) {
        if (!LoginUtil.getInstance().isLogin()) {
            callback.onChangeAddressFailed("未登录");
            return;
        }
        Log.i("addAddress", LoginUtil.getInstance().getLogin().getData().getType() + LoginUtil.getInstance().getLogin().getData().getUid()
                + LoginUtil.getInstance().getLogin().getData().getToken() + dataBean.getConsigneeName() + dataBean.getProvince() + dataBean.getCity()
                + dataBean.getDistrict() + dataBean.getDetails() + dataBean.getConsigneeNumber() + dataBean.getIsDefalut());
        mAddressServicel.addAddress("inAdd", LoginUtil.getInstance().getLogin().getData().getType(), LoginUtil.getInstance().getLogin().getData().getUid()
                , LoginUtil.getInstance().getLogin().getData().getToken(), dataBean.getConsigneeName(), dataBean.getProvince(), dataBean.getCity()
                , dataBean.getDistrict(), dataBean.getDetails(), dataBean.getConsigneeNumber(), dataBean.getIsDefalut()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<UpAddress>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("onNext", "status:" + e.toString());
            }

            @Override
            public void onNext(UpAddress upAddress) {
                Log.i("onNext", "status:" + upAddress.getStatus() + "msg:" + upAddress.getMsg());
                if (upAddress.getStatus().equals(Status.STATUS_OPERATION_SUCCESS)) {
                    callback.onAddAddressSuccess(upAddress);
                } else {
                    callback.onAddAddressFailed("操作失败");
                }
            }
        });
    }
}
