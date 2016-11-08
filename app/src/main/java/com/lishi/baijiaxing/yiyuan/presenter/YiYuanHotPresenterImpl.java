package com.lishi.baijiaxing.yiyuan.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.yiyuan.YiYuanHotCallback;
import com.lishi.baijiaxing.yiyuan.model.YiYuanHotBean;
import com.lishi.baijiaxing.yiyuan.model.YiYuanHotModel;
import com.lishi.baijiaxing.yiyuan.model.YiYuanHotModelImpl;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/24.
 */
public class YiYuanHotPresenterImpl<T, V extends BaseView> extends BasePresenter implements YiYuanHotPresenter, YiYuanHotCallback {
    private YiYuanHotModelImpl mYiYuanHotModel;

    public YiYuanHotPresenterImpl(BaseView baseView) {
        super(baseView);
        mYiYuanHotModel = new YiYuanHotModelImpl();
    }

    @Override
    public void loadData() {
        mYiYuanHotModel.loadData(this);
    }
}
