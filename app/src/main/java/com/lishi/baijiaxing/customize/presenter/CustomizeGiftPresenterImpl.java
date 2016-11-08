package com.lishi.baijiaxing.customize.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.customize.CustomizeCallback;
import com.lishi.baijiaxing.customize.model.CustomizeGiftModelImpl;
import com.lishi.baijiaxing.customize.view.CustomizeGiftView;

/**
 * Created by Administrator on 2016/10/27.
 */
public class CustomizeGiftPresenterImpl<T, V extends BaseView> extends BasePresenter implements CustomizePresenter, CustomizeCallback {
    private CustomizeGiftView mCustomizeGiftView;
    private CustomizeGiftModelImpl mCustomizeGiftModel;

    public CustomizeGiftPresenterImpl(BaseView baseView) {
        super(baseView);
        this.mCustomizeGiftModel = new CustomizeGiftModelImpl();
        this.mCustomizeGiftView = (CustomizeGiftView) baseView;
    }

    @Override
    public void loadData() {
        mCustomizeGiftModel.loadData(this);
    }
}
