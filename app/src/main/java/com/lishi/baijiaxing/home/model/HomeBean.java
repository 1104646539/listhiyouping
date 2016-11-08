package com.lishi.baijiaxing.home.model;

import com.lishi.baijiaxing.base.BaseBean;
import com.lishi.baijiaxing.bean.HomeRecommendBean;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/29.
 */
public class HomeBean extends BaseBean {
    JSONArray advertiseArray;
    ArrayList<HomeCommodityBean> classifyDatas;
    ArrayList<HomeRecommendBean> recommendDatas;

    public HomeBean(JSONArray advertiseArray, ArrayList<HomeCommodityBean> classifyDatas, ArrayList<HomeRecommendBean> recommendDatas) {
        this.advertiseArray = advertiseArray;
        this.classifyDatas = classifyDatas;
        this.recommendDatas = recommendDatas;
    }

    public JSONArray getAdvertiseArray() {
        return advertiseArray;
    }

    public void setAdvertiseArray(JSONArray advertiseArray) {
        this.advertiseArray = advertiseArray;
    }

    public ArrayList<HomeCommodityBean> getClassifyDatas() {
        return classifyDatas;
    }

    public void setClassifyDatas(ArrayList<HomeCommodityBean> classifyDatas) {
        this.classifyDatas = classifyDatas;
    }

    public ArrayList<HomeRecommendBean> getRecommendDatas() {
        return recommendDatas;
    }

    public void setRecommendDatas(ArrayList<HomeRecommendBean> recommendDatas) {
        this.recommendDatas = recommendDatas;
    }
}

