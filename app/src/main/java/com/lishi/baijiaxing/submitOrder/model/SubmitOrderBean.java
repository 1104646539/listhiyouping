package com.lishi.baijiaxing.submitOrder.model;

import com.lishi.baijiaxing.base.BaseBean;
import com.lishi.baijiaxing.bean.CouponsBean;
import com.lishi.baijiaxing.bean.DeliveryAddressBean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 */
public class SubmitOrderBean extends BaseBean {
    /**
     * 收货地址
     */
    DeliveryAddressBean mDeliveryAddressBean;
    /**
     * 购买商品列表
     */
    List<SubmitOrderCommodityBean> mSubmitOrderCommodityBeen;

    /**
     * 配送方式
     */
    String deliveryMethod;
    /**
     * 发票信息
     */
    String bill;

    /**
     * 优惠券
     */
    CouponsBean coupons;
    /**
     * 积分
     */
    int integral;
    /**
     * 运费
     */
    int freight;

    public SubmitOrderBean() {

    }

    public SubmitOrderBean(DeliveryAddressBean deliveryAddressBean, List<SubmitOrderCommodityBean> submitOrderCommodityBeen, String deliveryMethod, String bill, CouponsBean coupons, int integral, int freight) {
        mDeliveryAddressBean = deliveryAddressBean;
        mSubmitOrderCommodityBeen = submitOrderCommodityBeen;
        this.deliveryMethod = deliveryMethod;
        this.bill = bill;
        this.coupons = coupons;
        this.integral = integral;
        this.freight = freight;
    }

    public DeliveryAddressBean getDeliveryAddressBean() {
        return mDeliveryAddressBean;
    }

    public void setDeliveryAddressBean(DeliveryAddressBean deliveryAddressBean) {
        mDeliveryAddressBean = deliveryAddressBean;
    }

    public List<SubmitOrderCommodityBean> getSubmitOrderCommodityBeen() {
        return mSubmitOrderCommodityBeen;
    }

    public void setSubmitOrderCommodityBeen(List<SubmitOrderCommodityBean> submitOrderCommodityBeen) {
        mSubmitOrderCommodityBeen = submitOrderCommodityBeen;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public CouponsBean getCoupons() {
        return coupons;
    }

    public void setCoupons(CouponsBean coupons) {
        this.coupons = coupons;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public int getFreight() {
        return freight;
    }

    public void setFreight(int freight) {
        this.freight = freight;
    }
}
