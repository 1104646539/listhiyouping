package com.lishi.baijiaxing.free.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/20.
 */

public class FreeList implements Parcelable {

    /**
     * status : 200
     * msg : 请求数据成功
     * data : [{"zid":"35","gid":"6322","potpUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809e1109ecdd.jpg","name":"USB快充小夜灯 光控夜灯 多孔快充 包邮","price":"100.00","limitNum":"2","applyNum":"0","form":"3","type":"2"}]
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
         * zid : 35
         * gid : 6322
         * potpUrl : http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809e1109ecdd.jpg
         * name : USB快充小夜灯 光控夜灯 多孔快充 包邮
         * price : 100.00
         * limitNum : 2
         * applyNum : 0
         * form : 3
         * type : 2
         */

        private String zid;
        private String gid;
        private String potpUrl;
        private String name;
        private String price;
        private String limitNum;
        private String applyNum;
        private String form;
        private String type;

        public String getZid() {
            return zid;
        }

        public void setZid(String zid) {
            this.zid = zid;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getPotpUrl() {
            return potpUrl;
        }

        public void setPotpUrl(String potpUrl) {
            this.potpUrl = potpUrl;
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

        public String getLimitNum() {
            return limitNum;
        }

        public void setLimitNum(String limitNum) {
            this.limitNum = limitNum;
        }

        public String getApplyNum() {
            return applyNum;
        }

        public void setApplyNum(String applyNum) {
            this.applyNum = applyNum;
        }

        public String getForm() {
            return form;
        }

        public void setForm(String form) {
            this.form = form;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.zid);
            dest.writeString(this.gid);
            dest.writeString(this.potpUrl);
            dest.writeString(this.name);
            dest.writeString(this.price);
            dest.writeString(this.limitNum);
            dest.writeString(this.applyNum);
            dest.writeString(this.form);
            dest.writeString(this.type);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.zid = in.readString();
            this.gid = in.readString();
            this.potpUrl = in.readString();
            this.name = in.readString();
            this.price = in.readString();
            this.limitNum = in.readString();
            this.applyNum = in.readString();
            this.form = in.readString();
            this.type = in.readString();
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

    public FreeList() {
    }

    protected FreeList(Parcel in) {
        this.status = in.readString();
        this.msg = in.readString();
        this.data = new ArrayList<DataBean>();
        in.readList(this.data, DataBean.class.getClassLoader());
    }

    public static final Creator<FreeList> CREATOR = new Creator<FreeList>() {
        @Override
        public FreeList createFromParcel(Parcel source) {
            return new FreeList(source);
        }

        @Override
        public FreeList[] newArray(int size) {
            return new FreeList[size];
        }
    };
}
