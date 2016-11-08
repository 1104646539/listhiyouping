package com.lishi.baijiaxing.free.model;

import com.google.gson.JsonArray;
import com.lishi.baijiaxing.base.BaseBean;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * 初始化时的
 * Created by Administrator on 2016/10/17.
 */
public class FreeBean extends BaseBean {
    JSONArray mJsonArray;
//    ArrayList<FreeCommodityBean> mFreeCommodityBeen;

    public FreeBean() {
    }

    public FreeBean(JSONArray jsonArray) {
        mJsonArray = jsonArray;
    }

    public JSONArray getJsonArray() {
        return mJsonArray;
    }

    public void setJsonArray(JSONArray jsonArray) {
        mJsonArray = jsonArray;
    }
//
//    public ArrayList<FreeCommodityBean> getFreeCommodityBeen() {
//        return mFreeCommodityBeen;
//    }
//
//    public void setFreeCommodityBeen(ArrayList<FreeCommodityBean> freeCommodityBeen) {
//        mFreeCommodityBeen = freeCommodityBeen;
//    }
}
