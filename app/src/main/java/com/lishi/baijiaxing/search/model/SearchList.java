package com.lishi.baijiaxing.search.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/19.
 */

public class SearchList implements Parcelable {

    /**
     * status : 200
     * msg : 请求数据成功
     * data : {"searchName":"虎","pageNum":"1","page":"1","commodityList":[{"gid":"6290","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809dfdb228b5.jpg","name":"卧虎藏龙杯 紫砂礼品 办公室紫砂茶杯 送礼过滤隔片杯 包邮","nowPrice":"328.00","oldPrice":"393.00"}]}
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
         * searchName : 虎
         * pageNum : 1
         * page : 1
         * commodityList : [{"gid":"6290","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809dfdb228b5.jpg","name":"卧虎藏龙杯 紫砂礼品 办公室紫砂茶杯 送礼过滤隔片杯 包邮","nowPrice":"328.00","oldPrice":"393.00"}]
         */

        private String searchName;
        private String pageNum;
        private String page;
        private List<CommodityListBean> commodityList;

        public String getSearchName() {
            return searchName;
        }

        public void setSearchName(String searchName) {
            this.searchName = searchName;
        }

        public String getPageNum() {
            return pageNum;
        }

        public void setPageNum(String pageNum) {
            this.pageNum = pageNum;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public List<CommodityListBean> getCommodityList() {
            return commodityList;
        }

        public void setCommodityList(List<CommodityListBean> commodityList) {
            this.commodityList = commodityList;
        }

        public static class CommodityListBean implements Parcelable {
            /**
             * gid : 6290
             * photoUrl : http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809dfdb228b5.jpg
             * name : 卧虎藏龙杯 紫砂礼品 办公室紫砂茶杯 送礼过滤隔片杯 包邮
             * nowPrice : 328.00
             * oldPrice : 393.00
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
            dest.writeString(this.searchName);
            dest.writeString(this.pageNum);
            dest.writeString(this.page);
            dest.writeList(this.commodityList);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.searchName = in.readString();
            this.pageNum = in.readString();
            this.page = in.readString();
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

    public SearchList() {
    }

    protected SearchList(Parcel in) {
        this.status = in.readString();
        this.msg = in.readString();
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<SearchList> CREATOR = new Parcelable.Creator<SearchList>() {
        @Override
        public SearchList createFromParcel(Parcel source) {
            return new SearchList(source);
        }

        @Override
        public SearchList[] newArray(int size) {
            return new SearchList[size];
        }
    };
}
