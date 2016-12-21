package com.lishi.baijiaxing.home.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.bean.HomeRecommendBean;
import com.lishi.baijiaxing.home.HomeCallBack;
import com.lishi.baijiaxing.home.model.AdList;
import com.lishi.baijiaxing.home.model.Commodity;
import com.lishi.baijiaxing.home.model.Festival;
import com.lishi.baijiaxing.home.model.HomeModelImpl;
import com.lishi.baijiaxing.home.model.Seckill;
import com.lishi.baijiaxing.home.view.HomeView;

import java.util.List;

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
    public void pullToRefreshSuccess(List<Commodity.DataBean> data) {
        mHomeView.onPullSuccess(data);
    }

    @Override
    public void pullToRefreshFailed(String error) {
        mHomeView.onPullloadFailed(error);
    }

    @Override
    public void getAdListSuccess(List<AdList.DataBean> adLists) {
        mHomeView.getAdListSuccess(adLists);
    }


    @Override
    public void getFestivalSuccess(Festival.DataBean festivals) {
        mHomeView.getFestivalSuccess(festivals);
    }

    @Override
    public void getSeckillSuccess(Seckill.DataBean seckillBeen) {
        mHomeView.getSeckillSuccess(seckillBeen);
    }

    @Override
    public void getCommodityListSuccess(List<Commodity.DataBean> commodities) {
        mHomeView.getCommodityListSuccess(commodities);
        mHomeView.closeDialog();
    }

    @Override
    public void getAdListFailed(String error) {
        mHomeView.getAdListFailed(error);
    }

    @Override
    public void getFestivalFailed(String error) {
        mHomeView.getFestivalFailed(error);
    }

    @Override
    public void getSeckillFailed(String error) {
        mHomeView.getSeckillFailed(error);
    }

    @Override
    public void getCommodityListFailed(String error) {
        mHomeView.getCommodityListFailed(error); mHomeView.closeDialog();
    }

    @Override
    public void PullDownLoadData() {
        mHomeModel.pullRecommendData(this);
    }

    @Override
    public void loadData() {
        mHomeView.showDialog();
        mHomeModel.getAdList(this);
        mHomeModel.getCommodityList(this);
        mHomeModel.getFestival(this);
        mHomeModel.getSeckill(this);
    }
}
