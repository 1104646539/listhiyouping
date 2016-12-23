package com.lishi.baijiaxing.yiyuan.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/22.
 */

public class HotDetails implements Parcelable {

    /**
     * status : 200
     * msg : 请求数据成功
     * data : {"commodityUrls":[{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/201610/goods_img/6324_P_1476831070326.jpg"}],"name":"雅趣双层茶叶罐 高端茶叶罐 普洱醒茶罐 密封茶叶罐 包邮","ooid":"54","maxNum":"3","gid":"6324","starttime":"1482213986","briefUrls":["http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161027/1477536478571029.jpg"],"nowNum":"2","applyNum":"0","type":"0"}
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
         * commodityUrls : [{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/201610/goods_img/6324_P_1476831070326.jpg"}]
         * name : 雅趣双层茶叶罐 高端茶叶罐 普洱醒茶罐 密封茶叶罐 包邮
         * ooid : 54
         * maxNum : 3
         * gid : 6324
         * starttime : 1482213986
         * briefUrls : ["http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161027/1477536478571029.jpg"]
         * nowNum : 2
         * applyNum : 0
         * type : 0
         */

        private String name;
        private String ooid;
        private String maxNum;
        private String gid;
        private String starttime;
        private String nowNum;
        private String applyNum;
        private String type;
        private List<CommodityUrlsBean> commodityUrls;
        private List<String> briefUrls;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOoid() {
            return ooid;
        }

        public void setOoid(String ooid) {
            this.ooid = ooid;
        }

        public String getMaxNum() {
            return maxNum;
        }

        public void setMaxNum(String maxNum) {
            this.maxNum = maxNum;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public String getNowNum() {
            return nowNum;
        }

        public void setNowNum(String nowNum) {
            this.nowNum = nowNum;
        }

        public String getApplyNum() {
            return applyNum;
        }

        public void setApplyNum(String applyNum) {
            this.applyNum = applyNum;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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
             * photoUrl : http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/201610/goods_img/6324_P_1476831070326.jpg
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
            dest.writeString(this.ooid);
            dest.writeString(this.maxNum);
            dest.writeString(this.gid);
            dest.writeString(this.starttime);
            dest.writeString(this.nowNum);
            dest.writeString(this.applyNum);
            dest.writeString(this.type);
            dest.writeList(this.commodityUrls);
            dest.writeStringList(this.briefUrls);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.name = in.readString();
            this.ooid = in.readString();
            this.maxNum = in.readString();
            this.gid = in.readString();
            this.starttime = in.readString();
            this.nowNum = in.readString();
            this.applyNum = in.readString();
            this.type = in.readString();
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

    public HotDetails() {
    }

    protected HotDetails(Parcel in) {
        this.status = in.readString();
        this.msg = in.readString();
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<HotDetails> CREATOR = new Parcelable.Creator<HotDetails>() {
        @Override
        public HotDetails createFromParcel(Parcel source) {
            return new HotDetails(source);
        }

        @Override
        public HotDetails[] newArray(int size) {
            return new HotDetails[size];
        }
    };
}
