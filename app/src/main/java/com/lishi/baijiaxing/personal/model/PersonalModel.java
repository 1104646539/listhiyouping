package com.lishi.baijiaxing.personal.model;

import com.lishi.baijiaxing.bean.UserBean;
import com.lishi.baijiaxing.personal.PersonalCallback;
import com.lishi.baijiaxing.personal.presenter.PersonalPresenter;
import com.lishi.baijiaxing.wxapi.model.Login;

/**
 * Created by Administrator on 2016/8/18.
 */
public interface PersonalModel {
    void obtainLogin(PersonalCallback personalCallback);//是否有登录帐号，

    void Login(PersonalCallback personalCallback, UserBean userBean);

    void loadData(PersonalCallback personalCallback);

    void getUserInfo(PersonalCallback personalCallback, Login login);
}
