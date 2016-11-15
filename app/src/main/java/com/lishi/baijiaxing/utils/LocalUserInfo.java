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
    /**
     * 会员等级
     */
    String level;

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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    private LocalUserInfo() {
    }

    public static LocalUserInfo getInstance() {
        return LocalUserInfoHolder.localUserInfo;
    }

    private static class LocalUserInfoHolder {
        static LocalUserInfo localUserInfo = new LocalUserInfo();
    }

    public boolean isNull() {
        if (LocalUserInfoHolder.localUserInfo != null) {
            if (LocalUserInfoHolder.localUserInfo.nid == null || LocalUserInfoHolder.localUserInfo.nickName == null) {
                return true;
            }
        }
        return false;
    }

    public LocalUserInfo(String nid, String photoUrl, String sex, String nickName, String level) {
        this.nid = nid;
        this.photoUrl = photoUrl;
        this.sex = sex;
        this.nickName = nickName;
        this.level = level;
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
        dest.writeString(this.level);
    }

    protected LocalUserInfo(Parcel in) {
        this.nid = in.readString();
        this.photoUrl = in.readString();
        this.sex = in.readString();
        this.nickName = in.readString();
        this.level = in.readString();
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
