package com.lishi.baijiaxing.orderAddressManage.presenter;

import com.lishi.baijiaxing.bean.DeliveryAddressBean;

import java.util.ArrayList;

/**
 * 收货地址管理
 * Created by Administrator on 2016/8/22.
 */
public interface AddressPresenter {
    void loadAddressData();

    void onLoadAddressDataSuccess(ArrayList<DeliveryAddressBean> deliveryAddressBeen);

    void onLoadAddressDataFailed();

    void changeAddress(ArrayList<DeliveryAddressBean> deliveryAddressBeens, int position, DeliveryAddressBean deliveryAddressBean);

    void deleteAddress(ArrayList<DeliveryAddressBean> deliveryAddressBeens, int position);

    void addAddress(ArrayList<DeliveryAddressBean> deliveryAddressBeens, DeliveryAddressBean deliveryAddressBean);

    void setDefault(ArrayList<DeliveryAddressBean> deliveryAddressBeens, int position,boolean isCheck);

    void onChangeAddressSuccess(ArrayList<DeliveryAddressBean> deliveryAddressBeen);

    void onChangeAddressFailed();

    void onDeleteAddressSuccess(ArrayList<DeliveryAddressBean> deliveryAddressBeen);

    void onDeleteAddressFailed();

    void onAddAddressSuccess(ArrayList<DeliveryAddressBean> deliveryAddressBeen);

    void onAddAddressFailed();

    void onSetDefaultSuccess(ArrayList<DeliveryAddressBean> deliveryAddressBeen);

    void onSetDefaultFailed();

}
