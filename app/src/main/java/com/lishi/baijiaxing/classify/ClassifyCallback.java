package com.lishi.baijiaxing.classify;

import com.lishi.baijiaxing.base.BaseRequestCallBack;
import com.lishi.baijiaxing.classify.model.ClassAd;
import com.lishi.baijiaxing.classify.model.ClassList;
import com.lishi.baijiaxing.classify.model.ClassOne;

/**
 * Created by Administrator on 2016/9/29.
 */
public interface ClassifyCallback extends BaseRequestCallBack {
    void loadClassListSuccess(ClassList classList);

    void loadClassListFailed(String error);

    void loadAdSuccess(ClassAd classAd);

    void loadAdFailed(String error);

    void loadClassSuccess(ClassOne classOne);

    void loadClassFailed(String error);
}
