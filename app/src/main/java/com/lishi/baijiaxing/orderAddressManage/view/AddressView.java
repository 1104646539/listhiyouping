package com.lishi.baijiaxing.orderAddressManage.view;

import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.bean.DeliveryAddressBean;
import com.lishi.baijiaxing.orderAddressManage.model.AddressList;
import com.lishi.baijiaxing.orderAddressManage.model.UpAddress;

import java.util.ArrayList;

/**
 * 收货地址管理
 * Created by Administrator on 2016/8/22.
 */
public interface AddressView extends BaseView {
    void onLoadAddressDataSuccess(AddressList addressList);

    void onLoadAddressDataFailed(String error);

    void onChangeAddressSuccess(UpAddress upAddress);

    void onChangeAddressFailed(String error);

    void onDeleteAddressSuccess(UpAddress upAddress);

    void onDeleteAddressFailed(String error);

    void onAddAddressSuccess(UpAddress address);

    void onAddAddressFailed(String error);
}
