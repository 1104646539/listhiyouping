package com.lishi.baijiaxing.customize.model;

import com.lishi.baijiaxing.customize.CustomizeDetailsCallback;

/**
 * Created by Administrator on 2016/10/27.
 */
public interface CustomizeDetailsModel {
    void loadData(CustomizeDetailsCallback callback);

    void collectCommodity(CustomizeDetailsCallback callback);
}
