package com.lishi.baijiaxing.personal;

import com.lishi.baijiaxing.base.BaseRequestCallBack;
import com.lishi.baijiaxing.bean.UserBean;

/**
 * Created by Administrator on 2016/10/10.
 */
public interface PersonalCallback<T> extends BaseRequestCallBack<T> {
    void isLogin(boolean login);

    void loginSuccess(UserBean userBean);

    void loginFailed(String error);

}
