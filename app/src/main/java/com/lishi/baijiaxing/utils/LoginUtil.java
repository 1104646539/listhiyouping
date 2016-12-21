package com.lishi.baijiaxing.utils;

import com.facebook.stetho.common.LogUtil;
import com.lishi.baijiaxing.wxapi.model.Login;

/**
 * 用户登录管理类
 * Created by Administrator on 2016/12/1.
 */

public class LoginUtil {
    private Login login;

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    private LoginUtil() {
    }

    private static class LoginUtilHolder {
        private static LoginUtil loginUtil = new LoginUtil();
    }

    public static LoginUtil getInstance() {
        return LoginUtilHolder.loginUtil;
    }

    public boolean isLogin() {
        if (getInstance().getLogin() != null) {
            if (getInstance().getLogin().getData() != null && getInstance().getLogin().getStatus().equals(Status.STATUS_LOGIN_SUCCESS) && !getInstance().getLogin().getData().getUid().equals("")) {
                return true;
            }
            return false;
        }
        return false;
    }
}
