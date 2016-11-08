package com.lishi.baijiaxing.orderAddressManage.view;

import com.lishi.baijiaxing.bean.DeliveryAddressBean;

import java.util.ArrayList;

/**
 * 收货地址管理
 * Created by Administrator on 2016/8/22.
 */
public interface AddressView {
    void loadAddressData();

    void onLoadAddressDataSuccess(ArrayList<DeliveryAddressBean> deliveryAddressBeen);

    void onLoadAddressDataFailed();

    void changeAddress();

    void deleteAddress();

    void addAddress();

    void setDefault();

    void onChangeAddressSuccess(ArrayList<DeliveryAddressBean> deliveryAddressBeen);

    void onChangeAddressFailed();

    void onDeleteAddressSuccess(ArrayList<DeliveryAddressBean> deliveryAddressBeen);

    void onDeleteAddressFailed();

    void onAddAddressSuccess(ArrayList<DeliveryAddressBean> deliveryAddressBeen);

    void onAddAddressFailed();

    void onSetDefaultSuccess(ArrayList<DeliveryAddressBean> deliveryAddressBeen);

    void onSetDefaultFailed();

}
