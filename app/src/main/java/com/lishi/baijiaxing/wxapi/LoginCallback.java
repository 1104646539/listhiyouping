package com.lishi.baijiaxing.wxapi;

import com.lishi.baijiaxing.base.BaseRequestCallBack;
import com.lishi.baijiaxing.wxapi.model.Login;

/**
 * Created by Administrator on 2016/11/30.
 */

public interface LoginCallback extends BaseRequestCallBack {
    void wxChatLoginSuccess(Login login);

    void localLoginSuccess(Login login);

    void qqLoginSuccess(Login login);

    void loginFailed(String error);
}
