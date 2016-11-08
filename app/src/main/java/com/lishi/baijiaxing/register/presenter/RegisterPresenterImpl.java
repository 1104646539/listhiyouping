package com.lishi.baijiaxing.register.presenter;

import com.lishi.baijiaxing.bean.UserBean;
import com.lishi.baijiaxing.register.model.RegisterModelImpl;
import com.lishi.baijiaxing.register.view.RegisterView;

import javax.xml.transform.URIResolver;

/**
 * Created by Administrator on 2016/8/22.
 */
public class RegisterPresenterImpl implements RegisterPresenter {
    private RegisterView mRegisterView;
    private RegisterModelImpl mRegisterModel;

    public RegisterPresenterImpl(RegisterView registerView) {
        this.mRegisterView = registerView;
        this.mRegisterModel = new RegisterModelImpl();
    }

    @Override
    public void registerUser(UserBean userBean) {
        mRegisterModel.registerUser(this, userBean);
        mRegisterView.registerUser();
    }

    @Override
    public void sendCode(double phoneNumber) {
        mRegisterModel.sendCode(this, phoneNumber);
        mRegisterView.sendCode();
    }

    @Override
    public void verificationCode(double code) {
        mRegisterModel.verificationCode(this, code);
        mRegisterView.verificationCode();
    }

    @Override
    public void onSendCodeSuccess() {
        mRegisterView.onSendCodeSuccess();
    }

    @Override
    public void onSendCodeFailed() {
        mRegisterView.onSendCodeFailed();
    }

    @Override
    public void registerUserSuccess() {
        mRegisterView.registerUserSuccess();
    }

    @Override
    public void registerUserFailed() {
        mRegisterView.registerUserFailed();
    }

    @Override
    public void onVerificationCodeSuccess() {
        mRegisterView.onVerificationCodeSuccess();
    }

    @Override
    public void onVerificationCodeFailed() {
        mRegisterView.onVerificationCodeFailed();
    }
}
