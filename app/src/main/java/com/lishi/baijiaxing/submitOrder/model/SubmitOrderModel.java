package com.lishi.baijiaxing.submitOrder.model;

import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.submitOrder.presenter.SubmitOrderPresenter;

import java.util.ArrayList;

/**提交订单
 * Created by Administrator on 2016/8/22.
 */
public interface SubmitOrderModel {
    void loadOrderData(SubmitOrderPresenter presenter, ArrayList<CommodityBean> storeBeen);
}
