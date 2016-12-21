package com.lishi.baijiaxing.wxapi.view;

import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.wxapi.model.Login;

/**
 * 登录
 * Created by Administrator on 2016/8/22.
 */
public interface LoginView  extends BaseView{
    void wxChatLoginSuccess(Login login);

    void qqLoginSuccess(Login login);

    void localLoginSuccess(Login login);

    void loginFailed(String error);
}
