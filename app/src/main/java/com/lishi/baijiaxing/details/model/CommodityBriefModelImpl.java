package com.lishi.baijiaxing.details.model;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.details.CommodityBriefCallback;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/1.
 */
public class CommodityBriefModelImpl extends BaseModel implements CommodityBriefModel {
    @Override
    public void loadData(CommodityBriefCallback callback) {
        ArrayList<Integer> srcIds = new ArrayList<>();
        srcIds.add(R.drawable.free_details_info1);
        srcIds.add(R.drawable.free_details_info2);
        srcIds.add(R.drawable.free_details_info3);
        srcIds.add(R.drawable.free_details_info4);
        srcIds.add(R.drawable.free_details_info5);
        srcIds.add(R.drawable.free_details_info6);
        
        callback.onLoadSuccess(srcIds);
    }
}
