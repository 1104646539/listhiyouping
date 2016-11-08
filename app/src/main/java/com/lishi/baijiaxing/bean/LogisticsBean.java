package com.lishi.baijiaxing.bean;

import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;

/**
 * 快递实体类
 * Created by Administrator on 2016/7/6.
 */
public class LogisticsBean {
    private int fastmailNum;//快递单号
    private CommodityBean mCommodityBean;//商品信息
    private int State;

    public LogisticsBean(int fastmailNum, CommodityBean commodityBean, int state) {
        this.fastmailNum = fastmailNum;
        mCommodityBean = commodityBean;
        State = state;
    }

    public int getFastmailNum() {
        return fastmailNum;
    }

    public void setFastmailNum(int fastmailNum) {
        this.fastmailNum = fastmailNum;
    }

    public CommodityBean getCommodityBean() {
        return mCommodityBean;
    }

    public void setCommodityBean(CommodityBean commodityBean) {
        mCommodityBean = commodityBean;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }
}
