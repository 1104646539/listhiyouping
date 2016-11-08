package com.lishi.baijiaxing.orderAddressManage.view;

/**编辑收货地址
 * Created by Administrator on 2016/8/22.
 */
public interface EditAddressView {
    void changeAddress();

    void deleteAddress();

    void addAddress();

    void onChangeAddressSuccess();

    void onChangeAddressFailed();

    void onDeleteAddressSuccess();

    void onDeleteAddressFailed();

    void onAddAddressSuccess();

    void onAddAddressFailed();

}
