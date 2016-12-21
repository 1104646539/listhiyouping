package com.lishi.baijiaxing.wxapi.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 登录返回数据
 * Created by Administrator on 2016/11/29.
 */

public class Login implements Parcelable {

    /**
     * status : 201
     * msg : 登录成功
     * data : {"type":"qq","uid":"2682","token":"9f787f166f477ae93ff206e54454af48"}
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
         * type : qq
         * uid : 2682
         * token : 9f787f166f477ae93ff206e54454af48
         */

        private String type;
        private String uid;
        private String token;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.type);
            dest.writeString(this.uid);
            dest.writeString(this.token);
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "type='" + type + '\'' +
                    ", uid='" + uid + '\'' +
                    ", token='" + token + '\'' +
                    '}';
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.type = in.readString();
            this.uid = in.readString();
            this.token = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
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

    public Login() {
    }

    protected Login(Parcel in) {
        this.status = in.readString();
        this.msg = in.readString();
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<Login> CREATOR = new Parcelable.Creator<Login>() {
        @Override
        public Login createFromParcel(Parcel source) {
            return new Login(source);
        }

        @Override
        public Login[] newArray(int size) {
            return new Login[size];
        }
    };

    @Override
    public String toString() {
        return "Login{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
