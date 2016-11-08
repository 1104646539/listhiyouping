package com.lishi.baijiaxing.personal.view;

import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.bean.UserBean;

/**
 * Created by Administrator on 2016/8/18.
 */
public interface PersonalView extends BaseView {
    void login();

    void obtainLogin(boolean isLogin);

    void onLoginSuccess(UserBean userBean);

    void onLoginFailed(String error);
}
