package com.lishi.baijiaxing.customize.model;

import com.lishi.baijiaxing.base.BaseBean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/26.
 */
public class CustomizeCommodityBean extends BaseBean {
    /**
     * 产品图片
     */
    private String photoUrl;
    /**
     * 产品价格
     */
    private String price;
    /**
     * 生产周期
     */
    private int cycle;
    /**
     * 起订数量
     */
    private int startNum;

    /**
     * 手工费
     */
    private int manualPrice;

    /**
     * 产品简介
     */
    private String commodityBrief;

    /**
     * 规格
     */

    private String norms;

    /**
     * 已选数量
     */
    private int num;
    /**
     * 评价
     */
    private String comment;

    /**
     * 产品详细介绍
     */
    private List<Integer> srcs;

    /**
     * 规格
     *
     * @return
     */
    private List<NormsBean> normsBeens;

    /**
     * 购买的数量
     *
     * @return
     */
    private int config_num;

    public int getConfig_num() {
        return config_num;
    }

    public void setConfig_num(int config_num) {
        this.config_num = config_num;
    }

    public List<NormsBean> getNormsBeens() {
        return normsBeens;
    }

    public void setNormsBeens(List<NormsBean> normsBeens) {
        this.normsBeens = normsBeens;
    }

    public CustomizeCommodityBean(String photoUrl, String price, int cycle, int startNum, int manualPrice, String commodityBrief, String norms, int num, String comment, List<Integer> srcs, List<NormsBean> normsBeens) {
        this.photoUrl = photoUrl;
        this.price = price;
        this.cycle = cycle;
        this.startNum = startNum;
        this.manualPrice = manualPrice;
        this.commodityBrief = commodityBrief;
        this.norms = norms;
        this.num = num;
        this.comment = comment;
        this.srcs = srcs;
        this.normsBeens = normsBeens;
        this.config_num = 1;
    }

    public String getNorms() {
        return norms;
    }

    public void setNorms(String norms) {
        this.norms = norms;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public CustomizeCommodityBean() {
    }


    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public int getManualPrice() {
        return manualPrice;
    }

    public void setManualPrice(int manualPrice) {
        this.manualPrice = manualPrice;
    }

    public String getCommodityBrief() {
        return commodityBrief;
    }

    public void setCommodityBrief(String commodityBrief) {
        this.commodityBrief = commodityBrief;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Integer> getSrcs() {
        return srcs;
    }

    public void setSrcs(List<Integer> srcs) {
        this.srcs = srcs;
    }
}

