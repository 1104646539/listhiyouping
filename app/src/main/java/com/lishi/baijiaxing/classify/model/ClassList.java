package com.lishi.baijiaxing.classify.model;

import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */

public class ClassList {

    /**
     * status : 200
     * msg : 请求数据成功
     * data : [{"classifyname":"礼品","classifyid":"8"},{"classifyname":"箱包","classifyid":"25"},{"classifyname":"数码用品","classifyid":"29"},{"classifyname":"文化生活","classifyid":"42"},{"classifyname":"饰品","classifyid":"56"},{"classifyname":"食品、酒类、特产","classifyid":"74"},{"classifyname":"居家日用","classifyid":"87"},{"classifyname":"汽车用品","classifyid":"98"},{"classifyname":"个性定制","classifyid":"102"},{"classifyname":"图书","classifyid":"131"}]
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
         * classifyname : 礼品
         * classifyid : 8
         */

        private String classifyname;
        private String classifyid;

        public String getClassifyname() {
            return classifyname;
        }

        public void setClassifyname(String classifyname) {
            this.classifyname = classifyname;
        }

        public String getClassifyid() {
            return classifyid;
        }

        public void setClassifyid(String classifyid) {
            this.classifyid = classifyid;
        }
    }
}
