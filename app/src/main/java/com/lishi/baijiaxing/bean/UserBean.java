package com.lishi.baijiaxing.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 帐号类
 * Created by Administrator on 2016/8/18.
 */
public class UserBean implements Parcelable {
    private String userName;//昵称
    private String userNumber;//帐号
    private String userPassWord;//密码
    private double phoneNumber;
    private int photo;//头像
    private boolean isDefalut = true;//是否默认登录

    public UserBean() {
        
    }

    public UserBean(String userName, String userNumber, String userPassWord, double phoneNumber, int photo) {
        this.userName = userName;
        this.userNumber = userNumber;
        this.userPassWord = userPassWord;
        this.photo = photo;
        this.phoneNumber = phoneNumber;
    }

    public double getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(double phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserPassWord() {
        return userPassWord;
    }

    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }

    public boolean isDefalut() {
        return isDefalut;
    }

    public void setDefalut(boolean defalut) {
        isDefalut = defalut;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public boolean getIsDefalut() {
        return isDefalut;
    }

    public void setIsDefalut(boolean isDefalut) {
        this.isDefalut = isDefalut;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.userNumber);
        dest.writeString(this.userPassWord);
        dest.writeDouble(this.phoneNumber);
        dest.writeInt(this.photo);
        dest.writeByte(this.isDefalut ? (byte) 1 : (byte) 0);
    }

    protected UserBean(Parcel in) {
        this.userName = in.readString();
        this.userNumber = in.readString();
        this.userPassWord = in.readString();
        this.phoneNumber = in.readDouble();
        this.photo = in.readInt();
        this.isDefalut = in.readByte() != 0;
    }

    public static final Parcelable.Creator<UserBean> CREATOR = new Parcelable.Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel source) {
            return new UserBean(source);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };
}
