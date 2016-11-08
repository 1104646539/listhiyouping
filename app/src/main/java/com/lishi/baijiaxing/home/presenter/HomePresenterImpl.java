package com.lishi.baijiaxing.home.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.bean.HomeRecommendBean;
import com.lishi.baijiaxing.home.HomeCallBack;
import com.lishi.baijiaxing.home.view.HomeView;
import com.lishi.baijiaxing.home.model.HomeModelImpl;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/17.
 */
public class HomePresenterImpl<T, V extends BaseView> extends BasePresenter implements HomePresenter, HomeCallBack {
    private HomeView mHomeView;
    private HomeModelImpl mHomeModel;

    public HomePresenterImpl(BaseView baseView) {
        super(baseView);
        mHomeModel = new HomeModelImpl();
        mHomeView = (HomeView) baseView;
    }

    @Override
    public void pullToRefreshSuccess(ArrayList<HomeRecommendBean> data) {
        mHomeView.onRecommendonPullSuccess(data);
    }

    @Override
    public void pullToRefreshFailed(String error) {
        mHomeView.onPullloadFailed();
    }

    @Override
    public void recommendPullLoadData() {
        mHomeModel.pullRecommendData(this);
    }

    @Override
    public void loadData() {
        mHomeModel.loadData(this);
    }
}
