package com.lishi.baijiaxing.register.presenter;

import com.lishi.baijiaxing.bean.UserBean;

/**
 * 注册
 * Created by Administrator on 2016/8/22.
 */
public interface RegisterPresenter {
    void registerUser(UserBean userBean);

    void sendCode(double phoneNumber);

    void verificationCode(double code);

    void onSendCodeSuccess();

    void onSendCodeFailed();

    void registerUserSuccess();

    void registerUserFailed();

    void onVerificationCodeSuccess();

    void onVerificationCodeFailed();
}
