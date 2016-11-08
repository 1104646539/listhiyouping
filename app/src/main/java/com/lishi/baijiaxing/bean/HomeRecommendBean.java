package com.lishi.baijiaxing.bean;

/**
 * Created by Administrator on 2016/6/7.
 */
public class HomeRecommendBean {
    private int newprice;//打折后的价格
    private int price;//原来的价格
    private int imgurl;//商品图片
    private String brief;//商品简介

    public HomeRecommendBean(int newprice, int price, int imgurl, String brief) {
        this.newprice = newprice;
        this.price = price;
        this.imgurl = imgurl;
        this.brief = brief;
    }

    public HomeRecommendBean() {
        
    }

    public int getNewprice() {
        return newprice;
    }

    public void setNewprice(int newprice) {
        this.newprice = newprice;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImgurl() {
        return imgurl;
    }

    public void setImgurl(int imgurl) {
        this.imgurl = imgurl;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
}
