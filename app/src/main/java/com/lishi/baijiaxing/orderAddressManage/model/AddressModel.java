package com.lishi.baijiaxing.orderAddressManage.model;

import com.lishi.baijiaxing.bean.DeliveryAddressBean;
import com.lishi.baijiaxing.orderAddressManage.presenter.AddressPresenter;
import com.lishi.baijiaxing.orderAddressManage.presenter.EditAddressPresenter;

import java.util.ArrayList;

/**
 * 收货地址管理
 * Created by Administrator on 2016/8/22.
 */
public interface AddressModel {
    void loadAddressData(AddressPresenter addressPresenter);

    void changeAddress(AddressPresenter addressPresenter, ArrayList<DeliveryAddressBean> deliveryAddressBeens, int position, DeliveryAddressBean deliveryAddressBean);

    void deleteAddress(AddressPresenter addressPresenter, ArrayList<DeliveryAddressBean> deliveryAddressBeens, int position);

    void addAddress(AddressPresenter addressPresenter, ArrayList<DeliveryAddressBean> deliveryAddressBeens, DeliveryAddressBean deliveryAddressBean);

    void setDefault(AddressPresenter addressPresenter,ArrayList<DeliveryAddressBean> deliveryAddressBeens, int position,boolean isCheck);
}
