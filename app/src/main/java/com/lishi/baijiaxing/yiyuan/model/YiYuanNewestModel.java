package com.lishi.baijiaxing.yiyuan.model;

import com.lishi.baijiaxing.yiyuan.YiYuanNewestCallback;

/**
 * Created by Administrator on 2016/10/24.
 */
public interface YiYuanNewestModel {
    void loadData(YiYuanNewestCallback callback,int page);
}
