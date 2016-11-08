package com.lishi.baijiaxing.myOrders.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.lishi.baijiaxing.shoppingCart.model.StoreBean;

/**
 * 我的订单实体类
 * Created by Administrator on 2016/8/4.
 */
public class MyOrderFormBean implements Parcelable {
    public final static int STAYPAYMENT = 0;//待付款
    public final static int STAYSHIPMENTS = 1;//待发货
    public final static int YETSHIPMENTS = 2;//已发货
    public final static int STAYEVALUATE = 3;//待评价
    public final static int REFUNDFINISH = 4;//退款完成
    public final static int REFUNDIN = 5;//退款中
    public final static int DEALFINISH = 6;//完成交易

    private int state;//状态
    private StoreBean mStoreBean;
    private int number;//总数量
    private double totalPrice;//总价

    public MyOrderFormBean(int state, StoreBean storeBean, int number, double totalPrice) {
        this.state = state;
        mStoreBean = storeBean;
        this.number = number;
        this.totalPrice = totalPrice;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public StoreBean getStoreBean() {
        return mStoreBean;
    }

    public void setStoreBean(StoreBean storeBean) {
        mStoreBean = storeBean;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.state);
        dest.writeParcelable(this.mStoreBean, flags);
        dest.writeInt(this.number);
        dest.writeDouble(this.totalPrice);
    }

    protected MyOrderFormBean(Parcel in) {
        this.state = in.readInt();
        this.mStoreBean = in.readParcelable(StoreBean.class.getClassLoader());
        this.number = in.readInt();
        this.totalPrice = in.readInt();
    }

    public static final Parcelable.Creator<MyOrderFormBean> CREATOR = new Parcelable.Creator<MyOrderFormBean>() {
        @Override
        public MyOrderFormBean createFromParcel(Parcel source) {
            return new MyOrderFormBean(source);
        }

        @Override
        public MyOrderFormBean[] newArray(int size) {
            return new MyOrderFormBean[size];
        }
    };
}
