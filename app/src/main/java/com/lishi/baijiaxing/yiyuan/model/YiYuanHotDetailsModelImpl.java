package com.lishi.baijiaxing.yiyuan.model;

import android.view.View;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.yiyuan.YiYuanHotDetailsCallback;
import com.lishi.baijiaxing.yiyuan.view.YiYuanActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/24.
 */
public class YiYuanHotDetailsModelImpl extends BaseModel implements YiYuanHotDetailsModel {
    private int type;
    private YiYuanHotDetailsBean mYiYuanDetailsBean;

    @Override
    public void loadData(YiYuanHotDetailsCallback callback, int type) {
        JSONArray advertiseArray = new JSONArray();
        try {
            JSONObject head_img0 = new JSONObject();
            head_img0.put("head_img", "http://www.bx5000.com/data/upload/shop/editor/web-101-102-1-3.jpg");
            JSONObject head_img1 = new JSONObject();
            head_img1.put("head_img", "http://www.bx5000.com/data/upload/shop/editor/web-101-101-2.jpg");
            JSONObject head_img2 = new JSONObject();
            head_img2.put("head_img", "http://www.bx5000.com/data/upload/shop/editor/web-101-101-3.jpg");
            advertiseArray.put(head_img0);
            advertiseArray.put(head_img1);
            advertiseArray.put(head_img2);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<Integer> srcIds = new ArrayList<>();
        srcIds.add(R.drawable.free_details_info1);
        srcIds.add(R.drawable.free_details_info2);
        srcIds.add(R.drawable.free_details_info3);
        srcIds.add(R.drawable.free_details_info4);
        srcIds.add(R.drawable.free_details_info5);
        srcIds.add(R.drawable.free_details_info6);

        if (type == YiYuanActivity.TYPE_HOT) {
            mYiYuanDetailsBean = new YiYuanHotDetailsBean(advertiseArray, "【利世优品】万仟堂陶瓷同心杯带盖过 滤办公茶杯水杯【利世优品】万仟堂陶瓷同心杯带盖过 滤办公茶杯水杯",
                    322, 160, 120, 1, "12112323", type, "2016-12-16 12:00:00", "69",srcIds);
        }
        callback.loadDataHotDetailsSuccess(mYiYuanDetailsBean);
    }
}
