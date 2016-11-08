package com.lishi.baijiaxing.wxapi.presenter;

import com.lishi.baijiaxing.bean.UserBean;

/**
 * 登录
 * Created by Administrator on 2016/8/22.
 */
public interface SignInPresenter {
    void signIn(UserBean userBean);

    void signInSuccess();

    void signInFailed();
}
