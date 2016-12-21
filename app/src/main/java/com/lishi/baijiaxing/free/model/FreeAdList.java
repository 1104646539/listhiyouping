package com.lishi.baijiaxing.free.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/20.
 */

public class FreeAdList implements Parcelable {

    /**
     * status : 200
     * msg : 请求数据成功
     * data : [{"gid":"147","name":"手机端天天免费领广告图1","potpUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/ads/s_583f7b4ca3c7e.jpg"},{"gid":"148","name":"手机端天天免费领广告图2","potpUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/ads/s_583f7b96d2a10.jpg"},{"gid":"149","name":"手机端天天免费领广告图3","potpUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/ads/s_583f7bba215dc.jpg"}]
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
         * gid : 147
         * name : 手机端天天免费领广告图1
         * potpUrl : http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/ads/s_583f7b4ca3c7e.jpg
         */

        private String gid;
        private String name;
        private String potpUrl;

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPotpUrl() {
            return potpUrl;
        }

        public void setPotpUrl(String potpUrl) {
            this.potpUrl = potpUrl;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.gid);
            dest.writeString(this.name);
            dest.writeString(this.potpUrl);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.gid = in.readString();
            this.name = in.readString();
            this.potpUrl = in.readString();
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
        dest.writeList(this.data);
    }

    public FreeAdList() {
    }

    protected FreeAdList(Parcel in) {
        this.status = in.readString();
        this.msg = in.readString();
        this.data = new ArrayList<DataBean>();
        in.readList(this.data, DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<FreeAdList> CREATOR = new Parcelable.Creator<FreeAdList>() {
        @Override
        public FreeAdList createFromParcel(Parcel source) {
            return new FreeAdList(source);
        }

        @Override
        public FreeAdList[] newArray(int size) {
            return new FreeAdList[size];
        }
    };
}
