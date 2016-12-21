package com.lishi.baijiaxing.home.model;

import java.util.List;

/**首页轮播图
 * Created by Administrator on 2016/11/23.
 */

public class AdList {

    /**
     * status : 200
     * msg : 请求数据成功
     * data : [{"cid":"171","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/ads/s_5836af6670061.jpg"},{"cid":"167","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/ads/s_582ab33c5c6c3.jpg"},{"cid":"172","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/ads/s_582aa0e12ed06.jpg"},{"cid":"174","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/ads/s_58259d977c3cd.jpg"},{"cid":"178","photoUrl":"http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/ads/s_5836acd8b91a6.jpg"}]
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
         * cid : 171
         * photoUrl : http://risevip.oss-cn-shenzhen.aliyuncs.com/Uploads/ads/s_5836af6670061.jpg
         */

        private String gid;
        private String photoUrl;

        public String getCid() {
            return gid;
        }

        public void setCid(String cid) {
            this.gid = cid;
        }

        public String getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }
    }
}
