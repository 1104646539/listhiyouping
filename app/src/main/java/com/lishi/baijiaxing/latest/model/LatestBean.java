package com.lishi.baijiaxing.latest.model;

import com.lishi.baijiaxing.base.BaseBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/4.
 */
public class LatestBean extends BaseBean {
    private List<String> classifyName;//专区名
    private List<LatestCommodityBean> mLatestCommodityBeen;//商品列表

    public LatestBean(List<String> classifyName, List<LatestCommodityBean> latestCommodityBeen) {
        this.classifyName = classifyName;
        mLatestCommodityBeen = latestCommodityBeen;
    }

    public List<String> getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(List<String> classifyName) {
        this.classifyName = classifyName;
    }

    public List<LatestCommodityBean> getLatestCommodityBeen() {
        return mLatestCommodityBeen;
    }

    public void setLatestCommodityBeen(List<LatestCommodityBean> latestCommodityBeen) {
        mLatestCommodityBeen = latestCommodityBeen;
    }
}
