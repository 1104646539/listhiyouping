package com.lishi.baijiaxing.retrievePassword.view;

/**
 * 找回密码
 * Created by Administrator on 2016/8/22.
 */
public interface RetrieveView {
    void sendCode();

    void retrieve();

    void verificationCode();

    void onSendCodeSuccess();

    void onSendCodeFailed();

    void onRetrieveSuccess();

    void onRetrieveFailed();

    void onVerificationCodeSuccess();

    void onVerificationCodeFailed();
}
