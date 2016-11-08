package com.lishi.baijiaxing.base;

import com.lishi.baijiaxing.manager.RetrofitManager;

/**
 * Created by Administrator on 2016/9/29.
 */
public class BaseModel {
    public RetrofitManager getRetrofitManager() {
        return RetrofitManager.builder();
    }
}
