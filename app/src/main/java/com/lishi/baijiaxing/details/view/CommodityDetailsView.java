package com.lishi.baijiaxing.details.view;

import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.details.model.CommodityDetails;
import com.lishi.baijiaxing.details.model.CommodityDetailsBean;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;

/**
 * Created by Administrator on 2016/11/1.
 */
public interface CommodityDetailsView extends BaseView<CommodityDetails.DataBean> {
    void loadSuccess(CommodityDetails.DataBean commodityDetails);

    void addCartSuccess(SCOperation scOperation);

    void addCartFailed(String error);
}
