package com.lishi.baijiaxing.details.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/25.
 */

public class CommodityDetails implements Parcelable {

    /**
     * status : 200
     * msg : 请求数据成功
     * data : {"commodityUrls":[{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/201611/goods_img/6450_P_1479405056717.jpg"}],"normsList":[{"thumbnailUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582e5e0bec4a4.jpg","inventory":"500","oldPrice":"699.00","nowPrice":"519.00","normsName":"标配"}],"name":"新品苏泊尔球釜柴火饭电饭煲4L家用智能预约电饭锅","cid":"6450","oldPrice":"699.00","nowPrice":"519.00","briefUrls":["http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433125214495.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433125465045.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433125831468.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433126372372.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433126290667.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433126397392.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433126417980.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433126235949.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433127398739.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433127395367.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433127979019.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433127371644.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433127865349.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433127258883.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433128927559.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433128915984.gif"]}
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
         * commodityUrls : [{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/201611/goods_img/6450_P_1479405056717.jpg"}]
         * normsList : [{"thumbnailUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582e5e0bec4a4.jpg","inventory":"500","oldPrice":"699.00","nowPrice":"519.00","normsName":"标配"}]
         * name : 新品苏泊尔球釜柴火饭电饭煲4L家用智能预约电饭锅
         * gid : 6450
         * oldPrice : 699.00
         * nowPrice : 519.00
         * briefUrls : ["http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433125214495.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433125465045.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433125831468.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433126372372.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433126290667.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433126397392.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433126417980.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433126235949.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433127398739.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433127395367.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433127979019.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433127371644.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433127865349.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433127258883.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433128927559.gif","http://www.risevip.com/Public/admin/js/ueditor/php/../../../bdimages/upload1/20161118/1479433128915984.gif"]
         */

        private String name;
        private String gid;
        private String oldPrice;
        private String nowPrice;
        private List<CommodityUrlsBean> commodityUrls;
        private List<NormsListBean> normsList;
        private List<String> briefUrls;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCid() {
            return gid;
        }

        public void setCid(String gid) {
            this.gid = gid;
        }

        public String getOldPrice() {
            return oldPrice;
        }

        public void setOldPrice(String oldPrice) {
            this.oldPrice = oldPrice;
        }

        public String getNowPrice() {
            return nowPrice;
        }

        public void setNowPrice(String nowPrice) {
            this.nowPrice = nowPrice;
        }

        public List<CommodityUrlsBean> getCommodityUrls() {
            return commodityUrls;
        }

        public void setCommodityUrls(List<CommodityUrlsBean> commodityUrls) {
            this.commodityUrls = commodityUrls;
        }

        public List<NormsListBean> getNormsList() {
            return normsList;
        }

        public void setNormsList(List<NormsListBean> normsList) {
            this.normsList = normsList;
        }

        public List<String> getBriefUrls() {
            return briefUrls;
        }

        public void setBriefUrls(List<String> briefUrls) {
            this.briefUrls = briefUrls;
        }

        public static class CommodityUrlsBean implements Parcelable {
            /**
             * photoUrl : http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/201611/goods_img/6450_P_1479405056717.jpg
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

        public static class NormsListBean {
            /**
             * thumbnailUrl : http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582e5e0bec4a4.jpg
             * inventory : 500
             * oldPrice : 699.00
             * nowPrice : 519.00
             * normsName : 标配
             */

            private String thumbnailUrl;
            private String inventory;
            private String oldPrice;
            private String nowPrice;
            private String normsName;

            public String getThumbnailUrl() {
                return thumbnailUrl;
            }

            public void setThumbnailUrl(String thumbnailUrl) {
                this.thumbnailUrl = thumbnailUrl;
            }

            public String getInventory() {
                return inventory;
            }

            public void setInventory(String inventory) {
                this.inventory = inventory;
            }

            public String getOldPrice() {
                return oldPrice;
            }

            public void setOldPrice(String oldPrice) {
                this.oldPrice = oldPrice;
            }

            public String getNowPrice() {
                return nowPrice;
            }

            public void setNowPrice(String nowPrice) {
                this.nowPrice = nowPrice;
            }

            public String getNormsName() {
                return normsName;
            }

            public void setNormsName(String normsName) {
                this.normsName = normsName;
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeString(this.gid);
            dest.writeString(this.oldPrice);
            dest.writeString(this.nowPrice);
            dest.writeTypedList(this.commodityUrls);
            dest.writeList(this.normsList);
            dest.writeStringList(this.briefUrls);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.name = in.readString();
            this.gid = in.readString();
            this.oldPrice = in.readString();
            this.nowPrice = in.readString();
            this.commodityUrls = in.createTypedArrayList(CommodityUrlsBean.CREATOR);
            this.normsList = new ArrayList<NormsListBean>();
            in.readList(this.normsList, NormsListBean.class.getClassLoader());
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
        dest.writeParcelable((Parcelable) this.data, flags);
    }

    public CommodityDetails() {
    }

    protected CommodityDetails(Parcel in) {
        this.status = in.readString();
        this.msg = in.readString();
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<CommodityDetails> CREATOR = new Parcelable.Creator<CommodityDetails>() {
        @Override
        public CommodityDetails createFromParcel(Parcel source) {
            return new CommodityDetails(source);
        }

        @Override
        public CommodityDetails[] newArray(int size) {
            return new CommodityDetails[size];
        }
    };
}
