package com.lishi.baijiaxing.register.model;

import com.lishi.baijiaxing.bean.UserBean;
import com.lishi.baijiaxing.register.presenter.RegisterPresenter;

/**
 * 注册
 * Created by Administrator on 2016/8/22.
 */
public interface RegisterModel {
    void registerUser(RegisterPresenter presenter, UserBean userBean);

    void sendCode(RegisterPresenter presenter, double phoneNumber);

    void verificationCode(RegisterPresenter presenter, double code);
}
