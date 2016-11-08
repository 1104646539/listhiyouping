package com.lishi.baijiaxing.orderAddressManage.presenter;

import com.lishi.baijiaxing.bean.DeliveryAddressBean;
import com.lishi.baijiaxing.orderAddressManage.model.AddressModelImpl;
import com.lishi.baijiaxing.orderAddressManage.view.AddressView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/22.
 */
public class AddressPresenterImpl implements AddressPresenter {
    private AddressModelImpl mAddressModel;
    private AddressView mAddressView;

    public AddressPresenterImpl(AddressView addressView) {
        this.mAddressView = addressView;
        mAddressModel = new AddressModelImpl();
    }

    @Override
    public void loadAddressData() {
        mAddressModel.loadAddressData(this);
    }

    @Override
    public void onLoadAddressDataSuccess(ArrayList<DeliveryAddressBean> deliveryAddressBeen) {
        mAddressView.onLoadAddressDataSuccess(deliveryAddressBeen);
    }

    @Override
    public void onLoadAddressDataFailed() {
        mAddressView.onLoadAddressDataFailed();
    }

    @Override
    public void changeAddress(ArrayList<DeliveryAddressBean> deliveryAddressBeens, int position, DeliveryAddressBean deliveryAddressBean) {
        mAddressModel.changeAddress(this, deliveryAddressBeens, position, deliveryAddressBean);
        mAddressView.changeAddress();
    }

    @Override
    public void deleteAddress(ArrayList<DeliveryAddressBean> deliveryAddressBeens, int position) {
        mAddressModel.deleteAddress(this, deliveryAddressBeens, position);
        mAddressView.deleteAddress();
    }

    @Override
    public void addAddress(ArrayList<DeliveryAddressBean> deliveryAddressBeens, DeliveryAddressBean deliveryAddressBean) {
        mAddressModel.addAddress(this, deliveryAddressBeens, deliveryAddressBean);
        mAddressView.addAddress();
    }

    @Override
    public void setDefault(ArrayList<DeliveryAddressBean> deliveryAddressBeens, int position,boolean isCheck) {
        mAddressModel.setDefault(this, deliveryAddressBeens, position,isCheck);
        mAddressView.setDefault();
    }

    @Override
    public void onChangeAddressSuccess(ArrayList<DeliveryAddressBean> deliveryAddressBeen) {
        mAddressView.onChangeAddressSuccess(deliveryAddressBeen);
    }


    @Override
    public void onChangeAddressFailed() {
        mAddressView.onChangeAddressFailed();
    }

    @Override
    public void onDeleteAddressSuccess(ArrayList<DeliveryAddressBean> deliveryAddressBeen) {
        mAddressView.onDeleteAddressSuccess(deliveryAddressBeen);
    }


    @Override
    public void onDeleteAddressFailed() {
        mAddressView.onDeleteAddressFailed();
    }

    @Override
    public void onAddAddressSuccess(ArrayList<DeliveryAddressBean> deliveryAddressBeen) {
        mAddressView.onAddAddressSuccess(deliveryAddressBeen);
    }

    @Override
    public void onAddAddressFailed() {
        mAddressView.onAddAddressFailed();
    }

    @Override
    public void onSetDefaultSuccess(ArrayList<DeliveryAddressBean> deliveryAddressBeen) {
        mAddressView.onSetDefaultSuccess(deliveryAddressBeen);
    }

    @Override
    public void onSetDefaultFailed() {
        mAddressView.onSetDefaultFailed();
    }


}
