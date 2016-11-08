package com.lishi.baijiaxing.yiyuan.model;

import com.lishi.baijiaxing.base.BaseBean;

/**
 * 一元领——最新揭晓
 * Created by Administrator on 2016/10/21.
 */
public class YiYuanNewestBean extends BaseBean {
    private String photo;
    private String name;
    /**
     * 期号
     */
    private int num;
    /**
     * 中奖号码
     */
    private String winningNum;
    /**
     * 中奖人昵称
     */
    private String winningName;

    public YiYuanNewestBean() {
    }

    public YiYuanNewestBean(String photo, String name, int num, String winningNum, String winningName) {
        this.photo = photo;
        this.name = name;
        this.num = num;
        this.winningNum = winningNum;
        this.winningName = winningName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getWinningNum() {
        return winningNum;
    }

    public void setWinningNum(String winningNum) {
        this.winningNum = winningNum;
    }

    public String getWinningName() {
        return winningName;
    }

    public void setWinningName(String winningName) {
        this.winningName = winningName;
    }
}
