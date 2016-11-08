package com.lishi.baijiaxing.yiyuan.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.yiyuan.YiYuanHotCallback;
import com.lishi.baijiaxing.yiyuan.YiYuanNewestCallback;
import com.lishi.baijiaxing.yiyuan.model.YiYuanHotModelImpl;
import com.lishi.baijiaxing.yiyuan.model.YiYuanNewestModelImpl;

/**
 * Created by Administrator on 2016/10/24.
 */
public class YiYuanNewestPresenterImpl<T, V extends BaseView> extends BasePresenter implements YiYuanNewestPresenter, YiYuanNewestCallback {
    private YiYuanNewestModelImpl mYiYuanNewestModel;

    public YiYuanNewestPresenterImpl(BaseView baseView) {
        super(baseView);
        mYiYuanNewestModel = new YiYuanNewestModelImpl();
    }

    @Override
    public void loadData() {
        
        mYiYuanNewestModel.loadData(this);
    }
}
