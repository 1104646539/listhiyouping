package com.lishi.baijiaxing.shoppingCart.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.bean.HomeRecommendBean;
import com.lishi.baijiaxing.shoppingCart.ShoppingCartCallback;
import com.lishi.baijiaxing.shoppingCart.model.ShoppingCartModelImpl;
import com.lishi.baijiaxing.shoppingCart.view.ShoppingCartView;

import java.util.ArrayList;

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
    public void loadData() {
        mShoppingCartModel.loadData(this);
    }

    @Override
    public void addStore(ArrayList<CommodityBean> commodityBeen) {
        mShoppingCartModel.addStore(this, commodityBeen);
    }

    @Override
    public void removeStore(ArrayList<CommodityBean> commodityBeen) {
        mShoppingCartModel.deleteStore(this, commodityBeen);
    }

    @Override
    public void pullLoad(ArrayList<HomeRecommendBean> homeRecommends) {
        mShoppingCartModel.pullLoad(this, homeRecommends);
    }

    @Override
    public void changeStore(ArrayList<CommodityBean> commodityBeen) {
        mShoppingCartModel.changeStore(this, commodityBeen);
    }


    @Override
    public void addStoreSuccess(ArrayList<CommodityBean> commodityBeen) {
        mShoppingCartView.onAddDataSuccess(commodityBeen);
    }

    @Override
    public void addStoreFailed(String error) {
        mShoppingCartView.onAddDataFailed(error);
    }

    @Override
    public void removeStoreSuccess(ArrayList<CommodityBean> commodityBeen) {
        mShoppingCartView.onDeleteDataSuccess(commodityBeen);
    }

    @Override
    public void removeStoreFailed(String error) {
        mShoppingCartView.onDeleteDataFailed(error);
    }

    @Override
    public void changeStoreSuccess(ArrayList<CommodityBean> commodityBeen) {
        mShoppingCartView.onChangeStoreSuccess(commodityBeen);
    }

    @Override
    public void changeStoreFailed(String error) {
        mShoppingCartView.onChangeStoreFailed(error);
    }

    @Override
    public void pullloadSuccess(ArrayList<HomeRecommendBean> homeRecommends) {
        mShoppingCartView.onPullLoadDataSuccess(homeRecommends);
    }

    @Override
    public void pullloadFailed(String error) {
        mShoppingCartView.onPullLoadDataFailed(error);
    }
}
