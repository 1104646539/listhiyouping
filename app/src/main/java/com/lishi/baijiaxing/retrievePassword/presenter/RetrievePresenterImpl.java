package com.lishi.baijiaxing.retrievePassword.presenter;

import com.lishi.baijiaxing.retrievePassword.model.RetrieveModelImpl;
import com.lishi.baijiaxing.retrievePassword.view.RetrieveView;

/**
 * 忘记密码
 * Created by Administrator on 2016/8/22.
 */

public class RetrievePresenterImpl implements RetrievePresenter {

    private RetrieveView mRetrieveView;
    private RetrieveModelImpl mRetrieveModel;

    public RetrievePresenterImpl(RetrieveView retrieveView) {
        this.mRetrieveView = retrieveView;
        mRetrieveModel = new RetrieveModelImpl();
    }

    @Override
    public void sendCode(double phoneNumber) {
        mRetrieveModel.sendCode(this, phoneNumber);
        mRetrieveView.sendCode();
    }

    @Override
    public void retrieve(double phoneNumber, String password) {
        mRetrieveModel.retrieve(this, phoneNumber, password);
        mRetrieveView.retrieve();
    }

    @Override
    public void verificationCode(double code) {
        mRetrieveModel.verificationCode(this, code);
        mRetrieveView.verificationCode();
    }

    @Override
    public void onSendCodeSuccess() {
        mRetrieveView.onSendCodeSuccess();
    }

    @Override
    public void onSendCodeFailed() {
    mRetrieveView.onSendCodeFailed();
    }

    @Override
    public void onRetrieveSuccess() {
mRetrieveView.onRetrieveSuccess();
    }

    @Override
    public void onRetrieveFailed() {
mRetrieveView.onRetrieveFailed();
    }

    @Override
    public void onVerificationCodeSuccess() {
mRetrieveView.onVerificationCodeSuccess();
    }

    @Override
    public void onVerificationCodeFailed() {
mRetrieveView.onVerificationCodeFailed();
    }
}
