package com.lishi.baijiaxing.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 收货地址
 * Created by Administrator on 2016/7/18.
 */
public class DeliveryAddressBean implements Parcelable {
    private String name;//收货人姓名
    private String number;//电话
    private String province;//省
    private String city;//市
    private String area;//区
    private String specific;//具体地址
    private boolean isChecked;//是否是默认收货地址

    public DeliveryAddressBean(String name, String number, String province, String city, String area, String specific, boolean isChecked) {
        this.name = name;
        this.number = number;
        this.province = province;
        this.city = city;
        this.area = area;
        this.specific = specific;
        this.isChecked = isChecked;
    }
    public DeliveryAddressBean(){
        
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSpecific() {
        return specific;
    }

    public void setSpecific(String specific) {
        this.specific = specific;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return province+"  "+city+"  "+area+"  "+specific;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.number);
        dest.writeString(this.province);
        dest.writeString(this.city);
        dest.writeString(this.area);
        dest.writeString(this.specific);
        dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
    }

    protected DeliveryAddressBean(Parcel in) {
        this.name = in.readString();
        this.number = in.readString();
        this.province = in.readString();
        this.city = in.readString();
        this.area = in.readString();
        this.specific = in.readString();
        this.isChecked = in.readByte() != 0;
    }

    public static final Creator<DeliveryAddressBean> CREATOR = new Creator<DeliveryAddressBean>() {
        @Override
        public DeliveryAddressBean createFromParcel(Parcel source) {
            return new DeliveryAddressBean(source);
        }

        @Override
        public DeliveryAddressBean[] newArray(int size) {
            return new DeliveryAddressBean[size];
        }
    };
}
