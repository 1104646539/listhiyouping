package com.lishi.baijiaxing.orderAddressManage.model;

import com.lishi.baijiaxing.bean.DeliveryAddressBean;
import com.lishi.baijiaxing.orderAddressManage.AddressCallback;
import com.lishi.baijiaxing.orderAddressManage.EditAddressCallback;
import com.lishi.baijiaxing.orderAddressManage.presenter.AddressPresenter;
import com.lishi.baijiaxing.orderAddressManage.presenter.EditAddressPresenter;

/**
 * Created by Administrator on 2016/8/22.
 */
public interface EditAddressModel {
    void deleteAddress(EditAddressCallback callback, AddressList.DataBean dataBean);

    void changeAddress(EditAddressCallback callback, AddressList.DataBean dataBean);
}
