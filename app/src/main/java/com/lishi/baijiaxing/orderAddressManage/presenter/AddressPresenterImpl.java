package com.lishi.baijiaxing.orderAddressManage.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.bean.DeliveryAddressBean;
import com.lishi.baijiaxing.orderAddressManage.AddressCallback;
import com.lishi.baijiaxing.orderAddressManage.model.AddressList;
import com.lishi.baijiaxing.orderAddressManage.model.AddressModelImpl;
import com.lishi.baijiaxing.orderAddressManage.model.UpAddress;
import com.lishi.baijiaxing.orderAddressManage.view.AddressView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/22.
 */
public class AddressPresenterImpl extends BasePresenter implements AddressPresenter, AddressCallback {
    private AddressModelImpl mAddressModel;
    private AddressView mAddressView;

    public AddressPresenterImpl(BaseView addressView) {
        super(addressView);
        this.mAddressView = (AddressView) addressView;
        mAddressModel = new AddressModelImpl();
    }

    @Override
    public void loadAddressData() {
        mAddressView.showDialog();
        mAddressModel.loadAddressListData(this);
    }

    @Override
    public void changeAddress(AddressList.DataBean dataBean) {
        mAddressModel.changeAddress(this, dataBean);
    }

    @Override
    public void deleteAddress(AddressList.DataBean dataBean) {
        mAddressModel.deleteAddress(this, dataBean);
    }

    @Override
    public void addAddress(AddressList.DataBean dataBean) {
        mAddressModel.addAddress(this, dataBean);
    }

    @Override
    public void onLoadAddressListSuccess(AddressList addressList) {
        mAddressView.closeDialog();
        mAddressView.onLoadAddressDataSuccess(addressList);
    }

    @Override
    public void onLoadAddressListFailed(String error) {
        mAddressView.closeDialog();
        mAddressView.onLoadAddressDataFailed(error);
    }

    @Override
    public void onChangeAddressSuccess(UpAddress upAddress) {
        mAddressView.onChangeAddressSuccess(upAddress);
    }

    @Override
    public void onChangeAddressFailed(String error) {
        mAddressView.onChangeAddressFailed(error);
    }

    @Override
    public void onDeleteAddressSuccess(UpAddress upAddress) {
        mAddressView.onDeleteAddressSuccess(upAddress);
    }

    @Override
    public void onDeleteAddressFailed(String error) {
        mAddressView.onDeleteAddressFailed(error);
    }

    @Override
    public void onAddAddressSuccess(UpAddress upAddress) {
        mAddressView.onAddAddressSuccess(upAddress);
    }

    @Override
    public void onAddAddressFailed(String error) {
        mAddressView.onAddAddressFailed(error);
    }
}
