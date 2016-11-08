package com.lishi.baijiaxing.yiyuan.model;

import com.lishi.baijiaxing.base.BaseBean;

/**
 * Created by Administrator on 2016/10/21.
 */
public class YiYuanHotBean extends BaseBean {

    private String photo;
    private String name;
    private int max_num;
    private int now_num;

    public YiYuanHotBean(String photo, String name, int max_num, int now_num) {
        this.photo = photo;
        this.name = name;
        this.max_num = max_num;
        this.now_num = now_num;
    }

    public YiYuanHotBean() {
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMax_num() {
        return max_num;
    }

    public void setMax_num(int max_num) {
        this.max_num = max_num;
    }

    public int getNow_num() {
        return now_num;
    }

    public void setNow_num(int now_num) {
        this.now_num = now_num;
    }
}
