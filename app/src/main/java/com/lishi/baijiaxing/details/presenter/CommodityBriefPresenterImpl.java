package com.lishi.baijiaxing.details.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.details.CommodityBriefCallback;
import com.lishi.baijiaxing.details.model.CommodityBriefModelImpl;
import com.lishi.baijiaxing.details.view.CommodityBriefView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/1.
 */
public class CommodityBriefPresenterImpl<T, V extends BaseView> extends BasePresenter implements CommodityBriefCallback, CommodityBriefPresenter {
    private CommodityBriefModelImpl mCommodityBriefModel;
    private CommodityBriefView mCommodityBriefView;

    public CommodityBriefPresenterImpl(BaseView baseView) {
        super(baseView);
        mCommodityBriefModel = new CommodityBriefModelImpl();
        mCommodityBriefView = (CommodityBriefView) baseView;
    }

    @Override
    public void loadData() {
        mCommodityBriefModel.loadData(this);
    }

    @Override
    public void loadDataSuccess(ArrayList<Integer> integers) {
        mCommodityBriefView.loadDataSuccess(integers);
    }

    @Override
    public void loadDataFailed(String error) {
        mCommodityBriefView.loadDataFailed(error);
    }
}
