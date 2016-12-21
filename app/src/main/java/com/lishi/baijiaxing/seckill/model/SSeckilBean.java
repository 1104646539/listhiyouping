package com.lishi.baijiaxing.seckill.model;

import com.lishi.baijiaxing.base.BaseBean;

/**秒杀
 * Created by Administrator on 2016/11/22.
 */

public class SSeckilBean extends BaseBean {
    String photoUrl;
    String gid;
    String name;
    String nowPrice;
    String oldPrice;
    /**
     * 需要的数量
     */
    String totalNum;
    /**
     * 已售完的数量
     */
    String soldNum;

    public SSeckilBean(String photoUrl, String cid, String name, String nowPrice, String oldPrice, String totalNum, String soldNum) {
        this.photoUrl = photoUrl;
        this.gid = cid;
        this.name = name;
        this.nowPrice = nowPrice;
        this.oldPrice = oldPrice;
        this.totalNum = totalNum;
        this.soldNum = soldNum;
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

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public String getSoldNum() {
        return soldNum;
    }

    public void setSoldNum(String soldNum) {
        this.soldNum = soldNum;
    }
    
}
