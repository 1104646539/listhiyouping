package com.lishi.baijiaxing.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.jar.Attributes;

/**
 * 已登录的帐号
 * Created by Joes on 2016/11/8.
 */
public class LocalUserInfo implements Parcelable {
    /**
     * 帐号
     */
    String nid;
    /**
     * 昵称
     */
    String nickName;

    /**
     * 密码
     */
    String paw;

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPaw() {
        return paw;
    }

    public void setPaw(String paw) {
        this.paw = paw;
    }

    public LocalUserInfo(String nid, String nickName, String paw) {
        this.nid = nid;
        this.nickName = nickName;
        this.paw = paw;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nid);
        dest.writeString(this.nickName);
        dest.writeString(this.paw);
    }

    protected LocalUserInfo(Parcel in) {
        this.nid = in.readString();
        this.nickName = in.readString();
        this.paw = in.readString();
    }

    public static final Creator<LocalUserInfo> CREATOR = new Creator<LocalUserInfo>() {
        @Override
        public LocalUserInfo createFromParcel(Parcel source) {
            return new LocalUserInfo(source);
        }

        @Override
        public LocalUserInfo[] newArray(int size) {
            return new LocalUserInfo[size];
        }
    };
}
