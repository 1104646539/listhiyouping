package com.lishi.baijiaxing.yiyuan.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.view.MyListView;
import com.lishi.baijiaxing.yiyuan.YiYuanHotCallback;
import com.lishi.baijiaxing.yiyuan.YiYuanNewestCallback;
import com.lishi.baijiaxing.yiyuan.model.LotteryList;
import com.lishi.baijiaxing.yiyuan.model.YiYuanHotModelImpl;
import com.lishi.baijiaxing.yiyuan.model.YiYuanNewestModelImpl;
import com.lishi.baijiaxing.yiyuan.view.YiYuanNewestView;

/**
 * Created by Administrator on 2016/10/24.
 */
public class YiYuanNewestPresenterImpl<T, V extends BaseView> extends BasePresenter implements YiYuanNewestPresenter, YiYuanNewestCallback {
    private YiYuanNewestModelImpl mYiYuanNewestModel;
    private YiYuanNewestView mYiYuanNewestView;

    public YiYuanNewestPresenterImpl(BaseView baseView) {
        super(baseView);
        mYiYuanNewestModel = new YiYuanNewestModelImpl();
        mYiYuanNewestView = (YiYuanNewestView) baseView;
    }

    @Override
    public void loadData(int page) {
        mYiYuanNewestModel.loadData(this, page);
    }

    @Override
    public void loadLotteryListSuccess(LotteryList lotteryList) {
        if (lotteryList.getData() == null || lotteryList.getData().getCommodityList() == null) {
            mYiYuanNewestView.loadLotteryListFailed("数据为空");
        }
        mYiYuanNewestView.loadLotteryListSuccess(lotteryList.getData().getCommodityList());
    }

    @Override
    public void loadLotteryListFailed(String error) {
        mYiYuanNewestView.loadLotteryListFailed(error);
    }

    @Override
    public void onLastPage(String status) {
        mYiYuanNewestView.onLastPage(status);
    }
}
