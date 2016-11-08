package com.lishi.baijiaxing.shoppingCart.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 提交订单里的购物列表
 * Created by Administrator on 2016/7/18.
 */
public class ShoppingListBean implements Parcelable {
    private StoreBean mStoreBean;//要购买的商品列表
    private String shoppingMephod;//配送方式
    private String shoppingTime;//配送时间
    private String invoiceInfo;//发票信息

    public ShoppingListBean(StoreBean storeBeen, String shoppingMephod, String shoppingTime, String invoiceInfo) {
        mStoreBean = storeBeen;
        this.shoppingMephod = shoppingMephod;
        this.shoppingTime = shoppingTime;
        this.invoiceInfo = invoiceInfo;
    }

    public StoreBean getStoreBean() {
        return mStoreBean;
    }

    public void setStoreBean(StoreBean storeBeen) {
        mStoreBean = storeBeen;
    }

    public String getShoppingMephod() {
        return shoppingMephod;
    }

    public void setShoppingMephod(String shoppingMephod) {
        this.shoppingMephod = shoppingMephod;
    }

    public String getShoppingTime() {
        return shoppingTime;
    }

    public void setShoppingTime(String shoppingTime) {
        this.shoppingTime = shoppingTime;
    }

    public String getInvoiceInfo() {
        return invoiceInfo;
    }

    public void setInvoiceInfo(String invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mStoreBean, flags);
        dest.writeString(this.shoppingMephod);
        dest.writeString(this.shoppingTime);
        dest.writeString(this.invoiceInfo);
    }

    protected ShoppingListBean(Parcel in) {
        this.mStoreBean = in.readParcelable(StoreBean.class.getClassLoader());
        this.shoppingMephod = in.readString();
        this.shoppingTime = in.readString();
        this.invoiceInfo = in.readString();
    }

    public static final Parcelable.Creator<ShoppingListBean> CREATOR = new Parcelable.Creator<ShoppingListBean>() {
        @Override
        public ShoppingListBean createFromParcel(Parcel source) {
            return new ShoppingListBean(source);
        }

        @Override
        public ShoppingListBean[] newArray(int size) {
            return new ShoppingListBean[size];
        }
    };
}
