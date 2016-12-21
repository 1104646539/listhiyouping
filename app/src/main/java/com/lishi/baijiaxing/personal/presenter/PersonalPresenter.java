package com.lishi.baijiaxing.personal.presenter;

import com.lishi.baijiaxing.bean.UserBean;
import com.lishi.baijiaxing.wxapi.model.Login;

/**
 * Created by Administrator on 2016/8/18.
 */
public interface PersonalPresenter {
    void loadData();

    void login(UserBean userBean);

    void obtainLogin();

    void getUserInfo(Login login);
}
