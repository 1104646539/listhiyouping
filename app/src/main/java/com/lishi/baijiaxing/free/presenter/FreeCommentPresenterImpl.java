package com.lishi.baijiaxing.free.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.free.FreeCommentCallback;
import com.lishi.baijiaxing.free.model.FreeCommentModelImpl;

/**
 * Created by Administrator on 2016/10/21.
 */
public class FreeCommentPresenterImpl<T, V extends BaseView> extends BasePresenter implements FreeCommentPresenter, FreeCommentCallback {
    private FreeCommentModelImpl mFreeCommentModel;

    public FreeCommentPresenterImpl(BaseView baseView) {
        super(baseView);
        this.mFreeCommentModel = new FreeCommentModelImpl();
    }

    @Override
    public void loadData() {
        mFreeCommentModel.loadData(this);
    }
}
