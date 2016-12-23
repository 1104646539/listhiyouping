package com.lishi.baijiaxing.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页商品列表
 * Created by Administrator on 2016/11/23.
 */

public class Commodity implements Parcelable {

    /**
     * status : 200
     * msg : 请求数据成功
     * data : {"commodityList":[{"gid":"6355","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809e1b95bbb8.jpg","name":"心飞扬保温壶 爱奇屋A1-L28优质双层不锈钢保温壶 12小时保温壶","nowPrice":"88.00","oldPrice":"105.00"}],"page":"1","pageNum":"8"}
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
         * commodityList : [{"gid":"6355","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809e1b95bbb8.jpg","name":"心飞扬保温壶 爱奇屋A1-L28优质双层不锈钢保温壶 12小时保温壶","nowPrice":"88.00","oldPrice":"105.00"}]
         * page : 1
         * pageNum : 8
         */

        private String page;
        private String pageNum;
        private List<CommodityListBean> commodityList;

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getPageNum() {
            return pageNum;
        }

        public void setPageNum(String pageNum) {
            this.pageNum = pageNum;
        }

        public List<CommodityListBean> getCommodityList() {
            return commodityList;
        }

        public void setCommodityList(List<CommodityListBean> commodityList) {
            this.commodityList = commodityList;
        }

        public static class CommodityListBean implements Parcelable {
            /**
             * gid : 6355
             * photoUrl : http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809e1b95bbb8.jpg
             * name : 心飞扬保温壶 爱奇屋A1-L28优质双层不锈钢保温壶 12小时保温壶
             * nowPrice : 88.00
             * oldPrice : 105.00
             */

            private String gid;
            private String photoUrl;
            private String name;
            private String nowPrice;
            private String oldPrice;

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }

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

            public String getNowPrice() {
                return nowPrice;
            }

            public void setNowPrice(String nowPrice) {
                this.nowPrice = nowPrice;
            }

            public String getOldPrice() {
                return oldPrice;
            }

            public void setOldPrice(String oldPrice) {
                this.oldPrice = oldPrice;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.gid);
                dest.writeString(this.photoUrl);
                dest.writeString(this.name);
                dest.writeString(this.nowPrice);
                dest.writeString(this.oldPrice);
            }

            public CommodityListBean() {
            }

            protected CommodityListBean(Parcel in) {
                this.gid = in.readString();
                this.photoUrl = in.readString();
                this.name = in.readString();
                this.nowPrice = in.readString();
                this.oldPrice = in.readString();
            }

            public static final Creator<CommodityListBean> CREATOR = new Creator<CommodityListBean>() {
                @Override
                public CommodityListBean createFromParcel(Parcel source) {
                    return new CommodityListBean(source);
                }

                @Override
                public CommodityListBean[] newArray(int size) {
                    return new CommodityListBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.page);
            dest.writeString(this.pageNum);
            dest.writeList(this.commodityList);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.page = in.readString();
            this.pageNum = in.readString();
            this.commodityList = new ArrayList<CommodityListBean>();
            in.readList(this.commodityList, CommodityListBean.class.getClassLoader());
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

    public Commodity() {
    }

    protected Commodity(Parcel in) {
        this.status = in.readString();
        this.msg = in.readString();
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<Commodity> CREATOR = new Parcelable.Creator<Commodity>() {
        @Override
        public Commodity createFromParcel(Parcel source) {
            return new Commodity(source);
        }

        @Override
        public Commodity[] newArray(int size) {
            return new Commodity[size];
        }
    };
}
