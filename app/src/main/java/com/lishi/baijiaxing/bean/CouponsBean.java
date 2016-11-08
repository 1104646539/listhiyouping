package com.lishi.baijiaxing.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**优惠券
 * Created by Administrator on 2016/7/21.
 */
public class CouponsBean implements Parcelable {
    private String date;//有效期
    private int money;//优惠金额
    private String astrict_local;//优惠限制地方
    private int astrict_money;//优惠限制金额
    private boolean isAvailable;//是否在有效期内

    public CouponsBean(String date, int money, String astrict_local, int astrict_money, boolean isAvailable) {
        this.date = date;
        this.money = money;
        this.astrict_local = astrict_local;
        this.isAvailable = isAvailable;
    }

    public String getDate() {
        return date;
    }

    public int getAstrict_money() {
        return astrict_money;
    }

    public void setAstrict_money(int astrict_money) {
        this.astrict_money = astrict_money;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getAstrict_local() {
        return astrict_local;
    }

    public void setAstrict_local(String astrict_local) {
        this.astrict_local = astrict_local;
    }


    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeInt(this.money);
        dest.writeString(this.astrict_local);
        dest.writeInt(this.astrict_money);
        dest.writeByte(this.isAvailable ? (byte) 1 : (byte) 0);
    }

    protected CouponsBean(Parcel in) {
        this.date = in.readString();
        this.money = in.readInt();
        this.astrict_local = in.readString();
        this.astrict_money = in.readInt();
        this.isAvailable = in.readByte() != 0;
    }

    public static final Parcelable.Creator<CouponsBean> CREATOR = new Parcelable.Creator<CouponsBean>() {
        @Override
        public CouponsBean createFromParcel(Parcel source) {
            return new CouponsBean(source);
        }

        @Override
        public CouponsBean[] newArray(int size) {
            return new CouponsBean[size];
        }
    };
}
