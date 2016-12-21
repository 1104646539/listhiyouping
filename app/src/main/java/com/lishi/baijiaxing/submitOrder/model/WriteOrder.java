package com.lishi.baijiaxing.submitOrder.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.lishi.baijiaxing.orderAddressManage.model.AddressList;

/**
 * Created by Administrator on 2016/12/9.
 */

public class WriteOrder implements Parcelable {

    /**
     * status : 200
     * msg : 请求数据成功
     * data : {"invoice":"不发开票","coupon":"","integral":"","freight":"0","addr":{"aid":"504","consigneeName":"王尼玛","province":"广东省","city":"深圳市","district":"龙岗区","details":"体育馆","consigneeNumber":"11022322536","isDefalut":"1"}}
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
         * invoice : 不发开票
         * coupon :
         * integral :
         * freight : 0
         * addr : {"aid":"504","consigneeName":"王尼玛","province":"广东省","city":"深圳市","district":"龙岗区","details":"体育馆","consigneeNumber":"11022322536","isDefalut":"1"}
         */

        private String invoice;
        private String coupon;
        private String integral;
        private String freight;
        private AddressList.DataBean addr;

        public String getInvoice() {
            return invoice;
        }

        public void setInvoice(String invoice) {
            this.invoice = invoice;
        }

        public String getCoupon() {
            return coupon;
        }

        public void setCoupon(String coupon) {
            this.coupon = coupon;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getFreight() {
            return freight;
        }

        public void setFreight(String freight) {
            this.freight = freight;
        }

        public AddressList.DataBean getAddr() {
            return addr;
        }

        public void setAddr(AddressList.DataBean addr) {
            
            this.addr = addr;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.invoice);
            dest.writeString(this.coupon);
            dest.writeString(this.integral);
            dest.writeString(this.freight);
            dest.writeParcelable(this.addr, flags);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.invoice = in.readString();
            this.coupon = in.readString();
            this.integral = in.readString();
            this.freight = in.readString();
            this.addr = in.readParcelable(AddressList.DataBean.class.getClassLoader());
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

    public WriteOrder() {
    }

    protected WriteOrder(Parcel in) {
        this.status = in.readString();
        this.msg = in.readString();
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<WriteOrder> CREATOR = new Parcelable.Creator<WriteOrder>() {
        @Override
        public WriteOrder createFromParcel(Parcel source) {
            return new WriteOrder(source);
        }

        @Override
        public WriteOrder[] newArray(int size) {
            return new WriteOrder[size];
        }
    };
}
