package com.lishi.baijiaxing.details.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.details.CommodityDetailsCallback;
import com.lishi.baijiaxing.details.model.CommodityDetails;
import com.lishi.baijiaxing.details.model.CommodityDetailsBean;
import com.lishi.baijiaxing.details.model.CommodityDetailsModelImpl;
import com.lishi.baijiaxing.details.view.CommodityDetailsView;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;

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
    public void loadData(String cid) {
        mCommodityDetailsView.showDialog();
        mCommodityDetailsModel.loadData(this, cid);
    }

    @Override
    public void addCart(String gid, String number) {
        mCommodityDetailsModel.addCart(this, gid, number);
    }


    @Override
    public void loadSuccess(CommodityDetails.DataBean commodityDetails) {
        mCommodityDetailsView.loadDataSuccess(commodityDetails);
        mCommodityDetailsView.closeDialog();
    }

    @Override
    public void loadFailed(String error) {
//        mCommodityDetailsView.closeDialog();
        mCommodityDetailsView.loadDataFailed(error);
    }

    @Override
    public void addCartSuccess(SCOperation scOperation) {
        mCommodityDetailsView.addCartSuccess(scOperation);
    }

    @Override
    public void addCartFailed(String error) {
        mCommodityDetailsView.addCartFailed(error);
    }
}
