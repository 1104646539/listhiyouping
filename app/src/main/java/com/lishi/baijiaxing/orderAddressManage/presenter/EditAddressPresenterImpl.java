package com.lishi.baijiaxing.orderAddressManage.presenter;

import com.lishi.baijiaxing.bean.DeliveryAddressBean;
import com.lishi.baijiaxing.orderAddressManage.model.EditAddressModelImpl;
import com.lishi.baijiaxing.orderAddressManage.view.EditAddressView;

/**
 * 编辑收货地址
 * Created by Administrator on 2016/8/22.
 */
public class EditAddressPresenterImpl implements EditAddressPresenter {

    private EditAddressModelImpl mEditAddressModel;
    private EditAddressView mEditAddressView;


    public EditAddressPresenterImpl(EditAddressView editAddressView) {
        this.mEditAddressView = editAddressView;
        mEditAddressModel = new EditAddressModelImpl();
    }

    @Override
    public void changeAddress(DeliveryAddressBean deliveryAddressBean) {
        mEditAddressModel.changeAddress(this, deliveryAddressBean);
        mEditAddressView.changeAddress();
    }

    @Override
    public void deleteAddress(DeliveryAddressBean deliveryAddressBean) {
        mEditAddressModel.deleteAddress(this, deliveryAddressBean);
        mEditAddressView.deleteAddress();
    }

    @Override
    public void addAddress(DeliveryAddressBean deliveryAddressBean) {
        mEditAddressModel.addAddress(this, deliveryAddressBean);
        mEditAddressView.addAddress();
    }

    @Override
    public void onChangeAddressSuccess() {
        mEditAddressView.onChangeAddressSuccess();
    }

    @Override
    public void onChangeAddressFailed() {
        mEditAddressView.onChangeAddressFailed();
    }

    @Override
    public void onDeleteAddressSuccess() {
        mEditAddressView.onDeleteAddressSuccess();
    }

    @Override
    public void onDeleteAddressFailed() {
        mEditAddressView.onDeleteAddressFailed();
    }

    @Override
    public void onAddAddressSuccess() {
        mEditAddressView.onAddAddressSuccess();
    }

    @Override
    public void onAddAddressFailed() {
        mEditAddressView.onAddAddressFailed();
    }
}
