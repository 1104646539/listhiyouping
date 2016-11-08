package com.lishi.baijiaxing.orderAddressManage.presenter;

import com.lishi.baijiaxing.bean.DeliveryAddressBean;

/**
 * Created by Administrator on 2016/8/22.
 */
public interface EditAddressPresenter {
    void changeAddress(DeliveryAddressBean deliveryAddressBean);

    void deleteAddress(DeliveryAddressBean deliveryAddressBean);

    void addAddress(DeliveryAddressBean deliveryAddressBean);

    void onChangeAddressSuccess();

    void onChangeAddressFailed();

    void onDeleteAddressSuccess();

    void onDeleteAddressFailed();

    void onAddAddressSuccess();

    void onAddAddressFailed();
}
