package com.lishi.baijiaxing.shoppingCart.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车商店类
 * Created by Administrator on 2016/6/14.
 */
public class StoreBean implements Parcelable {
    private boolean isChecked = false;//是否被选中
    private int storeId;//商家唯一id
    private String commStore;//商品店家
    private String commStoreImg;//商品店家图标
    private List<CommodityBean> mCommodityBeanList;//同一个店家的商品

    public StoreBean() {

    }

    public StoreBean(boolean isChecked, int storeId, String commStore, String commStoreImg, List<CommodityBean> commodityBeanList) {
        this.isChecked = isChecked;
        this.storeId = storeId;
        this.commStore = commStore;
        this.commStoreImg = commStoreImg;
        mCommodityBeanList = commodityBeanList;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getCommId() {
        return storeId;
    }

    public void setCommId(int storeId) {
        this.storeId = storeId;
    }

    public String getCommStore() {
        return commStore;
    }

    public void setCommStore(String commStore) {
        this.commStore = commStore;
    }

    public String getCommStoreImg() {
        return commStoreImg;
    }

    public void setCommStoreImg(String commStoreImg) {
        this.commStoreImg = commStoreImg;
    }

    public List<CommodityBean> getCommodityBeanList() {
        return mCommodityBeanList;
    }

    public void setCommodityBeanList(List<CommodityBean> commodityBeanList) {
        mCommodityBeanList = commodityBeanList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
        dest.writeInt(this.storeId);
        dest.writeString(this.commStore);
        dest.writeString(this.commStoreImg);
        dest.writeList(this.mCommodityBeanList);
    }

    protected StoreBean(Parcel in) {
        this.isChecked = in.readByte() != 0;
        this.storeId = in.readInt();
        this.commStore = in.readString();
        this.commStoreImg = in.readString();
        this.mCommodityBeanList = new ArrayList<CommodityBean>();
        in.readList(this.mCommodityBeanList, CommodityBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<StoreBean> CREATOR = new Parcelable.Creator<StoreBean>() {
        @Override
        public StoreBean createFromParcel(Parcel source) {
            return new StoreBean(source);
        }

        @Override
        public StoreBean[] newArray(int size) {
            return new StoreBean[size];
        }
    };
}
