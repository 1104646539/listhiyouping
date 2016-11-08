package com.lishi.baijiaxing.submitOrder.view;

import com.lishi.baijiaxing.bean.CouponsBean;
import com.lishi.baijiaxing.bean.DeliveryAddressBean;
import com.lishi.baijiaxing.shoppingCart.model.ShoppingListBean;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrderBean;

import java.util.ArrayList;

/**提交订单
 * Created by Administrator on 2016/8/22.
 */
public interface SubmitOrderView {
    void loadOrderData();

    void onLoadOrderDataSuccess(SubmitOrderBean submitOrderBean);

    void onLoadOrderDataFailed();
}
