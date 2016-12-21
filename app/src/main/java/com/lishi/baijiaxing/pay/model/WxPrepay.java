package com.lishi.baijiaxing.pay.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * 请求服务器的预支付信息
 * Created by Administrator on 2016/12/14.
 */

public class WxPrepay implements Parcelable {

    @Override
    public String toString() {
        return "WxPrepay{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data.toString() +
                '}';
    }

    /**
     * status : 200
     * msg : 请求数据成功
     * data : {"appid":"wxaa99a1b35b04eb47","noncestr":"yfe6ftculq1xwqozrbrtf6kb6chj215t","package":"Sign=WXpay","partnerid":"1411589602","prepayid":"wx20161215170800f38384ef6b0039350489","timestamp":"1481792880","paySign":"64B06E877EEC313B89DC995F4E9E351D"}
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
         * appid : wxaa99a1b35b04eb47
         * noncestr : yfe6ftculq1xwqozrbrtf6kb6chj215t
         * package : Sign=WXpay
         * partnerid : 1411589602
         * prepayid : wx20161215170800f38384ef6b0039350489
         * timestamp : 1481792880
         * paySign : 64B06E877EEC313B89DC995F4E9E351D
         */

        private String appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String prepayid;
        private String timestamp;
        private String paySign;

        @Override
        public String toString() {
            return "DataBean{" +
                    "appid='" + appid + '\'' +
                    ", noncestr='" + noncestr + '\'' +
                    ", packageX='" + packageX + '\'' +
                    ", partnerid='" + partnerid + '\'' +
                    ", prepayid='" + prepayid + '\'' +
                    ", timestamp='" + timestamp + '\'' +
                    ", paySign='" + paySign + '\'' +
                    '}';
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getPaySign() {
            return paySign;
        }

        public void setPaySign(String paySign) {
            this.paySign = paySign;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.appid);
            dest.writeString(this.noncestr);
            dest.writeString(this.packageX);
            dest.writeString(this.partnerid);
            dest.writeString(this.prepayid);
            dest.writeString(this.timestamp);
            dest.writeString(this.paySign);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.appid = in.readString();
            this.noncestr = in.readString();
            this.packageX = in.readString();
            this.partnerid = in.readString();
            this.prepayid = in.readString();
            this.timestamp = in.readString();
            this.paySign = in.readString();
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

    public WxPrepay() {
    }

    protected WxPrepay(Parcel in) {
        this.status = in.readString();
        this.msg = in.readString();
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Creator<WxPrepay> CREATOR = new Creator<WxPrepay>() {
        @Override
        public WxPrepay createFromParcel(Parcel source) {
            return new WxPrepay(source);
        }

        @Override
        public WxPrepay[] newArray(int size) {
            return new WxPrepay[size];
        }
    };
}
