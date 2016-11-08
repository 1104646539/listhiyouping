package com.lishi.baijiaxing.yiyuan.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.yiyuan.YiYuanHotDetailsCallback;
import com.lishi.baijiaxing.yiyuan.YiYuanNewestDetailsCallback;
import com.lishi.baijiaxing.yiyuan.model.YiYuanHotDetailsModelImpl;
import com.lishi.baijiaxing.yiyuan.model.YiYuanNewestDetailsBean;
import com.lishi.baijiaxing.yiyuan.model.YiYuanNewestDetailsModelImpl;
import com.lishi.baijiaxing.yiyuan.view.YiYuanNewestDetailsView;

/**
 * Created by Administrator on 2016/10/24.
 */
public class YiYuanNewestDetailsPresenterImpl<T, V extends BaseView> extends BasePresenter implements YiYuanDetailsPresenter, YiYuanNewestDetailsCallback {
    private YiYuanNewestDetailsView mYiYuanNewestDetailsView;
    private YiYuanNewestDetailsModelImpl mYiYuanNewestDetailsModelImpl;

    public YiYuanNewestDetailsPresenterImpl(BaseView baseView) {
        super(baseView);
        mYiYuanNewestDetailsModelImpl = new YiYuanNewestDetailsModelImpl();
        mYiYuanNewestDetailsView = (YiYuanNewestDetailsView) baseView;
    }

    @Override
    public void loadData(int type) {
        mYiYuanNewestDetailsModelImpl.loadData(this, type);
    }

    @Override
    public void loadDataNewestDetailsSuccess(YiYuanNewestDetailsBean bean) {
        mYiYuanNewestDetailsView.loadDataNewestSuccess(bean);
    }
}
