package com.lishi.baijiaxing.free.model;

import com.lishi.baijiaxing.base.BaseBean;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/19.
 */
public class FreeDetailsBean extends BaseBean {
    /**
     * 轮播图
     */
    JSONArray mJSONArray;
    /**
     * 商品名
     */
    String name;

    /**
     * 限量
     */
    int limitNum;
    /**
     * 已申请人数
     */
    int peopleNum;

    /**
     * 市场价
     */
    int price;

    /**
     * 类型
     */
    int type;

    /**
     * 商品介绍
     */
    ArrayList<Integer> mSrcs;

    public FreeDetailsBean() {
    }

    public FreeDetailsBean(JSONArray JSONArray, String name, int limitNum, int peopleNum, int price, int type, ArrayList<Integer> srcs) {
        mJSONArray = JSONArray;
        this.name = name;
        this.limitNum = limitNum;
        this.peopleNum = peopleNum;
        this.price = price;
        this.type = type;
        mSrcs = srcs;
    }

    public JSONArray getJSONArray() {
        return mJSONArray;
    }

    public void setJSONArray(JSONArray JSONArray) {
        mJSONArray = JSONArray;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(int limitNum) {
        this.limitNum = limitNum;
    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<Integer> getSrcs() {
        return mSrcs;
    }

    public void setSrcs(ArrayList<Integer> srcs) {
        mSrcs = srcs;
    }
}
