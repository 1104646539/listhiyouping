package com.lishi.baijiaxing.free.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.free.FreeFragmentCallback;
import com.lishi.baijiaxing.free.model.FreeList;
import com.lishi.baijiaxing.free.model.FreeFragmentModelImpl;
import com.lishi.baijiaxing.free.view.FreeFragmentView;

/**
 * Created by Administrator on 2016/10/17.
 */
public class FreeFragmentPresenterImpl<T, V extends BaseView> extends BasePresenter implements FreeFragmentCallback, FreeFragmentPresenter {
    private FreeFragmentModelImpl mFreeModel;
    private FreeFragmentView mFreeView;

    public FreeFragmentPresenterImpl(BaseView baseView) {
        super(baseView);
        mFreeModel = new FreeFragmentModelImpl();
        mFreeView = (FreeFragmentView) baseView;
    }

    @Override
    public void loadData(int type) {
        mFreeModel.loadData(this, type);
    }

    @Override
    public void loadFreeListSuccess(FreeList freeList) {
        mFreeView.loadFreeListSuccess(freeList);
    }

    @Override
    public void loadFreeListFailed(String error) {
        mFreeView.loadFreeListFailed(error);
    }
}
