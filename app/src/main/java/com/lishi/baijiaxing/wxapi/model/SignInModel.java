package com.lishi.baijiaxing.wxapi.model;

import com.lishi.baijiaxing.bean.UserBean;
import com.lishi.baijiaxing.wxapi.presenter.SignInPresenter;

/**登录
 * Created by Administrator on 2016/8/22.
 */
public interface SignInModel {
    void signIn(SignInPresenter presenter, UserBean userBean);
    
}
