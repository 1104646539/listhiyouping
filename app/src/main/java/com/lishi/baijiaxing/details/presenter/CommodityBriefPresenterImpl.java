package com.lishi.baijiaxing.details.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.details.CommodityBriefCallback;
import com.lishi.baijiaxing.details.model.CommodityBriefModelImpl;
import com.lishi.baijiaxing.details.model.CommodityDetails;
import com.lishi.baijiaxing.details.view.CommodityBriefView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/1.
 */
public class CommodityBriefPresenterImpl extends BasePresenter implements CommodityBriefCallback, CommodityBriefPresenter {
    private CommodityBriefModelImpl mCommodityBriefModel;
    private CommodityBriefView mCommodityBriefView;

    public CommodityBriefPresenterImpl(BaseView baseView) {
        super(baseView);
        mCommodityBriefModel = new CommodityBriefModelImpl();
        mCommodityBriefView = (CommodityBriefView) baseView;
    }

    @Override
    public void loadData(String cid) {
        mCommodityBriefModel.loadData(this, cid);
    }

    @Override
    public void loadSuccess(CommodityDetails commodityDetails) {
        mCommodityBriefView.loadDataSuccess(commodityDetails);
    }

    @Override
    public void loadFailed(String error) {
        mCommodityBriefView.loadDataFailed(error);
    }
}
