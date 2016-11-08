package com.lishi.baijiaxing.register.model;

import com.lishi.baijiaxing.bean.UserBean;
import com.lishi.baijiaxing.register.presenter.RegisterPresenter;

/**
 * Created by Administrator on 2016/8/22.
 */
public class RegisterModelImpl implements RegisterModel {
    @Override
    public void registerUser(RegisterPresenter presenter, UserBean userBean) {
        presenter.registerUserSuccess();
    }

    @Override
    public void sendCode(RegisterPresenter presenter, double phoneNumber) {
        presenter.onSendCodeSuccess();
    }

    @Override
    public void verificationCode(RegisterPresenter presenter, double code) {
        presenter.onVerificationCodeSuccess();
    }
}
