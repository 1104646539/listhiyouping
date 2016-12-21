package com.lishi.baijiaxing.hot.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */

public class HotCommodity implements Parcelable {

    /**
     * status : 200
     * msg : 请求数据成功
     * data : [{"gid":"6395","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582564a4455f2.jpg","name":"利世卡塞纳伞\u2014\u2014在雨中优雅着  中国高端商务伞具价值风范 不滴水也吹不翻的伞","nowPrice":"98.00","oldPrice":"159.90","salesVolume":"13693","type":1},{"gid":"6303","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809e13462b5f.jpg","name":"花艺暖茶 玫瑰组合花茶红巧梅金盏菊茉莉花贡菊花女性美容养颜养生花草茶","nowPrice":"98.00","oldPrice":"118.00","salesVolume":"11698","type":1},{"gid":"6369","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5822ce189d665.jpg","name":"爱华仕360°全方位电脑保护双肩包 时尚撞色大容量韩版旅行休闲双肩背包","nowPrice":"138.00","oldPrice":"159.00","salesVolume":"11328","type":1},{"gid":"6314","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809df18a250a.jpg","name":"开心一家饭勺套装|不锈钢家庭饭勺套装","nowPrice":"23.50","oldPrice":"28.20","salesVolume":"10378","type":1},{"gid":"6298","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_58354a0e06539.JPG","name":"虎符龙节套装  极速上传下载 高大上龙节笔 公虎符龙节套装 包邮","nowPrice":"188.00","oldPrice":"208.00","salesVolume":"9686","type":1},{"gid":"6347","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809de6f0c85b.jpg","name":"开门红福袋2017鸡年开门红保险大礼包五件套 春联定制","nowPrice":"12.00","oldPrice":"18.00","salesVolume":"9536","type":1},{"gid":"6333","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5823d72902939.jpg","name":"56°恒温紫砂杯CEO杯 养生杯 办公室专用茶杯 全手工制作高端定制礼品","nowPrice":"398.00","oldPrice":"478.00","salesVolume":"8596","type":1},{"gid":"6342","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809de3019510.JPG","name":"钱到家钱道佳开光真品保真旺财旺偏财纯铜铜钱古币正品挂件","nowPrice":"18.00","oldPrice":"22.00","salesVolume":"8537","type":1},{"gid":"6349","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5832abc34e074.jpg","name":"小鸡也智慧台历 2017新年台历周历挂历保险礼品赠送礼品定制","nowPrice":"12.00","oldPrice":"10.80","salesVolume":"7568","type":1},{"gid":"6357","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809d7cc8eb10.jpg","name":"洪荒之力说保险台历2017鸡年新台历 日历 保险开门红礼品 礼品定制","nowPrice":"16.00","oldPrice":"20.00","salesVolume":"6681","type":1},{"gid":"6356","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5832abfe7a137.jpg","name":"现代生活黄历2017年老黄历 择吉通胜日历 年历 礼品定制","nowPrice":"8.80","oldPrice":"10.00","salesVolume":"6183","type":1},{"gid":"6348","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809de87b7a2d.jpg","name":"利世新语2017开门红挂历山水画挂历 台历 礼品定制","nowPrice":"18.00","oldPrice":"28.00","salesVolume":"5569","type":1},{"gid":"6360","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809d6afde3f7.jpg","name":"金鸡报晓福字挂牌2017年保险挂历定制 挂轴挂历 台历 加印LOGO 礼品定制","nowPrice":"18.00","oldPrice":"21.00","salesVolume":"4589","type":1},{"gid":"6311","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582d4eb817de4.jpg","name":"LED月光灯简约现代客厅灯卧室灯艾普达装饰灯 手机阅读护眼灯","nowPrice":"280.00","oldPrice":"336.00","salesVolume":"3234","type":1}]
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
         * gid : 6395
         * photoUrl : http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582564a4455f2.jpg
         * name : 利世卡塞纳伞——在雨中优雅着  中国高端商务伞具价值风范 不滴水也吹不翻的伞
         * nowPrice : 98.00
         * oldPrice : 159.90
         * salesVolume : 13693
         * type : 1
         */

        private String gid;
        private String photoUrl;
        private String name;
        private String nowPrice;
        private String oldPrice;
        private String salesVolume;
        private int type;

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

        public String getSalesVolume() {
            return salesVolume;
        }

        public void setSalesVolume(String salesVolume) {
            this.salesVolume = salesVolume;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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
            dest.writeString(this.salesVolume);
            dest.writeInt(this.type);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.gid = in.readString();
            this.photoUrl = in.readString();
            this.name = in.readString();
            this.nowPrice = in.readString();
            this.oldPrice = in.readString();
            this.salesVolume = in.readString();
            this.type = in.readInt();
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

    public HotCommodity() {
    }

    protected HotCommodity(Parcel in) {
        this.status = in.readString();
        this.msg = in.readString();
        this.data = new ArrayList<DataBean>();
        in.readList(this.data, DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<HotCommodity> CREATOR = new Parcelable.Creator<HotCommodity>() {
        @Override
        public HotCommodity createFromParcel(Parcel source) {
            return new HotCommodity(source);
        }

        @Override
        public HotCommodity[] newArray(int size) {
            return new HotCommodity[size];
        }
    };
}
