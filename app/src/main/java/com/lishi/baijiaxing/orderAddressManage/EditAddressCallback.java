package com.lishi.baijiaxing.orderAddressManage;

import com.lishi.baijiaxing.orderAddressManage.model.UpAddress;

/**
 * Created by Administrator on 2016/12/2.
 */

public interface EditAddressCallback {
    void onChangeAddressSuccess(UpAddress upAddress);

    void onChangeAddressFailed(String error);

    void onDeleteAddressSuccess(UpAddress upAddress);

    void onDeleteAddressFailed(String error);

    void onAddAddressSuccess(UpAddress upAddress);

    void onAddAddressFailed(String error);
}
