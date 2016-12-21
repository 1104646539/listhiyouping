package com.lishi.baijiaxing.orderAddressManage.view;

import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.orderAddressManage.model.UpAddress;

/**
 * 编辑收货地址
 * Created by Administrator on 2016/8/22.
 */
public interface EditAddressView extends BaseView {
    void onChangeAddressSuccess(UpAddress address);

    void onChangeAddressFailed(String error);

    void onDeleteAddressSuccess(UpAddress address);

    void onDeleteAddressFailed(String error);

    void onAddAddressSuccess(UpAddress address);

    void onAddAddressFailed(String error);

}
