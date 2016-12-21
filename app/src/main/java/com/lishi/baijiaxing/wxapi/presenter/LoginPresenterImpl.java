package com.lishi.baijiaxing.wxapi.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.utils.LocalUserInfo;
import com.lishi.baijiaxing.wxapi.LoginCallback;
import com.lishi.baijiaxing.wxapi.model.Login;
import com.lishi.baijiaxing.wxapi.model.LoginModelImpl;
import com.lishi.baijiaxing.wxapi.model.QQUserInfo;
import com.lishi.baijiaxing.wxapi.model.WXUserInfo;
import com.lishi.baijiaxing.wxapi.view.LoginView;

/**
 * Created by Administrator on 2016/8/22.
 */
public class LoginPresenterImpl extends BasePresenter implements LoginPresenter, LoginCallback {
    private LoginView mLoginView;
    private LoginModelImpl mLoginModel;

    public LoginPresenterImpl(BaseView loginView) {
        super(loginView);
        this.mLoginView = (LoginView) loginView;
        this.mLoginModel = new LoginModelImpl();
    }

    @Override
    public void wxChatLogin(WXUserInfo userBean) {
        mLoginModel.wxChatLogin(this, userBean);
    }

    @Override
    public void qqLogin(QQUserInfo userInfo, String openid) {
        mLoginModel.qqLogin(this, userInfo, openid);
    }

    @Override
    public void localLogin(String name,String  paw) {
        mLoginModel.localLogin(this, name,paw);
    }

    @Override
    public void loginFailed(String error) {
        mLoginView.loginFailed(error);
    }

    @Override
    public void wxChatLoginSuccess(Login login) {
        mLoginView.wxChatLoginSuccess(login);
    }

    @Override
    public void localLoginSuccess(Login login) {
        mLoginView.localLoginSuccess(login);
    }

    @Override
    public void qqLoginSuccess(Login login) {
        mLoginView.qqLoginSuccess(login);
    }
}
