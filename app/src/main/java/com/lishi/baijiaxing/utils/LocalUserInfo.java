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
     * 头像url
     */
    String photoUrl;
    /**
     * 性别
     */
    String sex;
    /**
     * 昵称
     */
    String nickName;

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public static Creator<LocalUserInfo> getCREATOR() {
        return CREATOR;
    }

    private LocalUserInfo() {
    }

    public static LocalUserInfo getInstance() {
        return LocalUserInfoHolder.localUserInfo;
    }

    private static class LocalUserInfoHolder {
        static LocalUserInfo localUserInfo = new LocalUserInfo();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nid);
        dest.writeString(this.photoUrl);
        dest.writeString(this.sex);
        dest.writeString(this.nickName);
    }

    protected LocalUserInfo(Parcel in) {
        this.nid = in.readString();
        this.photoUrl = in.readString();
        this.sex = in.readString();
        this.nickName = in.readString();
    }

    public static final Parcelable.Creator<LocalUserInfo> CREATOR = new Parcelable.Creator<LocalUserInfo>() {
        @Override
        public LocalUserInfo createFromParcel(Parcel source) {
            return new LocalUserInfo(source);
        }

        @Override
        public LocalUserInfo[] newArray(int size) {
            return new LocalUserInfo[size];
        }
    };

    public boolean isNull() {
        if (nid.equals("") || nickName.equals("")) {
            return true;
        }
        return false;
    }
}
