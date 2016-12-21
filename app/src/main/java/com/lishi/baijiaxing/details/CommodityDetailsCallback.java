package com.lishi.baijiaxing.details;

import com.lishi.baijiaxing.base.BaseRequestCallBack;
import com.lishi.baijiaxing.details.model.CommodityDetails;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;

/**
 * Created by Administrator on 2016/11/1.
 */
public interface CommodityDetailsCallback extends BaseRequestCallBack {
    void loadSuccess(CommodityDetails.DataBean commodityDetails);

    void loadFailed(String error);

    void addCartSuccess(SCOperation scOperation);

    void addCartFailed(String error);
}
