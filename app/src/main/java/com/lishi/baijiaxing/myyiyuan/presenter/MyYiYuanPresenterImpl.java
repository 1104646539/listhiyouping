package com.lishi.baijiaxing.myyiyuan.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.myyiyuan.MyYiYuanCallback;
import com.lishi.baijiaxing.myyiyuan.model.MyYiYuanModelImpl;

/**
 * Created by Administrator on 2016/10/28.
 */
public class MyYiYuanPresenterImpl<T, V extends BaseView> extends BasePresenter implements MyYiYuanPresenter, MyYiYuanCallback {
    private MyYiYuanModelImpl mMyYiYuanModel;

    public MyYiYuanPresenterImpl(BaseView baseView) {
        super(baseView);
        mMyYiYuanModel = new MyYiYuanModelImpl();
    }

    @Override
    public void loadData() {
        mMyYiYuanModel.loadData(this);
    }
}
