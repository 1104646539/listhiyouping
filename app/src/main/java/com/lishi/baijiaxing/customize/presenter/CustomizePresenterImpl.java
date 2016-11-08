package com.lishi.baijiaxing.customize.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.customize.CustomizeCallback;
import com.lishi.baijiaxing.customize.model.CustomizeModelImpl;

/**
 * Created by Administrator on 2016/10/26.
 */
public class CustomizePresenterImpl<T, V extends BaseView> extends BasePresenter implements CustomizeCallback, CustomizePresenter {
  private  CustomizeModelImpl mCustomizeModel;

    public CustomizePresenterImpl(BaseView baseView) {
        super(baseView);
        mCustomizeModel = new CustomizeModelImpl();
    }

    @Override
    public void loadData() {
        mCustomizeModel.loadData(this);
    }
}
