package com.lishi.baijiaxing.base;

/**
 * Created by Administrator on 2016/9/29.
 */
public interface BaseRequestCallBack<T> {
    void onLoadBefore();

    void onLoadComplete();

    void onLoadSuccess(T data);

    void onLoadFailed(String error);
}
