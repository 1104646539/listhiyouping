package com.lishi.baijiaxing.details.model;

import com.lishi.baijiaxing.base.BaseBean;
import com.lishi.baijiaxing.customize.model.NormsBean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 */
public class CommodityDetailsBean extends BaseBean {
    private String photoUrl;
    private String name;
    private int price;
    private List<Integer> brief;
    /**
     * 规格
     */
    private String norms;
    private List<NormsBean> normsBeens;
    /**
     * 库存
     */
    private int inventory;

    public CommodityDetailsBean(String photoUrl, String name, int price, List<Integer> brief, String norms, List<NormsBean> normsBeens, int inventory) {
        this.photoUrl = photoUrl;
        this.name = name;
        this.price = price;
        this.brief = brief;
        this.norms = norms;
        this.normsBeens = normsBeens;
        this.inventory = inventory;
    }

    public int getInventory() {

        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public CommodityDetailsBean() {

    }

    public List<NormsBean> getNormsBeens() {
        return normsBeens;
    }

    public void setNormsBeens(List<NormsBean> normsBeens) {
        this.normsBeens = normsBeens;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getNorms() {
        return norms;
    }

    public void setNorms(String norms) {
        this.norms = norms;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Integer> getBrief() {
        return brief;
    }

    public void setBrief(List<Integer> brief) {
        this.brief = brief;
    }
}
