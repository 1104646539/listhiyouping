package com.lishi.baijiaxing.home.model;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */

public class Seckill {

    /**
     * status : 200
     * msg : 请求数据成功
     * data : {"seckillTime":"1480492431","seckillList":[{"cid":"6351","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582aa1493be4c.jpg","nowPrice":"100","oldPrice":"68.00"},{"cid":"6298","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_58354a0e06539.JPG","nowPrice":"150","oldPrice":"168.00"},{"cid":"6351","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582aa1493be4c.jpg","nowPrice":"300","oldPrice":"68.00"},{"cid":"6311","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582d4eb817de4.jpg","nowPrice":"20","oldPrice":"280.00"},{"cid":"6314","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809df18a250a.jpg","nowPrice":"360","oldPrice":"23.50"},{"cid":"6391","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_58251faeeaf60.jpg","nowPrice":"500","oldPrice":"275.00"},{"cid":"6446","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582d70fc7d7eb.jpg","nowPrice":"800","oldPrice":"333.00"}]}
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

    public static class DataBean {
        /**
         * seckillTime : 1480492431
         * seckillList : [{"cid":"6351","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582aa1493be4c.jpg","nowPrice":"100","oldPrice":"68.00"},{"cid":"6298","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_58354a0e06539.JPG","nowPrice":"150","oldPrice":"168.00"},{"cid":"6351","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582aa1493be4c.jpg","nowPrice":"300","oldPrice":"68.00"},{"cid":"6311","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582d4eb817de4.jpg","nowPrice":"20","oldPrice":"280.00"},{"cid":"6314","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_5809df18a250a.jpg","nowPrice":"360","oldPrice":"23.50"},{"cid":"6391","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_58251faeeaf60.jpg","nowPrice":"500","oldPrice":"275.00"},{"cid":"6446","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582d70fc7d7eb.jpg","nowPrice":"800","oldPrice":"333.00"}]
         */

        private String seckillTime;
        private List<SeckillListBean> seckillList;

        public String getSeckillTime() {
            return seckillTime;
        }

        public void setSeckillTime(String seckillTime) {
            this.seckillTime = seckillTime;
        }

        public List<SeckillListBean> getSeckillList() {
            return seckillList;
        }

        public void setSeckillList(List<SeckillListBean> seckillList) {
            this.seckillList = seckillList;
        }

        public static class SeckillListBean {
            /**
             * cid : 6351
             * photoUrl : http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/goods/m_582aa1493be4c.jpg
             * nowPrice : 100
             * oldPrice : 68.00
             */

            private String gid;
            private String photoUrl;
            private String nowPrice;
            private String oldPrice;

            public String getCid() {
                return gid;
            }

            public void setCid(String gid) {
                this.gid = gid;
            }

            public String getPhotoUrl() {
                return photoUrl;
            }

            public void setPhotoUrl(String photoUrl) {
                this.photoUrl = photoUrl;
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
}
