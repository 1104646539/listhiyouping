package com.lishi.baijiaxing.free.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/20.
 */

public class FreeDetails implements Parcelable {


    /**
     * status : 200
     * msg : 请求数据成功
     * data : {"commodityUrls":[{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/201610/goods_img/6346_P_1476901941250.jpg"}],"name":"《52个生活小妙招》周历 2017年保险办公记事本周历 挂历 加印LOGO 礼品定制","applyNum":"250","price":"18.00","limitNum":"10","type":2,"time":"1482422399","gid":"6346","form":"2","briefUrls":["http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161020/1476930775360143.gif"],"zid":"33"}
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
         * commodityUrls : [{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/201610/goods_img/6346_P_1476901941250.jpg"}]
         * name : 《52个生活小妙招》周历 2017年保险办公记事本周历 挂历 加印LOGO 礼品定制
         * applyNum : 250
         * price : 18.00
         * limitNum : 10
         * type : 2
         * time : 1482422399
         * gid : 6346
         * form : 2
         * briefUrls : ["http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161020/1476930775360143.gif"]
         * zid : 33
         */

        private String name;
        private String applyNum;
        private String price;
        private String limitNum;
        private String type;
        private String time;
        private String gid;
        private String form;
        private String zid;
        private List<CommodityUrlsBean> commodityUrls;
        private List<String> briefUrls;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getApplyNum() {
            return applyNum;
        }

        public void setApplyNum(String applyNum) {
            this.applyNum = applyNum;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getForm() {
            return form;
        }

        public void setForm(String form) {
            this.form = form;
        }

        public String getZid() {
            return zid;
        }

        public void setZid(String zid) {
            this.zid = zid;
        }

        public List<CommodityUrlsBean> getCommodityUrls() {
            return commodityUrls;
        }

        public void setCommodityUrls(List<CommodityUrlsBean> commodityUrls) {
            this.commodityUrls = commodityUrls;
        }

        public List<String> getBriefUrls() {
            return briefUrls;
        }

        public void setBriefUrls(List<String> briefUrls) {
            this.briefUrls = briefUrls;
        }

        public static class CommodityUrlsBean implements Parcelable {
            /**
             * photoUrl : http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/201610/goods_img/6346_P_1476901941250.jpg
             */

            private String photoUrl;

            public String getPhotoUrl() {
                return photoUrl;
            }

            public void setPhotoUrl(String photoUrl) {
                this.photoUrl = photoUrl;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.photoUrl);
            }

            public CommodityUrlsBean() {
            }

            protected CommodityUrlsBean(Parcel in) {
                this.photoUrl = in.readString();
            }

            public static final Creator<CommodityUrlsBean> CREATOR = new Creator<CommodityUrlsBean>() {
                @Override
                public CommodityUrlsBean createFromParcel(Parcel source) {
                    return new CommodityUrlsBean(source);
                }

                @Override
                public CommodityUrlsBean[] newArray(int size) {
                    return new CommodityUrlsBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeString(this.applyNum);
            dest.writeString(this.price);
            dest.writeString(this.limitNum);
            dest.writeString(this.type);
            dest.writeString(this.time);
            dest.writeString(this.gid);
            dest.writeString(this.form);
            dest.writeString(this.zid);
            dest.writeList(this.commodityUrls);
            dest.writeStringList(this.briefUrls);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.name = in.readString();
            this.applyNum = in.readString();
            this.price = in.readString();
            this.limitNum = in.readString();
            this.type = in.readString();
            this.time = in.readString();
            this.gid = in.readString();
            this.form = in.readString();
            this.zid = in.readString();
            this.commodityUrls = new ArrayList<CommodityUrlsBean>();
            in.readList(this.commodityUrls, CommodityUrlsBean.class.getClassLoader());
            this.briefUrls = in.createStringArrayList();
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

    public FreeDetails() {
    }

    protected FreeDetails(Parcel in) {
        this.status = in.readString();
        this.msg = in.readString();
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Creator<FreeDetails> CREATOR = new Creator<FreeDetails>() {
        @Override
        public FreeDetails createFromParcel(Parcel source) {
            return new FreeDetails(source);
        }

        @Override
        public FreeDetails[] newArray(int size) {
            return new FreeDetails[size];
        }
    };
}
