package com.lishi.baijiaxing.classify.view;

import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.classify.model.ClassAd;
import com.lishi.baijiaxing.classify.model.ClassList;
import com.lishi.baijiaxing.classify.model.ClassOne;
import com.lishi.baijiaxing.classify.model.OneClassify;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/17.
 */
public interface ClassifyView extends BaseView {
    void loadClassListSuccess(ClassList classList);

    void loadClassListFailed(String error);

    void loadAdSuccess(ClassAd classAd);

    void loadAdFailed(String error);

    void loadClassSuccess(ClassOne classOne);

    void loadClassFailed(String error);
}
