package com.lishi.baijiaxing.personal.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/12/1.
 */

public class LocalUserInfo implements Parcelable {

    /**
     * status : 200
     * msg : 请求数据成功
     * data : {"aid":"2558","nickname":"咸鱼","headimg":"http://wx.qlogo.cn/mmopen/CkS8OHwluCtzXhfH1ba3FuKTLdibfO4rvvIdADpo0VeMtsXTsZyfBFPn9tsahtRa1m1gkibiaLGF8QRYvwUmde8rDJ3BygksxIJ/0","sex":"0","email":"","number":"","viplevel":"1"}
     */

    private String status;
    private String msg;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * aid : 2558
         * nickname : 咸鱼
         * headimg : http://wx.qlogo.cn/mmopen/CkS8OHwluCtzXhfH1ba3FuKTLdibfO4rvvIdADpo0VeMtsXTsZyfBFPn9tsahtRa1m1gkibiaLGF8QRYvwUmde8rDJ3BygksxIJ/0
         * sex : 0
         * email :
         * number :
         * viplevel : 1
         */

        private String uid;
        private String nickname;
        private String headimg;
        private String sex;
        private String email;
        private String number;
        private String viplevel;

        public String getAid() {
            return uid;
        }

        public void setAid(String uid) {
            this.uid = uid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getViplevel() {
            return viplevel;
        }

        public void setViplevel(String viplevel) {
            this.viplevel = viplevel;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "aid='" + uid + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", headimg='" + headimg + '\'' +
                    ", sex='" + sex + '\'' +
                    ", email='" + email + '\'' +
                    ", number='" + number + '\'' +
                    ", viplevel='" + viplevel + '\'' +
                    '}';

        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.uid);
            dest.writeString(this.nickname);
            dest.writeString(this.headimg);
            dest.writeString(this.sex);
            dest.writeString(this.email);
            dest.writeString(this.number);
            dest.writeString(this.viplevel);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.uid = in.readString();
            this.nickname = in.readString();
            this.headimg = in.readString();
            this.sex = in.readString();
            this.email = in.readString();
            this.number = in.readString();
            this.viplevel = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeString(this.msg);
        dest.writeParcelable(this.data, flags);
    }

    public LocalUserInfo() {
    }

    protected LocalUserInfo(Parcel in) {
        this.status = in.readString();
        this.msg = in.readString();
        this.data = in.readParcelable(DataBean.class.getClassLoader());
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
}
