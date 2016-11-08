package com.lishi.baijiaxing.base;

/**
 * Created by Administrator on 2016/9/29.
 */
public class BasePresenter<T, V extends BaseView> implements BaseRequestCallBack<T> {
    public BaseView mBaseView;

    public BasePresenter(V baseView) {
        this.mBaseView = baseView;
    }

    @Override
    public void onLoadBefore() {
        mBaseView.showDialog();
    }

    @Override
    public void onLoadComplete() {
        mBaseView.closeDialog();
    }

    @Override
    public void onLoadSuccess(T data) {
        mBaseView.loadDataSuccess(data);
    }

    @Override
    public void onLoadFailed(String error) {
        mBaseView.loadDataFailed(error);
    }
}
