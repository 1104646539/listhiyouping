package com.lishi.baijiaxing.customize.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.customize.CustomizeDetailsCallback;
import com.lishi.baijiaxing.customize.model.CustomizeDetailsModelImpl;
import com.lishi.baijiaxing.customize.view.CustomizeDetailsView;

/**
 * Created by Administrator on 2016/10/27.
 */
public class CustomizeDetailsPresenterImpl<T, V extends BaseView> extends BasePresenter implements CustomizeDetailsPresenter, CustomizeDetailsCallback {
    private CustomizeDetailsModelImpl mCustomizeDetailsModelImpl;
    private CustomizeDetailsView mCustomizeDetailsView;

    public CustomizeDetailsPresenterImpl(BaseView baseView) {
        super(baseView);
        mCustomizeDetailsModelImpl = new CustomizeDetailsModelImpl();
        mCustomizeDetailsView = (CustomizeDetailsView) baseView;
    }

    @Override
    public void loadData() {
        mCustomizeDetailsModelImpl.loadData(this);
    }

    @Override
    public void collectCommodity() {
        mCustomizeDetailsModelImpl.collectCommodity(this);

    }
}
