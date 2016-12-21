package com.lishi.baijiaxing.personal;

import com.lishi.baijiaxing.base.BaseRequestCallBack;
import com.lishi.baijiaxing.bean.UserBean;
import com.lishi.baijiaxing.personal.model.LocalUserInfo;

/**
 * Created by Administrator on 2016/10/10.
 */
public interface PersonalCallback<T> extends BaseRequestCallBack<T> {
    void isLogin(boolean login);

    void loginSuccess(UserBean userBean);

    void loginFailed(String error);

    void getUserInfoSuccess(LocalUserInfo localUserInfo);

    void getUserInfoFailed(String error);
}
