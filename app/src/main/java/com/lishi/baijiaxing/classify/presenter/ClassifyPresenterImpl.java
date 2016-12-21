package com.lishi.baijiaxing.classify.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.classify.ClassifyCallback;
import com.lishi.baijiaxing.classify.model.ClassAd;
import com.lishi.baijiaxing.classify.model.ClassList;
import com.lishi.baijiaxing.classify.model.ClassOne;
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
        mClassifyView = (ClassifyView) baseView;
    }


    @Override
    public void loadClassList() {
        mClassifyModel.loadClassList(this);
    }

    @Override
    public void loadAd() {
        mClassifyView.showDialog();
        mClassifyModel.loadAd(this);
    }

    @Override
    public void loadClassOne(String classId) {
        mClassifyModel.loadClassOne(this, classId);
    }

    @Override
    public void loadClassListSuccess(ClassList classList) {
        mClassifyView.loadClassListSuccess(classList);
    }

    @Override
    public void loadClassListFailed(String error) {
        mClassifyView.loadClassListFailed(error);
    }

    @Override
    public void loadAdSuccess(ClassAd classAd) {
        mClassifyView.loadAdSuccess(classAd);
    }

    @Override
    public void loadAdFailed(String error) {
        mClassifyView.loadAdFailed(error);
    }

    @Override
    public void loadClassSuccess(ClassOne classOne) {
        mClassifyView.loadClassSuccess(classOne);
    }

    @Override
    public void loadClassFailed(String error) {
        mClassifyView.loadClassFailed(error);
    }
}
