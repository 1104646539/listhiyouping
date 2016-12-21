package com.lishi.baijiaxing.free.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.free.FreeCallback;
import com.lishi.baijiaxing.free.model.FreeAdList;
import com.lishi.baijiaxing.free.model.FreeModelImpl;
import com.lishi.baijiaxing.free.view.FreeView;

/**
 * Created by Administrator on 2016/10/17.
 */
public class FreePresenterImpl<T, V extends BaseView> extends BasePresenter implements FreeCallback, FreePresenter {
    private FreeModelImpl mFreeModel;
    private FreeView mFreeView;

    public FreePresenterImpl(BaseView baseView) {
        super(baseView);
        mFreeModel = new FreeModelImpl();
        this.mFreeView = (FreeView) baseView;
    }

    @Override
    public void loadData() {
        mFreeModel.loadData(this);
    }

    @Override
    public void loadAdListSuccess(FreeAdList list) {
        mFreeView.loadAdListSuccess(list);
    }

    @Override
    public void loadAdListFailed(String error) {
        mFreeView.loadAdListFailed(error);
    }
}
