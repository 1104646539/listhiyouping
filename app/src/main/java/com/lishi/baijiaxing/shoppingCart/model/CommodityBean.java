package com.lishi.baijiaxing.shoppingCart.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 购物车商品类
 * <p/>
 * Created by Administrator on 2016/3/14.
 */
public class CommodityBean implements Parcelable {
    private String commImgUrl;//商品图片的url
    private String commTitle;//商品标题
    private String commInfo;//商品信息
    private int commPrice;//商品价格
    private int commId;//商品唯一id
    private int commNum;//商品数量
    private boolean isChecked = false;//商品是否被选择

    public CommodityBean() {
        
    }

    public CommodityBean(String commImgUrl, String commTitle, String commInfo, int commPrice, int commId, int commNum, boolean isChecked) {
        this.commImgUrl = commImgUrl;
        this.commTitle = commTitle;
        this.commInfo = commInfo;
        this.commPrice = commPrice;
        this.commId = commId;
        this.commNum = commNum;
        this.isChecked = isChecked;
    }

    public String getCommImgUrl() {
        return commImgUrl;
    }

    public void setCommImgUrl(String commImgUrl) {
        this.commImgUrl = commImgUrl;
    }

    public String getCommTitle() {
        return commTitle;
    }

    public void setCommTitle(String commTitle) {
        this.commTitle = commTitle;
    }

    public String getCommInfo() {
        return commInfo;
    }

    public void setCommInfo(String commInfo) {
        this.commInfo = commInfo;
    }

    public int getCommPrice() {
        return commPrice;
    }

    public void setCommPrice(int commPrice) {
        this.commPrice = commPrice;
    }

    public int getCommId() {
        return commId;
    }

    public void setCommId(int commId) {
        this.commId = commId;
    }

    public int getCommNum() {
        return commNum;
    }

    public void setCommNum(int commNum) {
        this.commNum = commNum;
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
        dest.writeString(this.commImgUrl);
        dest.writeString(this.commTitle);
        dest.writeString(this.commInfo);
        dest.writeInt(this.commPrice);
        dest.writeInt(this.commId);
        dest.writeInt(this.commNum);
        dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
    }

    protected CommodityBean(Parcel in) {
        this.commImgUrl = in.readString();
        this.commTitle = in.readString();
        this.commInfo = in.readString();
        this.commPrice = in.readInt();
        this.commId = in.readInt();
        this.commNum = in.readInt();
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
