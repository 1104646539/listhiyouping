package com.lishi.baijiaxing.retrievePassword.presenter;

/**
 * 找回密码
 * Created by Administrator on 2016/8/22.
 */
public interface RetrievePresenter {
    void sendCode(double phoneNumber);

    void retrieve(double phoneNumber, String password);

    void verificationCode(double code);

    void onSendCodeSuccess();

    void onSendCodeFailed();

    void onRetrieveSuccess();

    void onRetrieveFailed();

    void onVerificationCodeSuccess();

    void onVerificationCodeFailed();
}
