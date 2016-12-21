package com.lishi.baijiaxing.submitOrder.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.lishi.baijiaxing.base.BaseBean;
import com.lishi.baijiaxing.bean.CouponsBean;
import com.lishi.baijiaxing.bean.DeliveryAddressBean;
import com.lishi.baijiaxing.home.model.Commodity;
import com.lishi.baijiaxing.orderAddressManage.model.AddressList;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.shoppingCart.model.SCCommodityList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 */
public class SubmitOrderBean extends BaseBean implements Parcelable {
    /**
     * 收货地址
     */
    AddressList.DataBean mDeliveryAddressBean;
    /**
     * 购买商品列表
     */
    List<SCCommodityList.DataBean> mDataBeen;

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
    String coupons;
    /**
     * 积分
     */
    String integral;
    /**
     * 运费
     */
    String freight;
    /**
     * 留言
     */
    String leaveMsg = "";

    String mFrom = "0";
    String zid;
    String gid;

    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getFrom() {
        return mFrom;
    }

    public void setFrom(String from) {
        mFrom = from;
    }

    public String getLeaveMsg() {
        return leaveMsg;
    }

    public void setLeaveMsg(String leaveMsg) {
        this.leaveMsg = leaveMsg;
    }

    public static Creator<SubmitOrderBean> getCREATOR() {
        return CREATOR;
    }

    public List<SCCommodityList.DataBean> getDataBeen() {
        return mDataBeen;
    }

    public void setDataBeen(List<SCCommodityList.DataBean> dataBeen) {
        mDataBeen = dataBeen;
    }

    public SubmitOrderBean() {

    }

    public AddressList.DataBean getDeliveryAddressBean() {
        return mDeliveryAddressBean;
    }

    public void setDeliveryAddressBean(AddressList.DataBean deliveryAddressBean) {
        mDeliveryAddressBean = deliveryAddressBean;
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

    public String getCoupons() {
        return coupons;
    }

    public void setCoupons(String coupons) {
        this.coupons = coupons;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mDeliveryAddressBean, flags);
        dest.writeTypedList(this.mDataBeen);
        dest.writeString(this.deliveryMethod);
        dest.writeString(this.bill);
        dest.writeString(this.coupons);
        dest.writeString(this.integral);
        dest.writeString(this.freight);
        dest.writeString(this.leaveMsg);
    }

    protected SubmitOrderBean(Parcel in) {
        this.mDeliveryAddressBean = in.readParcelable(AddressList.DataBean.class.getClassLoader());
        this.mDataBeen = in.createTypedArrayList(SCCommodityList.DataBean.CREATOR);
        this.deliveryMethod = in.readString();
        this.bill = in.readString();
        this.coupons = in.readString();
        this.integral = in.readString();
        this.freight = in.readString();
        this.leaveMsg = in.readString();
    }

    public static final Creator<SubmitOrderBean> CREATOR = new Creator<SubmitOrderBean>() {
        @Override
        public SubmitOrderBean createFromParcel(Parcel source) {
            return new SubmitOrderBean(source);
        }

        @Override
        public SubmitOrderBean[] newArray(int size) {
            return new SubmitOrderBean[size];
        }
    };
}
