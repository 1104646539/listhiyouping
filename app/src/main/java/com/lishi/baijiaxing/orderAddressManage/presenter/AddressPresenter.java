package com.lishi.baijiaxing.orderAddressManage.presenter;

import com.lishi.baijiaxing.bean.DeliveryAddressBean;
import com.lishi.baijiaxing.orderAddressManage.model.AddressList;

import java.util.ArrayList;

/**
 * 收货地址管理
 * Created by Administrator on 2016/8/22.
 */
public interface AddressPresenter {
    void loadAddressData();

    void changeAddress(AddressList.DataBean dataBean);

    void deleteAddress(AddressList.DataBean dataBean);

    void addAddress(AddressList.DataBean dataBean);
}
