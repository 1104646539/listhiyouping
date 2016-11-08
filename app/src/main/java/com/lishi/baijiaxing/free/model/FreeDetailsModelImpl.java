package com.lishi.baijiaxing.free.model;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.free.FreeDetailsCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/21.
 */
public class FreeDetailsModelImpl extends BaseModel implements FreeDetailsModel {
    @Override
    public void loadData(FreeDetailsCallback callback, int type) {
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

        FreeDetailsBean freeDetailsBean = new FreeDetailsBean(advertiseArray, "【利世优品】万仟堂陶瓷同心杯带盖过 滤办公茶杯水杯【利世优品】万仟堂陶瓷同心杯带盖过 滤办公茶杯水杯"
                , 150, 0, 68, type, srcIds);

        callback.onLoadSuccess(freeDetailsBean);
    }
}
