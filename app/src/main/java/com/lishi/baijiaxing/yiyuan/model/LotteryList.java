package com.lishi.baijiaxing.yiyuan.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/22.
 */

public class LotteryList implements Parcelable {

    /**
     * status : 200
     * msg : 请求数据成功
     * data : {"commodityList":[{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_58577b19eb01b.jpg","name":"一只大象猴赛雷","maxNum":"4","ooid":"57","gid":"6480","winningUser":"呱呱呱","nowNum":"4","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582564a4455f2.jpg","name":"利世卡塞纳伞\u2014\u2014在雨中优雅着  中国高端商务伞具价值风范 不滴水也吹不翻的伞","maxNum":"3","ooid":"56","gid":"6395","winningUser":"昏昏 Vera","nowNum":"3","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582564a4455f2.jpg","name":"利世卡塞纳伞\u2014\u2014在雨中优雅着  中国高端商务伞具价值风范 不滴水也吹不翻的伞","maxNum":"3","ooid":"55","gid":"6395","winningUser":"黄贤俊","nowNum":"3","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809e1394c6c5.jpg","name":"雅趣双层茶叶罐 高端茶叶罐 普洱醒茶罐 密封茶叶罐 包邮","maxNum":"3","ooid":"54","gid":"6324","winningUser":"呱呱呱","nowNum":"3","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582564a4455f2.jpg","name":"利世卡塞纳伞\u2014\u2014在雨中优雅着  中国高端商务伞具价值风范 不滴水也吹不翻的伞","maxNum":"3","ooid":"53","gid":"6395","winningUser":"呱呱呱","nowNum":"3","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809df18a250a.jpg","name":"开心一家饭勺套装|不锈钢家庭饭勺套装","maxNum":"3","ooid":"46","gid":"6314","winningUser":"不动峰","nowNum":"3","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809dad74e031.jpg","name":" 感恩有你纯手工茶叶罐 精品普洱罐 密封醒茶罐 商务礼品 包邮","maxNum":"3","ooid":"45","gid":"6321","winningUser":null,"nowNum":"3","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809e1109ecdd.jpg","name":"USB快充小夜灯 光控夜灯 多孔快充 包邮","maxNum":"3","ooid":"44","gid":"6322","winningUser":null,"nowNum":"3","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809e1394c6c5.jpg","name":"雅趣双层茶叶罐 高端茶叶罐 普洱醒茶罐 密封茶叶罐 包邮","maxNum":"3","ooid":"43","gid":"6324","winningUser":"呱呱呱","nowNum":"2","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_58574e0b6c178.jpg","name":"大象大象","maxNum":"2","ooid":"40","gid":"6479","winningUser":"昏昏 Vera","nowNum":"2","type":"0"}],"page":"1","pageNum":"1"}
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
         * commodityList : [{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_58577b19eb01b.jpg","name":"一只大象猴赛雷","maxNum":"4","ooid":"57","gid":"6480","winningUser":"呱呱呱","nowNum":"4","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582564a4455f2.jpg","name":"利世卡塞纳伞\u2014\u2014在雨中优雅着  中国高端商务伞具价值风范 不滴水也吹不翻的伞","maxNum":"3","ooid":"56","gid":"6395","winningUser":"昏昏 Vera","nowNum":"3","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582564a4455f2.jpg","name":"利世卡塞纳伞\u2014\u2014在雨中优雅着  中国高端商务伞具价值风范 不滴水也吹不翻的伞","maxNum":"3","ooid":"55","gid":"6395","winningUser":"黄贤俊","nowNum":"3","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809e1394c6c5.jpg","name":"雅趣双层茶叶罐 高端茶叶罐 普洱醒茶罐 密封茶叶罐 包邮","maxNum":"3","ooid":"54","gid":"6324","winningUser":"呱呱呱","nowNum":"3","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582564a4455f2.jpg","name":"利世卡塞纳伞\u2014\u2014在雨中优雅着  中国高端商务伞具价值风范 不滴水也吹不翻的伞","maxNum":"3","ooid":"53","gid":"6395","winningUser":"呱呱呱","nowNum":"3","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809df18a250a.jpg","name":"开心一家饭勺套装|不锈钢家庭饭勺套装","maxNum":"3","ooid":"46","gid":"6314","winningUser":"不动峰","nowNum":"3","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809dad74e031.jpg","name":" 感恩有你纯手工茶叶罐 精品普洱罐 密封醒茶罐 商务礼品 包邮","maxNum":"3","ooid":"45","gid":"6321","winningUser":null,"nowNum":"3","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809e1109ecdd.jpg","name":"USB快充小夜灯 光控夜灯 多孔快充 包邮","maxNum":"3","ooid":"44","gid":"6322","winningUser":null,"nowNum":"3","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809e1394c6c5.jpg","name":"雅趣双层茶叶罐 高端茶叶罐 普洱醒茶罐 密封茶叶罐 包邮","maxNum":"3","ooid":"43","gid":"6324","winningUser":"呱呱呱","nowNum":"2","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_58574e0b6c178.jpg","name":"大象大象","maxNum":"2","ooid":"40","gid":"6479","winningUser":"昏昏 Vera","nowNum":"2","type":"0"}]
         * page : 1
         * pageNum : 1
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
             * photoUrl : http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_58577b19eb01b.jpg
             * name : 一只大象猴赛雷
             * maxNum : 4
             * ooid : 57
             * gid : 6480
             * winningUser : 呱呱呱
             * nowNum : 4
             * type : 0
             */

            private String photoUrl;
            private String name;
            private String maxNum;
            private String ooid;
            private String gid;
            private String winningUser;
            private String nowNum;
            private String type;

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

            public String getMaxNum() {
                return maxNum;
            }

            public void setMaxNum(String maxNum) {
                this.maxNum = maxNum;
            }

            public String getOoid() {
                return ooid;
            }

            public void setOoid(String ooid) {
                this.ooid = ooid;
            }

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }

            public String getWinningUser() {
                return winningUser;
            }

            public void setWinningUser(String winningUser) {
                this.winningUser = winningUser;
            }

            public String getNowNum() {
                return nowNum;
            }

            public void setNowNum(String nowNum) {
                this.nowNum = nowNum;
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
                dest.writeString(this.photoUrl);
                dest.writeString(this.name);
                dest.writeString(this.maxNum);
                dest.writeString(this.ooid);
                dest.writeString(this.gid);
                dest.writeString(this.winningUser);
                dest.writeString(this.nowNum);
                dest.writeString(this.type);
            }

            public CommodityListBean() {
            }

            protected CommodityListBean(Parcel in) {
                this.photoUrl = in.readString();
                this.name = in.readString();
                this.maxNum = in.readString();
                this.ooid = in.readString();
                this.gid = in.readString();
                this.winningUser = in.readString();
                this.nowNum = in.readString();
                this.type = in.readString();
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

    public LotteryList() {
    }

    protected LotteryList(Parcel in) {
        this.status = in.readString();
        this.msg = in.readString();
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Creator<LotteryList> CREATOR = new Creator<LotteryList>() {
        @Override
        public LotteryList createFromParcel(Parcel source) {
            return new LotteryList(source);
        }

        @Override
        public LotteryList[] newArray(int size) {
            return new LotteryList[size];
        }
    };
}
