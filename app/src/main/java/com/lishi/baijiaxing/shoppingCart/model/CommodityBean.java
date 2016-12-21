package com.lishi.baijiaxing.shoppingCart.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 购物车商品类
 * <p/>
 * Created by Administrator on 2016/3/14.
 */
public class CommodityBean implements Parcelable {
    private SCCommodityList.DataBean mDataBean;
    private boolean isChecked = false;//商品是否被选择

    public CommodityBean() {

    }

    public CommodityBean(SCCommodityList.DataBean dataBean) {
        mDataBean = dataBean;
        isChecked = false;
    }

    public SCCommodityList.DataBean getDataBean() {
        return mDataBean;
    }

    public void setDataBean(SCCommodityList.DataBean dataBean) {
        mDataBean = dataBean;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mDataBean, flags);
        dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
    }

    protected CommodityBean(Parcel in) {
        this.mDataBean = in.readParcelable(SCCommodityList.DataBean.class.getClassLoader());
        this.isChecked = in.readByte() != 0;
    }

    public static final Parcelable.Creator<CommodityBean> CREATOR = new Parcelable.Creator<CommodityBean>() {
        @Override
        public CommodityBean createFromParcel(Parcel source) {
            return new CommodityBean(source);
        }

        @Override
        public CommodityBean[] newArray(int size) {
            return new CommodityBean[size];
        }
    };
}
