package com.lishi.baijiaxing.latest.model;

import com.lishi.baijiaxing.base.BaseBean;

/**
 * Created by Administrator on 2016/11/4.
 */
public class LatestCommodityBean extends BaseBean {
    private String name;
    private String photoUrl;
    private String gid;
    private String price;
    private String brief;

    public LatestCommodityBean(String name, String photoUrl, String cid, String price, String brief) {
        this.name = name;
        this.photoUrl = photoUrl;
        this.gid = cid;
        this.price = price;
        this.brief = brief;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
}
