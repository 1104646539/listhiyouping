package com.lishi.baijiaxing.home.model;

import java.util.List;

/**
 * 首页节日
 * Created by Administrator on 2016/11/23.
 */

public class Festival {

    /**
     * status : 200
     * msg : 请求数据成功
     * data : [{"festivalName":"圣诞节","festibalTime":"1482595199"}]
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
         * festivalName : 圣诞节
         * festibalTime : 1482595199
         */

        private String festivalName;
        private String festibalTime;

        public String getFestivalName() {
            return festivalName;
        }

        public void setFestivalName(String festivalName) {
            this.festivalName = festivalName;
        }

        public String getFestibalTime() {
            return festibalTime;
        }

        public void setFestibalTime(String festibalTime) {
            this.festibalTime = festibalTime;
        }
    }
}
