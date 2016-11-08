package com.lishi.baijiaxing.submitOrder.model;

import com.lishi.baijiaxing.base.BaseBean;

/**
 * Created by Administrator on 2016/10/31.
 */
public class SubmitOrderCommodityBean extends BaseBean {
    private String commImgUrl;//商品图片的url
    private String commName;//商品标题
    private int commPrice;//商品价格
    private int commId;//商品唯一id
    private int buyNum;//商品数量

    public SubmitOrderCommodityBean() {

    }

    public SubmitOrderCommodityBean(String commImgUrl, String commName, int commPrice, int commId, int buyNum) {
        this.commImgUrl = commImgUrl;
        this.commName = commName;
        this.commPrice = commPrice;
        this.commId = commId;
        this.buyNum = buyNum;
    }

    public String getCommImgUrl() {
        return commImgUrl;
    }

    public void setCommImgUrl(String commImgUrl) {
        this.commImgUrl = commImgUrl;
    }

    public String getCommName() {
        return commName;
    }

    public void setCommName(String commName) {
        this.commName = commName;
    }

    public int getCommPrice() {
        return commPrice;
    }

    public void setCommPrice(int commPrice) {
        this.commPrice = commPrice;
    }

    public int getCommId() {
        return commId;
    }

    public void setCommId(int commId) {
        this.commId = commId;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }
}
