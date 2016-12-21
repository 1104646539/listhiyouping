package com.lishi.baijiaxing.submitOrder.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/12/9.
 */

public class SubmitOrder implements Parcelable {

    /**
     * status : 205
     * msg : 下订成功
     * data : {"sn":"RS2016030915122479","price":"0"}
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
         * sn : RS2016030915122479
         * price : 0
         */

        private String sn;
        private String price;

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.sn);
            dest.writeString(this.price);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.sn = in.readString();
            this.price = in.readString();
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

    public SubmitOrder() {
    }

    protected SubmitOrder(Parcel in) {
        this.status = in.readString();
        this.msg = in.readString();
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<SubmitOrder> CREATOR = new Parcelable.Creator<SubmitOrder>() {
        @Override
        public SubmitOrder createFromParcel(Parcel source) {
            return new SubmitOrder(source);
        }

        @Override
        public SubmitOrder[] newArray(int size) {
            return new SubmitOrder[size];
        }
    };
}
