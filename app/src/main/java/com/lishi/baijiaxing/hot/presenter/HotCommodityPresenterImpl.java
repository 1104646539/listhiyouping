package com.lishi.baijiaxing.hot.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.hot.HotCommodityCallback;
import com.lishi.baijiaxing.hot.model.HotCommodity;
import com.lishi.baijiaxing.hot.model.HotCommodityModelImpl;
import com.lishi.baijiaxing.hot.view.HotCommodityView;

/**
 * Created by Administrator on 2016/12/8.
 */

public class HotCommodityPresenterImpl extends BasePresenter implements HotCommodityPresenter, HotCommodityCallback {
    private HotCommodityModelImpl mHotCommodityModel;
    private HotCommodityView mHotCommodityView;

    public HotCommodityPresenterImpl(BaseView baseView) {
        super(baseView);
        this.mHotCommodityModel = new HotCommodityModelImpl();
        this.mHotCommodityView = (HotCommodityView) baseView;
    }

    @Override
    public void loadData() {
        mHotCommodityView.showDialog();
        mHotCommodityModel.loadData(this);
    }

    @Override
    public void loadDataSuccess(HotCommodity commodity) {
        mHotCommodityView.closeDialog();
        mHotCommodityView.loadDataSuccess(commodity);
    }

    @Override
    public void loadDataFailed(String error) {
        mHotCommodityView.closeDialog();
        mHotCommodityView.loadDataFailed(error);
    }
}
