package com.lishi.baijiaxing.orderAddressManage.model;

import com.lishi.baijiaxing.bean.DeliveryAddressBean;
import com.lishi.baijiaxing.orderAddressManage.presenter.AddressPresenter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/22.
 */
public class AddressModelImpl implements AddressModel {
    @Override
    public void loadAddressData(AddressPresenter addressPresenter) {
        ArrayList<DeliveryAddressBean> mDeliveryAddressBeans = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            DeliveryAddressBean d = new DeliveryAddressBean("王尼玛", "18888886666", "广东省", "深圳市", "宝安区", "宝安中心1栋822室宝安中心1栋822室宝安中心1栋822室宝安中心1栋822室", false);
            if (i == 1) {
                d.setChecked(true);
            }
            mDeliveryAddressBeans.add(d);
        }
        addressPresenter.onLoadAddressDataSuccess(mDeliveryAddressBeans);
    }

    @Override
    public void changeAddress(AddressPresenter addressPresenter, ArrayList<DeliveryAddressBean> deliveryAddressBeens, int position, DeliveryAddressBean deliveryAddressBean) {
        deliveryAddressBeens.set(position, deliveryAddressBean);
        addressPresenter.onChangeAddressSuccess(deliveryAddressBeens);
    }

    @Override
    public void deleteAddress(AddressPresenter addressPresenter, ArrayList<DeliveryAddressBean> deliveryAddressBeens, int position) {
        deliveryAddressBeens.remove(position);
        addressPresenter.onDeleteAddressSuccess(deliveryAddressBeens);
    }

    @Override
    public void addAddress(AddressPresenter addressPresenter, ArrayList<DeliveryAddressBean> deliveryAddressBeens, DeliveryAddressBean deliveryAddressBean) {
        deliveryAddressBeens.add(deliveryAddressBean);
        addressPresenter.onAddAddressSuccess(deliveryAddressBeens);
    }


    @Override
    public void setDefault(AddressPresenter addressPresenter, ArrayList<DeliveryAddressBean> deliveryAddressBeens, int position, boolean isCheck) {

        for (int i = 0; i < deliveryAddressBeens.size(); i++) {
            if (isCheck) {
                if (i == position) {
                    deliveryAddressBeens.get(position).setChecked(true);
                } else {
                    deliveryAddressBeens.get(i).setChecked(false);
                }
            } else {
                if (i == position) {
                    deliveryAddressBeens.get(position).setChecked(false);
                } else {
                    
                }
            }
        }
        addressPresenter.onSetDefaultSuccess(deliveryAddressBeens);
    }
}
