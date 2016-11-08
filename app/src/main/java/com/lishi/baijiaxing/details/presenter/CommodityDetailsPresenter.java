package com.lishi.baijiaxing.details.presenter;

import com.lishi.baijiaxing.details.model.CommodityDetailsBean;
import com.lishi.baijiaxing.details.view.CommodityBriefView;
import com.lishi.baijiaxing.details.view.CommodityDetailsView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/1.
 */
public interface CommodityDetailsPresenter {
    void loadData();

    void loadDataSuccess(CommodityDetailsBean detailsBean);

    void loadDataFailed(String error);
}
