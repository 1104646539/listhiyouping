package com.lishi.baijiaxing.yiyuan.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.yiyuan.YiYuanHotDetailsCallback;
import com.lishi.baijiaxing.yiyuan.model.YiYuanHotDetailsBean;
import com.lishi.baijiaxing.yiyuan.model.YiYuanHotDetailsModelImpl;
import com.lishi.baijiaxing.yiyuan.view.YiYuanHotDetailsView;

/**
 * Created by Administrator on 2016/10/24.
 */
public class YiYuanHotDetailsPresenterImpl<T, V extends BaseView> extends BasePresenter implements YiYuanDetailsPresenter, YiYuanHotDetailsCallback {
    private YiYuanHotDetailsView mYiYuanHotDetailsView;
    private YiYuanHotDetailsModelImpl mYiYuanHotDetailsModel;

    public YiYuanHotDetailsPresenterImpl(BaseView baseView) {
        super(baseView);
        mYiYuanHotDetailsModel = new YiYuanHotDetailsModelImpl();
        mYiYuanHotDetailsView = (YiYuanHotDetailsView) baseView;
    }

    @Override
    public void loadData(int type) {
        mYiYuanHotDetailsModel.loadData(this, type);
    }

    @Override
    public void loadDataHotDetailsSuccess(YiYuanHotDetailsBean bean) {
        mYiYuanHotDetailsView.loadHotDataSuccess(bean);
    }
}
