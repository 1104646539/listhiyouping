package com.lishi.baijiaxing.orderAddressManage.presenter;

import com.lishi.baijiaxing.bean.DeliveryAddressBean;
import com.lishi.baijiaxing.orderAddressManage.model.AddressList;

/**
 * Created by Administrator on 2016/8/22.
 */
public interface EditAddressPresenter {
    void changeAddress(AddressList.DataBean dataBean);

    void deleteAddress(AddressList.DataBean dataBean);

    void addAddress(AddressList.DataBean dataBean);
}
