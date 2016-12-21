package com.lishi.baijiaxing.orderAddressManage.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2016/12/2.
 */

public class AddressList implements Parcelable {

    /**
     * status : 200
     * msg : 请求数据成功
     * data : [{"aid":"485","consigneeName":"","province":"广东省","city":"深圳市","district":"0","details":"体育馆","consigneeNumber":"11022","isDefalut":"0"},{"aid":"486","consigneeName":"","province":"广东省","city":"深圳市","district":"龙岗区","details":"体育馆","consigneeNumber":"11022","isDefalut":"0"}]
     */

    private String status;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * aid : 485
         * consigneeName :
         * province : 广东省
         * city : 深圳市
         * district : 0
         * details : 体育馆
         * consigneeNumber : 11022
         * isDefalut : 0
         */

        private String aid;
        private String consigneeName;
        private String province;
        private String city;
        private String district;
        private String details;
        private String consigneeNumber;
        private String isDefalut;

        @Override
        public String toString() {
            return province +
                    city +
                    district +
                    "  " + details;
        }

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String getConsigneeName() {
            return consigneeName;
        }

        public void setConsigneeName(String consigneeName) {
            this.consigneeName = consigneeName;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getConsigneeNumber() {
            return consigneeNumber;
        }

        public void setConsigneeNumber(String consigneeNumber) {
            this.consigneeNumber = consigneeNumber;
        }

        public String getIsDefalut() {
            return isDefalut;
        }

        public void setIsDefalut(String isDefalut) {
            this.isDefalut = isDefalut;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.aid);
            dest.writeString(this.consigneeName);
            dest.writeString(this.province);
            dest.writeString(this.city);
            dest.writeString(this.district);
            dest.writeString(this.details);
            dest.writeString(this.consigneeNumber);
            dest.writeString(this.isDefalut);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.aid = in.readString();
            this.consigneeName = in.readString();
            this.province = in.readString();
            this.city = in.readString();
            this.district = in.readString();
            this.details = in.readString();
            this.consigneeNumber = in.readString();
            this.isDefalut = in.readString();
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
    public String toString() {
        return "AddressList{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeString(this.msg);
        dest.writeTypedList(this.data);
    }

    public AddressList() {
    }

    protected AddressList(Parcel in) {
        this.status = in.readString();
        this.msg = in.readString();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Parcelable.Creator<AddressList> CREATOR = new Parcelable.Creator<AddressList>() {
        @Override
        public AddressList createFromParcel(Parcel source) {
            return new AddressList(source);
        }

        @Override
        public AddressList[] newArray(int size) {
            return new AddressList[size];
        }
    };

}
