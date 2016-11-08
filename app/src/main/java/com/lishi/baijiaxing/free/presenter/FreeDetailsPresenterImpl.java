package com.lishi.baijiaxing.free.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.free.FreeDetailsCallback;
import com.lishi.baijiaxing.free.model.FreeDetailsModelImpl;

/**
 * Created by Administrator on 2016/10/21.
 */
public class FreeDetailsPresenterImpl<T, V extends BaseView> extends BasePresenter implements FreeDetailsPresenter, FreeDetailsCallback {
    private FreeDetailsModelImpl mFreeDetailsModel;

    public FreeDetailsPresenterImpl(BaseView baseView) {
        super(baseView);
        mFreeDetailsModel = new FreeDetailsModelImpl();
    }

    @Override
    public void loadData(int type) {
        mFreeDetailsModel.loadData(this, type);
    }
}
