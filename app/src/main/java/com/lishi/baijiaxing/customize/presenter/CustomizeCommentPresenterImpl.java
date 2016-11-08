package com.lishi.baijiaxing.customize.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.customize.CustomizeCallback;
import com.lishi.baijiaxing.customize.model.CustomizeCommentModelImpl;
import com.lishi.baijiaxing.customize.view.CustomizeCommentView;
import com.lishi.baijiaxing.free.model.FreeCommentBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/28.
 */
public class CustomizeCommentPresenterImpl<T, V extends BaseView> extends BasePresenter implements CustomizePresenter, CustomizeCallback {
    private CustomizeCommentModelImpl mCustomizeCommentModel;
    private CustomizeCommentView mCustomizeCommentView;

    public CustomizeCommentPresenterImpl(BaseView baseView) {
        super(baseView);
        this.mCustomizeCommentModel = new CustomizeCommentModelImpl();
    }

    @Override
    public void loadData() {
        mCustomizeCommentModel.loadData(this);
    }
}
