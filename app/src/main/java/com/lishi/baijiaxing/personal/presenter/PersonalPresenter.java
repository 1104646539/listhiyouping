package com.lishi.baijiaxing.personal.presenter;

import com.lishi.baijiaxing.bean.UserBean;

/**
 * Created by Administrator on 2016/8/18.
 */
public interface PersonalPresenter {
    void loadData();

    void login(UserBean userBean);

    void obtainLogin();
}
