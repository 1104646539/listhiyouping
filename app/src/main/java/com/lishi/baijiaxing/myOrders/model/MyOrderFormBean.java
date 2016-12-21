package com.lishi.baijiaxing.myOrders.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.lishi.baijiaxing.shoppingCart.model.StoreBean;

/**
 * 我的订单实体类
 * Created by Administrator on 2016/8/4.
 */
public class MyOrderFormBean {
    // 0:未付款、1:已付款（未发货）、4:退换货、5:已发货(待收货)6:已完成（待评价，已收货）、7:取消订单    不输入为全部订单     9;//退款完成8;//完成交易
    public final static int STAYPAYMENT = 0;//待付款
    public final static int STAYSHIPMENTS = 1;//待发货
    public final static int YETSHIPMENTS = 5;//已发货
    public final static int STAYEVALUATE = 6;//待评价
    public final static int REFUNDFINISH = 9;//退款完成
    public final static int REFUNDIN = 4;//退款中
    public final static int DEALFINISH = 8;//完成交易
    public final static int CANCELORDER = 7;//取消订单
    public final static int DETELEORDER = 10;//删除订单

    private int state;//状态
}
