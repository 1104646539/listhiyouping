package com.lishi.baijiaxing.personal.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.bean.UserBean;
import com.lishi.baijiaxing.personal.PersonalCallback;
import com.lishi.baijiaxing.personal.model.PersonalModelImpl;
import com.lishi.baijiaxing.personal.view.PersonalView;

/**
 * Created by Administrator on 2016/8/18.
 */
public class PersonalPresenterImpl<T, V extends BaseView> extends BasePresenter implements PersonalPresenter, PersonalCallback {
    private PersonalModelImpl mPersonalModelImpl;
    private PersonalView mPersonalView;

    public PersonalPresenterImpl(BaseView baseView) {
        super(baseView);
        this.mPersonalView = (PersonalView) baseView;
        mPersonalModelImpl = new PersonalModelImpl();
    }

    @Override
    public void loadData() {
        mPersonalModelImpl.loadData(this);
    }

    @Override
    public void login(UserBean userBean) {
        mPersonalModelImpl.Login(this, userBean);
        mPersonalView.login();
    }

    @Override
    public void obtainLogin() {
        mPersonalModelImpl.obtainLogin(this);
    }

    @Override
    public void isLogin(boolean login) {
        mPersonalView.obtainLogin(login);
    }

    @Override
    public void loginSuccess(UserBean userBean) {
        mPersonalView.onLoginSuccess(userBean);
    }

    @Override
    public void loginFailed(String error) {
        mPersonalView.onLoginFailed(error);
    }
}
