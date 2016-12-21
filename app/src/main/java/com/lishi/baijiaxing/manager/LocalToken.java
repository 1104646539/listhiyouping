package com.lishi.baijiaxing.manager;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.lishi.baijiaxing.utils.LocalUserInfo;
import com.lishi.baijiaxing.wxapi.model.Login;

/**
 * Created by Administrator on 2016/11/30.
 */

public class LocalToken implements Parcelable {

    private Login.DataBean mDataBean;

    public Login.DataBean getDataBean() {
        return mDataBean;
    }

    public void setDataBean(Login.DataBean dataBean) {
        mDataBean = dataBean;
    }

    private LocalToken() {
    }

    public static LocalToken getInstance() {
        return LocalTokenHolder.localToken;
    }

    private static class LocalTokenHolder {
        public static LocalToken localToken = new LocalToken();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mDataBean, flags);
    }

    protected LocalToken(Parcel in) {
        this.mDataBean = in.readParcelable(Login.DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<LocalToken> CREATOR = new Parcelable.Creator<LocalToken>() {
        @Override
        public LocalToken createFromParcel(Parcel source) {
            return new LocalToken(source);
        }

        @Override
        public LocalToken[] newArray(int size) {
            return new LocalToken[size];
        }
    };
}
