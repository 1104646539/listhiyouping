package com.lishi.baijiaxing.classify.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.classify.ClassifyCallback;
import com.lishi.baijiaxing.classify.model.ClassifyModel;
import com.lishi.baijiaxing.classify.model.ClassifyModelImpl;
import com.lishi.baijiaxing.classify.view.ClassifyView;

/**
 * Created by Administrator on 2016/8/17.
 */
public class ClassifyPresenterImpl<T, V extends BaseView> extends BasePresenter implements ClassityPresenter, ClassifyCallback {
    private ClassifyModelImpl mClassifyModel;
    private ClassifyView mClassifyView;

    public ClassifyPresenterImpl(BaseView baseView) {
        super(baseView);
        mClassifyModel = new ClassifyModelImpl();
    }

    @Override
    public void loadData() {
        mClassifyModel.loadData(this);
    }
}
