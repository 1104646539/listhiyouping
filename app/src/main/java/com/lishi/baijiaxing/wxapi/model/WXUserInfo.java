package com.lishi.baijiaxing.wxapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */
public class WXUserInfo implements Parcelable {

    private String openid;
    private String nickname;
    private int sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private String unionid;
    private List<String> privilege;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public WXUserInfo() {
    }

    public List<String> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<String> privilege) {
        this.privilege = privilege;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.openid);
        dest.writeString(this.nickname);
        dest.writeInt(this.sex);
        dest.writeString(this.language);
        dest.writeString(this.city);
        dest.writeString(this.province);
        dest.writeString(this.country);
        dest.writeString(this.headimgurl);
        dest.writeString(this.unionid);
        dest.writeStringList(this.privilege);
    }

    protected WXUserInfo(Parcel in) {
        this.openid = in.readString();
        this.nickname = in.readString();
        this.sex = in.readInt();
        this.language = in.readString();
        this.city = in.readString();
        this.province = in.readString();
        this.country = in.readString();
        this.headimgurl = in.readString();
        this.unionid = in.readString();
        this.privilege = in.createStringArrayList();
    }

    public static final Creator<WXUserInfo> CREATOR = new Creator<WXUserInfo>() {
        @Override
        public WXUserInfo createFromParcel(Parcel source) {
            return new WXUserInfo(source);
        }

        @Override
        public WXUserInfo[] newArray(int size) {
            return new WXUserInfo[size];
        }
    };

    @Override
    public String toString() {
        return "WXUserInfo{" +
                "openid='" + openid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", language='" + language + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", unionid='" + unionid + '\'' +
                ", privilege=" + privilege +
                '}';
    }
}
