package com.lishi.baijiaxing.shoppingCart.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.bean.HomeRecommendBean;
import com.lishi.baijiaxing.shoppingCart.ShoppingCartCallback;
import com.lishi.baijiaxing.shoppingCart.model.SCCommodityList;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;
import com.lishi.baijiaxing.shoppingCart.model.SCRecommendList;
import com.lishi.baijiaxing.shoppingCart.model.ShoppingCartModelImpl;
import com.lishi.baijiaxing.shoppingCart.view.ShoppingCartView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/18.
 */
public class ShoppingCartPresenterImpl<T, V extends BaseView> extends BasePresenter implements ShoppingCartPresenter, ShoppingCartCallback {
    private ShoppingCartView mShoppingCartView;
    private ShoppingCartModelImpl mShoppingCartModel;

    public ShoppingCartPresenterImpl(BaseView baseView) {
        super(baseView);
        mShoppingCartModel = new ShoppingCartModelImpl();
        mShoppingCartView = (ShoppingCartView) baseView;
    }

    @Override
    public void removeCommoditySuccess(SCOperation scOperation) {
        mShoppingCartView.onRemoveDataSuccess(scOperation);
    }

    @Override
    public void removeCommodityFailed(String error) {
        mShoppingCartView.onRemoveDataFailed(error);
    }

    @Override
    public void loadCommodityListSuccess(SCCommodityList commodityList) {
        mShoppingCartView.onLoadCommoditySuccess(commodityList);
    }

    @Override
    public void loadRecommendListSuccess(SCRecommendList commodityList) {
        mShoppingCartView.onLoadRecommendSuccess(commodityList);
    }

    @Override
    public void loadCommodityListFailed(String error) {
        mShoppingCartView.onLoadCommodityFailed(error);
    }

    @Override
    public void loadRecommendListFailed(String error) {
        mShoppingCartView.onLoadRecommendFailed(error);
    }

    @Override
    public void pullDownloadSuccess(SCRecommendList recommendLists) {
        mShoppingCartView.onPullLoadDataSuccess(recommendLists);
    }

    @Override
    public void pullDownloadFailed(String error) {
        mShoppingCartView.onPullLoadDataFailed(error);
    }

    @Override
    public void changeCommodityFailed(String error) {
        mShoppingCartView.onChangeCommodityFailed(error);
        mShoppingCartView.closeDialog();
    }

    @Override
    public void changeCommoditySuccess(SCOperation scOperation) {
        mShoppingCartView.onChangeCommoditySuccess(scOperation);
        mShoppingCartView.closeDialog();
    }

    @Override
    public void upCommodityInfoSuccess(SCCommodityList scCommodityList) {
        mShoppingCartView.upCommodityInfoSuccess(scCommodityList);
    }

    @Override
    public void upCommodityInfoFailed(String error) {
        mShoppingCartView.upCommodityInfoFailed(error);
    }

    @Override
    public void loadCommodity() {
        mShoppingCartModel.loadCommodityList(this);
    }

    @Override
    public void loadRecommend() {
        mShoppingCartModel.loadRecommendList(this);
    }

    @Override
    public void removeCommodity(List<String> deleteIds) {
        mShoppingCartModel.removeCommodity(this, deleteIds);
    }

    @Override
    public void pullDownLoad() {
        mShoppingCartModel.pullDownLoad(this);
    }

    @Override
    public void changeCommodity(SCCommodityList.DataBean dataBean, String number) {
        mShoppingCartView.showDialog();
        mShoppingCartModel.changeCommodity(this, dataBean, number);
    }

    @Override
    public void upCommodityInfo(SCCommodityList.DataBean dataBean) {
        mShoppingCartModel.upCommodityInfo(this, dataBean);
    }

}
