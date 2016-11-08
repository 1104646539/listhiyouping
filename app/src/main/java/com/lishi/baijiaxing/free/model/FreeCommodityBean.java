package com.lishi.baijiaxing.free.model;

import com.lishi.baijiaxing.base.BaseBean;

/**
 * 免费领
 * Created by Administrator on 2016/10/17.
 */
public class FreeCommodityBean extends BaseBean {
    //数据类型
    /**
     * 即将开始
     */
    public static final int TYPE_START_BEFORE = 0;
    /**
     * 已结束
     */
    public static final int TYPE_FINISH = 3;//
    /**
     * 进行中，已申请
     */
    public static final int TYPE_BE_BEING_APPLY_OK = 1;//
    /**
     * 进行中，未申请
     */
    public static final int TYPE_BE_BEING_APPLY_NOT = 2;//


    /**
     * 数据类型
     */
    private int type;

    /**
     * 商品名
     */
    private String name;
    /**
     * 商品图片
     */
    private String photoUrl;
    /**
     * 商品原价
     */
    private int price;

    /**
     * 已申请人数
     */
    private int peopleNum;

    /**
     * 限量
     */
    private int limitNum;

    public FreeCommodityBean(int type, String name, String photoUrl, int price, int peopleNum, int limitNum) {
        this.type = type;
        this.name = name;
        this.photoUrl = photoUrl;
        this.price = price;
        this.peopleNum = peopleNum;
        this.limitNum = limitNum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }

    public int getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(int limitNum) {
        this.limitNum = limitNum;
    }
}
