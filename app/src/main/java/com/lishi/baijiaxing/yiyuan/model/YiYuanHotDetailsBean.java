package com.lishi.baijiaxing.yiyuan.model;

import com.lishi.baijiaxing.base.BaseBean;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/24.
 */
public class YiYuanHotDetailsBean extends BaseBean {
    /**
     * 轮播图
     */
    JSONArray mJSONArray;
    /**
     * 商品名
     */
    String name;

    /**
     * 期号
     */
    int num;

    /**
     * 总需份数
     */
    int needNum;

    /**
     * 剩余份数
     */
    int surplusNum;

    /**
     * 我参与的份数
     */
    int participationNum;

    /**
     * 夺宝号码
     */
    String winningNum;

    /**
     * 类型
     */
    int type;

    /**
     * 参与记录时间
     */
    String recordTime;

    /**
     * 商品介绍
     */
    ArrayList<Integer> mSrcs;

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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNeedNum() {
        return needNum;
    }

    public void setNeedNum(int needNum) {
        this.needNum = needNum;
    }

    public int getSurplusNum() {
        return surplusNum;
    }

    public void setSurplusNum(int surplusNum) {
        this.surplusNum = surplusNum;
    }

    public int getParticipationNum() {
        return participationNum;
    }

    public void setParticipationNum(int participationNum) {
        this.participationNum = participationNum;
    }

    public String getWinningNum() {
        return winningNum;
    }

    public void setWinningNum(String winningNum) {
        this.winningNum = winningNum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public ArrayList<Integer> getSrcs() {
        return mSrcs;
    }

    public void setSrcs(ArrayList<Integer> srcs) {
        mSrcs = srcs;
    }

    public YiYuanHotDetailsBean(JSONArray JSONArray, String name, int num, int needNum, int surplusNum, int participationNum, String winningNum, int type, String recordTime, ArrayList<Integer> srcs) {

        mJSONArray = JSONArray;
        this.name = name;
        this.num = num;
        this.needNum = needNum;
        this.surplusNum = surplusNum;
        this.participationNum = participationNum;
        this.winningNum = winningNum;
        this.type = type;
        this.recordTime = recordTime;
        mSrcs = srcs;
    }

    public YiYuanHotDetailsBean() {
    }


}
