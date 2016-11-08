package com.lishi.baijiaxing.free.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseRequestCallBack;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.free.FreeCallback;
import com.lishi.baijiaxing.free.FreeFragmentCallback;
import com.lishi.baijiaxing.free.model.FreeFragmentModel;
import com.lishi.baijiaxing.free.model.FreeFragmentModelImpl;
import com.lishi.baijiaxing.free.model.FreeModelImpl;
import com.lishi.baijiaxing.free.view.FreeFragmentView;
import com.lishi.baijiaxing.free.view.FreeView;

/**
 * Created by Administrator on 2016/10/17.
 */
public class FreeFragmentPresenterImpl<T, V extends BaseView> extends BasePresenter implements FreeFragmentCallback, FreeFragmentPresenter {
    private FreeFragmentModelImpl mFreeModel;
    private FreeFragmentView mFreeView;

    public FreeFragmentPresenterImpl(BaseView baseView) {
        super(baseView);
        mFreeModel = new FreeFragmentModelImpl();
    }

    @Override
    public void loadData(int type) {
        mFreeModel.loadData(this, type);
    }
}
