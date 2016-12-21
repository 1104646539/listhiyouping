package com.lishi.baijiaxing.orderAddressManage.model;

import android.util.Log;

import com.facebook.stetho.common.LogUtil;
import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.bean.DeliveryAddressBean;
import com.lishi.baijiaxing.orderAddressManage.AddressCallback;
import com.lishi.baijiaxing.orderAddressManage.EditAddressCallback;
import com.lishi.baijiaxing.orderAddressManage.network.EditAddressService;
import com.lishi.baijiaxing.orderAddressManage.presenter.AddressPresenter;
import com.lishi.baijiaxing.orderAddressManage.presenter.EditAddressPresenter;
import com.lishi.baijiaxing.utils.LoginUtil;
import com.lishi.baijiaxing.utils.Status;
import com.yuyh.library.imgsel.utils.LogUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 编辑收货地址
 * Created by Administrator on 2016/8/22.
 */
public class EditAddressModelImpl extends BaseModel implements EditAddressModel {
    private EditAddressService mEditAddressService;

    public EditAddressModelImpl() {
        mEditAddressService = (EditAddressService) getRetrofitManager().getHomeService(EditAddressService.class);
    }

    @Override
    public void deleteAddress(final EditAddressCallback callback, AddressList.DataBean dataBean) {
        mEditAddressService.deleteAddress("delAdd", LoginUtil.getInstance().getLogin().getData().getType(), LoginUtil.getInstance().getLogin().getData().getUid(),
                dataBean.getAid(), LoginUtil.getInstance().getLogin().getData().getToken()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpAddress>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("deleteAddress", "删除地址失败:" + e.toString());
                        callback.onDeleteAddressFailed(e.toString());
                    }

                    @Override
                    public void onNext(UpAddress upAddress) {
                        Log.i("deleteAddress", "删除地址成功:" + upAddress.getStatus() + "addressList.getData().size()" + upAddress.getMsg());
                        if (upAddress.getStatus().equals(Status.STATUS_OPERATION_SUCCESS)) {
                            callback.onDeleteAddressSuccess(upAddress);
                        } else {
                            callback.onDeleteAddressFailed(upAddress.getMsg());
                        }
                    }
                });
    }

    @Override
    public void changeAddress(final EditAddressCallback callback, AddressList.DataBean dataBean) {
        if (!LoginUtil.getInstance().isLogin()) {
            callback.onChangeAddressFailed("未登录");
            return;
        }

        mEditAddressService.changeAddress("upAdd", LoginUtil.getInstance().getLogin().getData().getType(), LoginUtil.getInstance().getLogin().getData().getUid()
                , LoginUtil.getInstance().getLogin().getData().getToken(), dataBean.getAid(), dataBean.getConsigneeName(), dataBean.getProvince(), dataBean.getCity()
                , dataBean.getDistrict(), dataBean.getDetails(), dataBean.getConsigneeNumber(), dataBean.getIsDefalut()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<UpAddress>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("onNext", "status:" + e.toString());
                callback.onChangeAddressFailed(e.toString());
            }

            @Override
            public void onNext(UpAddress upAddress) {
                Log.i("onNext", "status:" + upAddress.getStatus() + "msg:" + upAddress.getMsg());
                if (upAddress.getStatus().equals(Status.STATUS_OPERATION_SUCCESS)) {
                    callback.onChangeAddressSuccess(upAddress);
                } else {
                    callback.onChangeAddressFailed("操作失败");
                }
            }
        });
    }

}
