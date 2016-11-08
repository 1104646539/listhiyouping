package com.lishi.baijiaxing.bean;

import com.lishi.baijiaxing.R;

import java.util.List;

/**
 * 普世分类数据
 * Created by Administrator on 2016/7/4.
 */
public class MeaningfulBean {
    private int icon;
    private int briefImg;
    private List<Integer> goods;
    private int buy = R.drawable.buy_meaningful;

    public MeaningfulBean(int icon, int briefImg, List<Integer> goods) {
        this.icon = icon;
        this.briefImg = briefImg;
        this.goods = goods;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getBriefImg() {
        return briefImg;
    }

    public void setBriefImg(int briefImg) {
        this.briefImg = briefImg;
    }

    public List<Integer> getGoods() {
        return goods;
    }

    public void setGoods(List<Integer> goods) {
        this.goods = goods;
    }

    public int getBuy() {
        return buy;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }
}
