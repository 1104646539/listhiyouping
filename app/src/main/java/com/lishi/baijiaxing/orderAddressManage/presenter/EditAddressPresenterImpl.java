package com.lishi.baijiaxing.orderAddressManage.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.bean.DeliveryAddressBean;
import com.lishi.baijiaxing.orderAddressManage.EditAddressCallback;
import com.lishi.baijiaxing.orderAddressManage.model.AddressList;
import com.lishi.baijiaxing.orderAddressManage.model.EditAddressModelImpl;
import com.lishi.baijiaxing.orderAddressManage.model.UpAddress;
import com.lishi.baijiaxing.orderAddressManage.view.EditAddressView;

/**
 * 编辑收货地址
 * Created by Administrator on 2016/8/22.
 */
public class EditAddressPresenterImpl extends BasePresenter implements EditAddressPresenter, EditAddressCallback {

    private EditAddressModelImpl mEditAddressModel;
    private EditAddressView mEditAddressView;

    public EditAddressPresenterImpl(BaseView view) {
        super(view);
        mEditAddressView = (EditAddressView) view;
        mEditAddressModel = new EditAddressModelImpl();
    }

    @Override
    public void changeAddress(AddressList.DataBean dataBean) {
        mEditAddressModel.changeAddress(this, dataBean);
    }

    @Override
    public void deleteAddress(AddressList.DataBean dataBean) {
        mEditAddressView.showDialog();
        mEditAddressModel.deleteAddress(this, dataBean);
    }

    @Override
    public void addAddress(AddressList.DataBean dataBean) {

    }

    @Override
    public void onChangeAddressSuccess(UpAddress upAddress) {
        mEditAddressView.onChangeAddressSuccess(upAddress);
    }

    @Override
    public void onChangeAddressFailed(String error) {
        mEditAddressView.onChangeAddressFailed(error);
    }

    @Override
    public void onDeleteAddressSuccess(UpAddress upAddress) {
        mEditAddressView.onDeleteAddressSuccess(upAddress);
        mEditAddressView.closeDialog();
    }

    @Override
    public void onDeleteAddressFailed(String error) {
        mEditAddressView.onDeleteAddressFailed(error);
        mEditAddressView.closeDialog();
    }

    @Override
    public void onAddAddressSuccess(UpAddress upAddress) {

    }

    @Override
    public void onAddAddressFailed(String error) {

    }
}
