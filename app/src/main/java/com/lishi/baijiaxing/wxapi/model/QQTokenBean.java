package com.lishi.baijiaxing.wxapi.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/11/9.
 */
public class QQTokenBean implements Parcelable {
    private int ret;
    private String openid;
    private String access_token;
    private String pay_token;
    private int expires_in;
    private String pf;
    private String pfkey;
    private String msg;
    private int login_cost;
    private int query_authority_cost;
    private int authority_cost;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getPay_token() {
        return pay_token;
    }

    public void setPay_token(String pay_token) {
        this.pay_token = pay_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public String getPfkey() {
        return pfkey;
    }

    public void setPfkey(String pfkey) {
        this.pfkey = pfkey;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getLogin_cost() {
        return login_cost;
    }

    public void setLogin_cost(int login_cost) {
        this.login_cost = login_cost;
    }

    public int getQuery_authority_cost() {
        return query_authority_cost;
    }

    public void setQuery_authority_cost(int query_authority_cost) {
        this.query_authority_cost = query_authority_cost;
    }

    public int getAuthority_cost() {
        return authority_cost;
    }

    public void setAuthority_cost(int authority_cost) {
        this.authority_cost = authority_cost;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ret);
        dest.writeString(this.openid);
        dest.writeString(this.access_token);
        dest.writeString(this.pay_token);
        dest.writeInt(this.expires_in);
        dest.writeString(this.pf);
        dest.writeString(this.pfkey);
        dest.writeString(this.msg);
        dest.writeInt(this.login_cost);
        dest.writeInt(this.query_authority_cost);
        dest.writeInt(this.authority_cost);
    }

    public QQTokenBean() {
    }

    protected QQTokenBean(Parcel in) {
        this.ret = in.readInt();
        this.openid = in.readString();
        this.access_token = in.readString();
        this.pay_token = in.readString();
        this.expires_in = in.readInt();
        this.pf = in.readString();
        this.pfkey = in.readString();
        this.msg = in.readString();
        this.login_cost = in.readInt();
        this.query_authority_cost = in.readInt();
        this.authority_cost = in.readInt();
    }

    public static final Parcelable.Creator<QQTokenBean> CREATOR = new Parcelable.Creator<QQTokenBean>() {
        @Override
        public QQTokenBean createFromParcel(Parcel source) {
            return new QQTokenBean(source);
        }

        @Override
        public QQTokenBean[] newArray(int size) {
            return new QQTokenBean[size];
        }
    };

    @Override
    public String toString() {
        return "QQTokenBean{" +
                "ret=" + ret +
                ", openid='" + openid + '\'' +
                ", access_token='" + access_token + '\'' +
                ", pay_token='" + pay_token + '\'' +
                ", expires_in=" + expires_in +
                ", pf='" + pf + '\'' +
                ", pfkey='" + pfkey + '\'' +
                ", msg='" + msg + '\'' +
                ", login_cost=" + login_cost +
                ", query_authority_cost=" + query_authority_cost +
                ", authority_cost=" + authority_cost +
                '}';
    }
}
