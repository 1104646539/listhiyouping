package com.lishi.baijiaxing.yiyuan.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.yiyuan.YiYuanHotCallback;
import com.lishi.baijiaxing.yiyuan.model.HotList;
import com.lishi.baijiaxing.yiyuan.model.LotteryList;
import com.lishi.baijiaxing.yiyuan.model.YiYuanHotBean;
import com.lishi.baijiaxing.yiyuan.model.YiYuanHotModel;
import com.lishi.baijiaxing.yiyuan.model.YiYuanHotModelImpl;
import com.lishi.baijiaxing.yiyuan.view.YiYuanHotView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/24.
 */
public class YiYuanHotPresenterImpl<T, V extends BaseView> extends BasePresenter implements YiYuanHotPresenter, YiYuanHotCallback {
    private YiYuanHotModelImpl mYiYuanHotModel;
    private YiYuanHotView mYiYuanHotView;

    public YiYuanHotPresenterImpl(BaseView baseView) {
        super(baseView);
        mYiYuanHotModel = new YiYuanHotModelImpl();
        mYiYuanHotView = (YiYuanHotView) baseView;
    }

    @Override
    public void loadData() {
        mYiYuanHotModel.loadData(this);
    }

    @Override
    public void loadHotListSuccess(HotList hotList) {
        mYiYuanHotView.loadHotListSuccess(hotList);
    }

    @Override
    public void loadHotListFailed(String error) {
        mYiYuanHotView.loadHotListFailed(error);
    }
}
