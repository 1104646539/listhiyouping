package com.lishi.baijiaxing.orderAddressManage.model;

import com.lishi.baijiaxing.bean.DeliveryAddressBean;
import com.lishi.baijiaxing.home.model.AdList;
import com.lishi.baijiaxing.orderAddressManage.AddressCallback;
import com.lishi.baijiaxing.orderAddressManage.presenter.AddressPresenter;
import com.lishi.baijiaxing.orderAddressManage.presenter.EditAddressPresenter;

import java.util.ArrayList;

/**
 * 收货地址管理
 * Created by Administrator on 2016/8/22.
 */
public interface AddressModel {
    void loadAddressListData(AddressCallback callback);

    void changeAddress(AddressCallback callback, AddressList.DataBean dataBean);

    void deleteAddress(AddressCallback callback, AddressList.DataBean dataBean);

    void addAddress(AddressCallback callback, AddressList.DataBean dataBean);

}
