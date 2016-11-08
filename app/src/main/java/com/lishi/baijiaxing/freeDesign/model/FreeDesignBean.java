package com.lishi.baijiaxing.freeDesign.model;

import com.lishi.baijiaxing.base.BaseBean;

/**
 * Created by Administrator on 2016/11/7.
 */
public class FreeDesignBean extends BaseBean {
    private int photoUrl;
    private String name;
    private String brief;
    private String num;
    private String oldPrice;
    private String nowPrice;
    private String info;

    public FreeDesignBean(int photoUrl, String name, String brief, String num, String oldPrice, String nowPrice, String info) {
        this.photoUrl = photoUrl;
        this.name = name;
        this.brief = brief;
        this.num = num;
        this.oldPrice = oldPrice;
        this.nowPrice = nowPrice;
        this.info = info;
    }

    public int getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(int photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(String nowPrice) {
        this.nowPrice = nowPrice;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
