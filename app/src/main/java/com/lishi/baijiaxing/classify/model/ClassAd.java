package com.lishi.baijiaxing.classify.model;

import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */

public class ClassAd {

    /**
     * status : 200
     * msg : 请求数据成功
     * data : [{"url":"www.risevip.com/Public/wap/otherImg/ad_pro1.jpg","cid":""}]
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
         * url : www.risevip.com/Public/wap/otherImg/ad_pro1.jpg
         * cid : 
         */

        private String url;
        private String gid;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCid() {
            return gid;
        }

        public void setCid(String cid) {
            this.gid = cid;
        }
    }
}
