package com.lishi.baijiaxing.myOrders.presenter;

import com.lishi.baijiaxing.myOrders.model.MyOrderFormBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/19.
 */
public interface OrdersPresenter {
    void LoadData(String url);

    void onSuccess(ArrayList<MyOrderFormBean> myOrderFormBeen);

    void onFailed();


}
