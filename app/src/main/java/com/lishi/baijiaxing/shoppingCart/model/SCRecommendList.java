package com.lishi.baijiaxing.shoppingCart.model;

import java.util.List;

/**
 * Created by Administrator on 2016/12/5.
 */

public class SCRecommendList {

    /**
     * status : 200
     * msg : 请求数据成功
     * data : [{"gid":"6359","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809d7662193c.jpg","name":"尚雅保温壶 爱奇屋A1-12 1L不锈钢真空咖啡壶 保温壶 户外旅游壶","nowPrice":"68.00","oldPrice":"81.00"},{"gid":"6358","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809d79e09f5c.jpg","name":"开门红保温杯 爱奇屋A1-B350不锈钢真空500ML水杯旅行杯","nowPrice":"68.00","oldPrice":"81.00"}]
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

    public static class DataBean {
        /**
         * gid : 6359
         * photoUrl : http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809d7662193c.jpg
         * name : 尚雅保温壶 爱奇屋A1-12 1L不锈钢真空咖啡壶 保温壶 户外旅游壶
         * nowPrice : 68.00
         * oldPrice : 81.00
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
    }
}
