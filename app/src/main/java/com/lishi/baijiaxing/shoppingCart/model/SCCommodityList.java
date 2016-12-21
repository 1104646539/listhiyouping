package com.lishi.baijiaxing.shoppingCart.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2016/12/5.
 */

public class SCCommodityList implements Parcelable {

    /**
     * status : 200
     * msg : 请求数据成功
     * data : [{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5823e40a70029.jpg","name":"太平吉象抱枕被 保险公司的礼品抱枕被鸡年靠枕福字抱枕被 LOGO定制","price":"35.00","buyNum":"1","type":"1","cid":"3097","gid":"6373"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5823e40a70029.jpg","name":"太平吉象抱枕被 保险公司的礼品抱枕被鸡年靠枕福字抱枕被 LOGO定制","price":"35.00","buyNum":"1","type":"1","cid":"3098","gid":"6373"}]
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
         * photoUrl : http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5823e40a70029.jpg
         * name : 太平吉象抱枕被 保险公司的礼品抱枕被鸡年靠枕福字抱枕被 LOGO定制
         * price : 35.00
         * buyNum : 1
         * type : 1
         * cid : 3097
         * gid : 6373
         */

        private String photoUrl;
        private String name;
        private String price;
        private String buyNum;
        private String type;
        private String cid;
        private String gid;

        public String getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getBuyNum() {
            return buyNum;
        }

        public void setBuyNum(String buyNum) {
            this.buyNum = buyNum;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.photoUrl);
            dest.writeString(this.name);
            dest.writeString(this.price);
            dest.writeString(this.buyNum);
            dest.writeString(this.type);
            dest.writeString(this.cid);
            dest.writeString(this.gid);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.photoUrl = in.readString();
            this.name = in.readString();
            this.price = in.readString();
            this.buyNum = in.readString();
            this.type = in.readString();
            this.cid = in.readString();
            this.gid = in.readString();
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
        dest.writeTypedList(this.data);
    }

    public SCCommodityList() {
    }

    protected SCCommodityList(Parcel in) {
        this.status = in.readString();
        this.msg = in.readString();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Parcelable.Creator<SCCommodityList> CREATOR = new Parcelable.Creator<SCCommodityList>() {
        @Override
        public SCCommodityList createFromParcel(Parcel source) {
            return new SCCommodityList(source);
        }

        @Override
        public SCCommodityList[] newArray(int size) {
            return new SCCommodityList[size];
        }
    };
}
