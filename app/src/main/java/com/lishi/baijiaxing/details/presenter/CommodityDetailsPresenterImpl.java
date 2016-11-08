package com.lishi.baijiaxing.details.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.details.CommodityDetailsCallback;
import com.lishi.baijiaxing.details.model.CommodityDetailsBean;
import com.lishi.baijiaxing.details.model.CommodityDetailsModelImpl;
import com.lishi.baijiaxing.details.view.CommodityDetailsView;

/**
 * Created by Administrator on 2016/11/1.
 */
public class CommodityDetailsPresenterImpl<T, V extends BaseView> extends BasePresenter implements CommodityDetailsCallback, CommodityDetailsPresenter {
    private CommodityDetailsModelImpl mCommodityDetailsModel;
    private CommodityDetailsView mCommodityDetailsView;

    public CommodityDetailsPresenterImpl(BaseView baseView) {
        super(baseView);
        mCommodityDetailsModel = new CommodityDetailsModelImpl();
        mCommodityDetailsView = (CommodityDetailsView) baseView;
    }

    @Override
    public void loadData() {
        mCommodityDetailsModel.loadData(this);
    }

    @Override
    public void loadDataSuccess(CommodityDetailsBean detailsBeen) {
        mCommodityDetailsView.loadDataSuccess(detailsBeen);
    }


    @Override
    public void loadDataFailed(String error) {
        mCommodityDetailsView.loadDataFailed(error);
    }
}
