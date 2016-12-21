package com.lishi.baijiaxing.submitOrder.view;

import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.bean.CouponsBean;
import com.lishi.baijiaxing.bean.DeliveryAddressBean;
import com.lishi.baijiaxing.shoppingCart.model.ShoppingListBean;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrder;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrderBean;
import com.lishi.baijiaxing.submitOrder.model.WriteOrder;

import java.util.ArrayList;

/**
 * 提交订单
 * Created by Administrator on 2016/8/22.
 */
public interface SubmitOrderView extends BaseView {

    void onLoadOrderDataSuccess(WriteOrder writeOrder);

    void onLoadOrderDataFailed(String error);

    void onSubmitOrderSuccess(SubmitOrder submitOrder);

    void onSubmitOrderFailed(String error);
}
