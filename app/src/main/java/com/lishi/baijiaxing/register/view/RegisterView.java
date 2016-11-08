package com.lishi.baijiaxing.register.view;

import com.lishi.baijiaxing.bean.UserBean;

/**
 * 注册
 * Created by Administrator on 2016/8/22.
 */
public interface RegisterView {
    void registerUser();

    void sendCode();

    void verificationCode();

    void onSendCodeSuccess();

    void onSendCodeFailed();

    void registerUserSuccess();

    void registerUserFailed();

    void onVerificationCodeSuccess();

    void onVerificationCodeFailed();
}
