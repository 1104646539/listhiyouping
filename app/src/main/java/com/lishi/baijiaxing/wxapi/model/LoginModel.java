package com.lishi.baijiaxing.wxapi.model;

import android.util.LogPrinter;

import com.lishi.baijiaxing.bean.UserBean;
import com.lishi.baijiaxing.utils.LocalUserInfo;
import com.lishi.baijiaxing.wxapi.LoginCallback;
import com.lishi.baijiaxing.wxapi.presenter.LoginPresenter;

/**
 * 登录
 * Created by Administrator on 2016/8/22.
 */
public interface LoginModel {
    void wxChatLogin(LoginCallback callback, WXUserInfo userBean);

    void qqLogin(LoginCallback callback, QQUserInfo qqUserInfo,String openid);

    void localLogin(LoginCallback callback, String name,String  paw);
}
