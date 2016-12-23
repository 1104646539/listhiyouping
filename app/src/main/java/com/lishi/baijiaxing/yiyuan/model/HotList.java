package com.lishi.baijiaxing.yiyuan.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/22.
 */

public class HotList implements Parcelable {


    /**
     * status : 200
     * msg : 请求数据成功
     * data : [{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_58577b19eb01b.jpg","name":"一只大象猴赛雷","maxNum":"4","ooid":"57","gid":"6480","nowNum":"0","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_58577b19eb01b.jpg","name":"一只大象猴赛雷","maxNum":"3","ooid":"52","gid":"6480","nowNum":"0","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_58577b19eb01b.jpg","name":"一只大象猴赛雷","maxNum":"5","ooid":"51","gid":"6480","nowNum":"0","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_58577b19eb01b.jpg","name":"一只大象猴赛雷","maxNum":"3","ooid":"50","gid":"6480","nowNum":"2","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_583bde7e4bda2.jpg","name":"迪斯尼 家用滴漏式咖啡机 全自动小型泡茶咖啡壶 包邮","maxNum":"3","ooid":"49","gid":"6465","nowNum":"2","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_583bde7e4bda2.jpg","name":"迪斯尼 家用滴漏式咖啡机 全自动小型泡茶咖啡壶 包邮","maxNum":"3","ooid":"48","gid":"6465","nowNum":"1","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809dc5c63567.jpg","name":"澳洲白泥套组  白品茶忆友陶壶 烧水茶壶泡茶壶茶道专用壶小茶壶套装","maxNum":"3","ooid":"47","gid":"6277","nowNum":"0","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809e186ca1f5.jpg","name":"知音古琴U盘 创意中国结U盘 极速上传下载U盘 16G","maxNum":"3","ooid":"42","gid":"6323","nowNum":"3","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_58574e0b6c178.jpg","name":"大象大象","maxNum":"2","ooid":"41","gid":"6479","nowNum":"0","type":"0"},{"photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_583bde7e4bda2.jpg","name":"迪斯尼 家用滴漏式咖啡机 全自动小型泡茶咖啡壶 包邮","maxNum":"3","ooid":"39","gid":"6465","nowNum":"0","type":"0"}]
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
         * photoUrl : http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_58577b19eb01b.jpg
         * name : 一只大象猴赛雷
         * maxNum : 4
         * ooid : 57
         * gid : 6480
         * nowNum : 0
         * type : 0
         */

        private String photoUrl;
        private String name;
        private String maxNum;
        private String ooid;
        private String gid;
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
            dest.writeString(this.nowNum);
            dest.writeString(this.type);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.photoUrl = in.readString();
            this.name = in.readString();
            this.maxNum = in.readString();
            this.ooid = in.readString();
            this.gid = in.readString();
            this.nowNum = in.readString();
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

    public HotList() {
    }

    protected HotList(Parcel in) {
        this.status = in.readString();
        this.msg = in.readString();
        this.data = new ArrayList<DataBean>();
        in.readList(this.data, DataBean.class.getClassLoader());
    }

    public static final Creator<HotList> CREATOR = new Creator<HotList>() {
        @Override
        public HotList createFromParcel(Parcel source) {
            return new HotList(source);
        }

        @Override
        public HotList[] newArray(int size) {
            return new HotList[size];
        }
    };
}
