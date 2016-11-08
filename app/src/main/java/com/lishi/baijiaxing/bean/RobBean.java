package com.lishi.baijiaxing.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;

/**
 * 秒杀
 * Created by Administrator on 2016/7/28.
 */
public class RobBean implements Parcelable {
    private boolean isRob;//是否是秒杀
    private boolean isavailable;//是否还有货
    private boolean isTop;//是否是热门
    private long price;//现价
    private long old_price;//原价
    private long number;//总数量
    private long sold_number;//已出售的数量
    private CommodityBean mCommodityBean;

    public RobBean(boolean isRob, boolean isavailable, boolean isTop, long price, long old_price, long number, long sold_number, CommodityBean commodityBean) {
        this.isRob = isRob;
        this.isavailable = isavailable;
        this.isTop = isTop;
        this.price = price;
        this.old_price = old_price;
        this.number = number;
        this.sold_number = sold_number;
        mCommodityBean = commodityBean;
    }

    public boolean isRob() {
        return isRob;
    }

    public void setRob(boolean rob) {
        isRob = rob;
    }

    public boolean isavailable() {
        return isavailable;
    }

    public void setIsavailable(boolean isavailable) {
        this.isavailable = isavailable;
    }

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getOld_price() {
        return old_price;
    }

    public void setOld_price(long old_price) {
        this.old_price = old_price;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public long getSold_number() {
        return sold_number;
    }

    public void setSold_number(long sold_number) {
        this.sold_number = sold_number;
    }

    public CommodityBean getCommodityBean() {
        return mCommodityBean;
    }

    public void setCommodityBean(CommodityBean commodityBean) {
        mCommodityBean = commodityBean;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isRob ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isavailable ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isTop ? (byte) 1 : (byte) 0);
        dest.writeLong(this.price);
        dest.writeLong(this.old_price);
        dest.writeLong(this.number);
        dest.writeLong(this.sold_number);
        dest.writeParcelable(this.mCommodityBean, flags);
    }

    protected RobBean(Parcel in) {
        this.isRob = in.readByte() != 0;
        this.isavailable = in.readByte() != 0;
        this.isTop = in.readByte() != 0;
        this.price = in.readLong();
        this.old_price = in.readLong();
        this.number = in.readLong();
        this.sold_number = in.readLong();
        this.mCommodityBean = in.readParcelable(CommodityBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<RobBean> CREATOR = new Parcelable.Creator<RobBean>() {
        @Override
        public RobBean createFromParcel(Parcel source) {
            return new RobBean(source);
        }

        @Override
        public RobBean[] newArray(int size) {
            return new RobBean[size];
        }
    };
}
