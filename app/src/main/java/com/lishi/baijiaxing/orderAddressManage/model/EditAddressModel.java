package com.lishi.baijiaxing.orderAddressManage.model;

import com.lishi.baijiaxing.bean.DeliveryAddressBean;
import com.lishi.baijiaxing.orderAddressManage.presenter.AddressPresenter;
import com.lishi.baijiaxing.orderAddressManage.presenter.EditAddressPresenter;

/**
 * Created by Administrator on 2016/8/22.
 */
public interface EditAddressModel {
    void changeAddress(EditAddressPresenter addressPresenter, DeliveryAddressBean deliveryAddressBean);

    void deleteAddress(EditAddressPresenter addressPresenter, DeliveryAddressBean deliveryAddressBean);

    void addAddress(EditAddressPresenter addressPresenter, DeliveryAddressBean deliveryAddressBean);
}
