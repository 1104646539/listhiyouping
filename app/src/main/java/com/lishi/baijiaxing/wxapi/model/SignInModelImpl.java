package com.lishi.baijiaxing.wxapi.model;

import com.lishi.baijiaxing.bean.UserBean;
import com.lishi.baijiaxing.wxapi.presenter.SignInPresenter;

/**
 * Created by Administrator on 2016/8/22.
 */
public class SignInModelImpl implements SignInModel {
    @Override
    public void signIn(SignInPresenter presenter, UserBean userBean) {
        presenter.signInSuccess();
    }
}
