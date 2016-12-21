package com.lishi.baijiaxing.myOrders.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 */

public class OrderDetails implements Parcelable {

    /**
     * status : 200
     * msg : 请求数据成功
     * data : {"oid":"2872","orderNumber":"RS2016111611124872","orderStatus":"1","addr":{"consigneeName":"测试","province":"安徽省","city":"安庆市","district":"枞阳县","details":"嘎嘎嘎嘎","consigneeNumber":"33661111111"},"remark":"","commodityList":[{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582986494626d.jpg","name":"新品舞动阳光保温壶套装 爱奇屋AI-061保冷真空便携杯壶套装","price":"129.00","buyNum":"1","type":"1","gid":"6403"}],"totalPrice":"129.00","invoice":"不发开票","freight":0,"commodityTotalPrice":"129","orderStartTime":"1481858148"}
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
         * oid : 2872
         * orderNumber : RS2016111611124872
         * orderStatus : 1
         * addr : {"consigneeName":"测试","province":"安徽省","city":"安庆市","district":"枞阳县","details":"嘎嘎嘎嘎","consigneeNumber":"33661111111"}
         * remark : 
         * commodityList : [{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582986494626d.jpg","name":"新品舞动阳光保温壶套装 爱奇屋AI-061保冷真空便携杯壶套装","price":"129.00","buyNum":"1","type":"1","gid":"6403"}]
         * totalPrice : 129.00
         * invoice : 不发开票
         * freight : 0
         * commodityTotalPrice : 129
         * orderStartTime : 1481858148
         */

        private String oid;
        private String orderNumber;
        private String orderStatus;
        private AddrBean addr;
        private String remark;
        private String totalPrice;
        private String invoice;
        private int freight;
        private String commodityTotalPrice;
        private String orderStartTime;
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

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public AddrBean getAddr() {
            return addr;
        }

        public void setAddr(AddrBean addr) {
            this.addr = addr;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getInvoice() {
            return invoice;
        }

        public void setInvoice(String invoice) {
            this.invoice = invoice;
        }

        public int getFreight() {
            return freight;
        }

        public void setFreight(int freight) {
            this.freight = freight;
        }

        public String getCommodityTotalPrice() {
            return commodityTotalPrice;
        }

        public void setCommodityTotalPrice(String commodityTotalPrice) {
            this.commodityTotalPrice = commodityTotalPrice;
        }

        public String getOrderStartTime() {
            return orderStartTime;
        }

        public void setOrderStartTime(String orderStartTime) {
            this.orderStartTime = orderStartTime;
        }

        public List<CommodityListBean> getCommodityList() {
            return commodityList;
        }

        public void setCommodityList(List<CommodityListBean> commodityList) {
            this.commodityList = commodityList;
        }

        public static class AddrBean implements Parcelable {
            /**
             * consigneeName : 测试
             * province : 安徽省
             * city : 安庆市
             * district : 枞阳县
             * details : 嘎嘎嘎嘎
             * consigneeNumber : 33661111111
             */

            private String consigneeName;
            private String province;
            private String city;
            private String district;
            private String details;
            private String consigneeNumber;

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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.consigneeName);
                dest.writeString(this.province);
                dest.writeString(this.city);
                dest.writeString(this.district);
                dest.writeString(this.details);
                dest.writeString(this.consigneeNumber);
            }

            public AddrBean() {
            }

            protected AddrBean(Parcel in) {
                this.consigneeName = in.readString();
                this.province = in.readString();
                this.city = in.readString();
                this.district = in.readString();
                this.details = in.readString();
                this.consigneeNumber = in.readString();
            }

            public static final Creator<AddrBean> CREATOR = new Creator<AddrBean>() {
                @Override
                public AddrBean createFromParcel(Parcel source) {
                    return new AddrBean(source);
                }

                @Override
                public AddrBean[] newArray(int size) {
                    return new AddrBean[size];
                }
            };
        }

        public static class CommodityListBean implements Parcelable {
            /**
             * photoUrl : http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582986494626d.jpg
             * name : 新品舞动阳光保温壶套装 爱奇屋AI-061保冷真空便携杯壶套装
             * price : 129.00
             * buyNum : 1
             * type : 1
             * gid : 6403
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

        public DataBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.oid);
            dest.writeString(this.orderNumber);
            dest.writeString(this.orderStatus);
            dest.writeParcelable(this.addr, flags);
            dest.writeString(this.remark);
            dest.writeString(this.totalPrice);
            dest.writeString(this.invoice);
            dest.writeInt(this.freight);
            dest.writeString(this.commodityTotalPrice);
            dest.writeString(this.orderStartTime);
            dest.writeList(this.commodityList);
        }

        protected DataBean(Parcel in) {
            this.oid = in.readString();
            this.orderNumber = in.readString();
            this.orderStatus = in.readString();
            this.addr = in.readParcelable(AddrBean.class.getClassLoader());
            this.remark = in.readString();
            this.totalPrice = in.readString();
            this.invoice = in.readString();
            this.freight = in.readInt();
            this.commodityTotalPrice = in.readString();
            this.orderStartTime = in.readString();
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

    public OrderDetails() {
    }

    protected OrderDetails(Parcel in) {
        this.status = in.readString();
        this.msg = in.readString();
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Creator<OrderDetails> CREATOR = new Creator<OrderDetails>() {
        @Override
        public OrderDetails createFromParcel(Parcel source) {
            return new OrderDetails(source);
        }

        @Override
        public OrderDetails[] newArray(int size) {
            return new OrderDetails[size];
        }
    };
}
