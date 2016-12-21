package com.lishi.baijiaxing.myOrders.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */

public class MyOrderList implements Parcelable {

    /**
     * status : 200
     * msg : 请求数据成功
     * data : {"orderList":[{"oid":"3196","orderNumber":"20161119111250","totalPrice":null,"addtime":"2016-12-19 11:18:50","orderStatus":"1","commodityList":[{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809de1a207a9.jpg","name":"《52个生活小妙招》周历 2017年保险办公记事本周历 挂历 加印LOGO 礼品定制","price":"0.00","buyNum":"1","type":"2","gid":"6346"}]}],"page":"1","pageNum":"1"}
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
         * orderList : [{"oid":"3196","orderNumber":"20161119111250","totalPrice":null,"addtime":"2016-12-19 11:18:50","orderStatus":"1","commodityList":[{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809de1a207a9.jpg","name":"《52个生活小妙招》周历 2017年保险办公记事本周历 挂历 加印LOGO 礼品定制","price":"0.00","buyNum":"1","type":"2","gid":"6346"}]}]
         * page : 1
         * pageNum : 1
         */

        private String page;
        private String pageNum;
        private List<OrderListBean> orderList;

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

        public List<OrderListBean> getOrderList() {
            return orderList;
        }

        public void setOrderList(List<OrderListBean> orderList) {
            this.orderList = orderList;
        }

        public static class OrderListBean implements Parcelable {
            /**
             * oid : 3196
             * orderNumber : 20161119111250
             * totalPrice : 513
             * addtime : 2016-12-19 11:18:50
             * orderStatus : 1
             * commodityList : [{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809de1a207a9.jpg","name":"《52个生活小妙招》周历 2017年保险办公记事本周历 挂历 加印LOGO 礼品定制","price":"0.00","buyNum":"1","type":"2","gid":"6346"}]
             */

            private String oid;
            private String orderNumber;
            private String totalPrice;
            private String addtime;
            private String orderStatus;
            private List<CommodityListBean> commodityList;

            public String getOid() {
                return oid;
            }

            public void setOid(String oid) {
                this.oid = oid;
            }

            public String getOrderNumber() {
                return orderNumber;
            }

            public void setOrderNumber(String orderNumber) {
                this.orderNumber = orderNumber;
            }

            public String getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(String totalPrice) {
                this.totalPrice = totalPrice;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(String orderStatus) {
                this.orderStatus = orderStatus;
            }

            public List<CommodityListBean> getCommodityList() {
                return commodityList;
            }

            public void setCommodityList(List<CommodityListBean> commodityList) {
                this.commodityList = commodityList;
            }

            public static class CommodityListBean implements Parcelable {
                /**
                 * photoUrl : http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809de1a207a9.jpg
                 * name : 《52个生活小妙招》周历 2017年保险办公记事本周历 挂历 加印LOGO 礼品定制
                 * price : 0.00
                 * buyNum : 1
                 * type : 2
                 * gid : 6346
                 */

                private String photoUrl;
                private String name;
                private String price;
                private String buyNum;
                private String type;
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
                    dest.writeString(this.gid);
                }

                public CommodityListBean() {
                }

                protected CommodityListBean(Parcel in) {
                    this.photoUrl = in.readString();
                    this.name = in.readString();
                    this.price = in.readString();
                    this.buyNum = in.readString();
                    this.type = in.readString();
                    this.gid = in.readString();
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
                dest.writeString(this.oid);
                dest.writeString(this.orderNumber);
                dest.writeString(this.totalPrice);
                dest.writeString(this.addtime);
                dest.writeString(this.orderStatus);
                dest.writeList(this.commodityList);
            }

            public OrderListBean() {
            }

            protected OrderListBean(Parcel in) {
                this.oid = in.readString();
                this.orderNumber = in.readString();
                this.totalPrice = in.readParcelable(Object.class.getClassLoader());
                this.addtime = in.readString();
                this.orderStatus = in.readString();
                this.commodityList = new ArrayList<CommodityListBean>();
                in.readList(this.commodityList, CommodityListBean.class.getClassLoader());
            }

            public static final Creator<OrderListBean> CREATOR = new Creator<OrderListBean>() {
                @Override
                public OrderListBean createFromParcel(Parcel source) {
                    return new OrderListBean(source);
                }

                @Override
                public OrderListBean[] newArray(int size) {
                    return new OrderListBean[size];
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
            dest.writeList(this.orderList);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.page = in.readString();
            this.pageNum = in.readString();
            this.orderList = new ArrayList<OrderListBean>();
            in.readList(this.orderList, OrderListBean.class.getClassLoader());
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

    public MyOrderList() {
    }

    protected MyOrderList(Parcel in) {
        this.status = in.readString();
        this.msg = in.readString();
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Creator<MyOrderList> CREATOR = new Creator<MyOrderList>() {
        @Override
        public MyOrderList createFromParcel(Parcel source) {
            return new MyOrderList(source);
        }

        @Override
        public MyOrderList[] newArray(int size) {
            return new MyOrderList[size];
        }
    };
}
