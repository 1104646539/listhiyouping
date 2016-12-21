package com.lishi.baijiaxing.pay.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/12/9.
 */

public class PayType implements Parcelable {
    private String name;
    private int srcId;
    private String brief;

    public PayType(String name, int srcId, String brief) {
        this.name = name;
        this.srcId = srcId;
        this.brief = brief;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSrcId() {
        return srcId;
    }

    public void setSrcId(int srcId) {
        this.srcId = srcId;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public static Creator<PayType> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.srcId);
        dest.writeString(this.brief);
    }

    protected PayType(Parcel in) {
        this.name = in.readString();
        this.srcId = in.readInt();
        this.brief = in.readString();
    }

    public static final Parcelable.Creator<PayType> CREATOR = new Parcelable.Creator<PayType>() {
        @Override
        public PayType createFromParcel(Parcel source) {
            return new PayType(source);
        }

        @Override
        public PayType[] newArray(int size) {
            return new PayType[size];
        }
    };
}
