package com.lishi.baijiaxing.details;

import com.lishi.baijiaxing.base.BaseRequestCallBack;
import com.lishi.baijiaxing.details.model.CommodityDetails;

/**
 * Created by Administrator on 2016/11/1.
 */
public interface CommodityBriefCallback extends BaseRequestCallBack {
    void loadSuccess(CommodityDetails commodityDetails);

    void loadFailed(String error);
}
