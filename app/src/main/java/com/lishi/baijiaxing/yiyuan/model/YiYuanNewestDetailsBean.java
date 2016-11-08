package com.lishi.baijiaxing.yiyuan.model;

import com.lishi.baijiaxing.base.BaseBean;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/24.
 */
public class YiYuanNewestDetailsBean extends BaseBean {
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
     * 我参与的份数
     */
    int participationNum;


    public String getTime() {

        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 揭晓时间
     */
    String time;

    /**
     * 是否参与
     */
    boolean isParticipation = false;

    public boolean isParticipation() {
        return isParticipation;
    }

    public void setParticipation(boolean participation) {
        isParticipation = participation;
    }

    /**
     * 夺宝号码
     */
    String winningNum;

    /**
     * 中奖人
     * @return
     */
    String winningName;

    public YiYuanNewestDetailsBean(JSONArray JSONArray, String name, int num, int participationNum, String time, boolean isParticipation, String winningNum, String winningName, int type, String recordTime, ArrayList<Integer> srcs) {
        mJSONArray = JSONArray;
        this.name = name;
        this.num = num;
        this.participationNum = participationNum;
        this.time = time;
        this.isParticipation = isParticipation;
        this.winningNum = winningNum;
        this.winningName = winningName;
        this.type = type;
        this.recordTime = recordTime;
        mSrcs = srcs;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getWinningName() {
        return winningName;
    }

    public void setWinningName(String winningName) {
        this.winningName = winningName;
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

    public YiYuanNewestDetailsBean() {
    }

}
