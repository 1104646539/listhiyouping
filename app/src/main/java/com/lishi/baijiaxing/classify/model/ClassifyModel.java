package com.lishi.baijiaxing.classify.model;

import com.lishi.baijiaxing.classify.ClassifyCallback;

/**
 * Created by Administrator on 2016/8/17.
 */
public interface ClassifyModel {
    void loadClassList(ClassifyCallback callback);

    void loadClassOne(ClassifyCallback callback,String classId);

    void loadAd(ClassifyCallback callback);
}
