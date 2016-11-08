package com.lishi.baijiaxing.retrievePassword.model;

import com.lishi.baijiaxing.retrievePassword.presenter.RetrievePresenter;

/**
 * 忘记密码
 * Created by Administrator on 2016/8/22.
 */
public class RetrieveModelImpl implements RetrieveModel {
    @Override
    public void retrieve(RetrievePresenter presenter, double phoneNumber, String password) {
        presenter.onRetrieveSuccess();
    }

    @Override
    public void sendCode(RetrievePresenter presenter, double phoneNumber) {
        presenter.onSendCodeSuccess();
    }

    @Override
    public void verificationCode(RetrievePresenter presenter, double code) {
        presenter.onVerificationCodeSuccess();
    }
}
