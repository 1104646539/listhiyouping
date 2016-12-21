package com.lishi.baijiaxing.home.model;

/**
 * 首页秒杀显示
 * Created by Administrator on 2016/11/22.
 */
public class HomeSeckilBean {
    String photoUrl;
    String gid;
    String name;
    String nowPrice;
    String oldPrice;

    public HomeSeckilBean(String photoUrl, String gid, String name, String nowPrice, String oldPrice) {
        this.photoUrl = photoUrl;
        this.gid = gid;
        this.name = name;
        this.nowPrice = nowPrice;
        this.oldPrice = oldPrice;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getCid() {
        return gid;
    }

    public void setCid(String cid) {
        this.gid = cid;
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
