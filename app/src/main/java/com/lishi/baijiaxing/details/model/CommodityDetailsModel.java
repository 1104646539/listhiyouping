package com.lishi.baijiaxing.details.model;

import com.lishi.baijiaxing.details.CommodityCommentCallback;
import com.lishi.baijiaxing.details.CommodityDetailsCallback;

/**
 * Created by Administrator on 2016/11/1.
 */
public interface CommodityDetailsModel {
    void loadData(CommodityDetailsCallback callback, String gid);

    void addCart(CommodityDetailsCallback callback, String gid, String number);
}
