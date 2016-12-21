package com.lishi.baijiaxing.utils;

import android.util.Log;

import com.lishi.baijiaxing.personal.model.LocalUserInfo;

/**
 * 保存的用户信息
 * Created by Administrator on 2016/12/1.
 */

public class UserUtil {
    private LocalUserInfo mLogin;

    private UserUtil() {
    }

    public LocalUserInfo getLogin() {
        return mLogin;
    }

    public void setLogin(LocalUserInfo login) {
        mLogin = login;
    }

    private static class UserUtilHolder {
        private static UserUtil userUtil = new UserUtil();
    }

    public static UserUtil getInstance() {
        return UserUtilHolder.userUtil;
    }

    public boolean isLogin() {
        if (getInstance().getLogin() != null) {
            Log.i("已登录", "getInstance().getLogin().getStatus()：" + getInstance().getLogin().getStatus() + " getInstance().getLogin().getData().getAid():" + getInstance().getLogin().getData().getAid()
                    + "getInstance().getLogin().getData():" + getInstance().getLogin().getData());
            if (getInstance().getLogin().getStatus().equals(Status.STATUS_GETUSERINFO_SUCCESS) &&
                    getInstance().getLogin().getData().getAid() != null && !getInstance().getLogin().getData().getAid().equals("")) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }
}
