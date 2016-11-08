package com.lishi.baijiaxing.base;

/**
 * Created by Administrator on 2016/9/29.
 */
public interface BaseView<T> {
    void showDialog();

    void closeDialog();

    void loadDataSuccess(T data);

    void loadDataFailed(String error);
}
