package com.lishi.baijiaxing.orderAddressManage;

import com.lishi.baijiaxing.bean.DeliveryAddressBean;
import com.lishi.baijiaxing.orderAddressManage.model.AddressList;
import com.lishi.baijiaxing.orderAddressManage.model.UpAddress;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/2.
 */

public interface AddressCallback {

    void onLoadAddressListSuccess(AddressList addressList);

    void onLoadAddressListFailed(String error);

    void onChangeAddressSuccess(UpAddress upAddress);

    void onChangeAddressFailed(String error);

    void onDeleteAddressSuccess(UpAddress upAddress);

    void onDeleteAddressFailed(String error);

    void onAddAddressSuccess(UpAddress upAddress );

    void onAddAddressFailed(String error);
}
