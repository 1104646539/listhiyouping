package com.lishi.baijiaxing.retrievePassword.model;

import com.lishi.baijiaxing.retrievePassword.presenter.RetrievePresenter;

/**
 * 找回密码
 * Created by Administrator on 2016/8/22.
 */
public interface RetrieveModel {
    void retrieve(RetrievePresenter presenter, double phoneNumber,String password);

    void sendCode(RetrievePresenter presenter, double phoneNumber);

    void verificationCode(RetrievePresenter presenter, double code);
    
}
