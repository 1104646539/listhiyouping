package com.lishi.baijiaxing.wxapi.presenter;

import com.lishi.baijiaxing.bean.UserBean;
import com.lishi.baijiaxing.wxapi.model.SignInModelImpl;
import com.lishi.baijiaxing.wxapi.view.SignInView;

/**
 * Created by Administrator on 2016/8/22.
 */
public class SignInPresenterImpl implements SignInPresenter {
    private SignInView mSignInView;
    private SignInModelImpl mSignInModel;

    public SignInPresenterImpl(SignInView signInView) {
        this.mSignInView = signInView;
        this.mSignInModel = new SignInModelImpl();
    }

    @Override
    public void signIn(UserBean userBean) {
        mSignInModel.signIn(this, userBean);
    }

    @Override
    public void signInSuccess() {
        mSignInView.onSignInSuccess();
    }

    @Override
    public void signInFailed() {
        mSignInView.onSignInFailed();
    }
}
