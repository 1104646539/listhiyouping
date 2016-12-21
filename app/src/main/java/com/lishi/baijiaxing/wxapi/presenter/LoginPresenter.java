package com.lishi.baijiaxing.wxapi.presenter;

import com.lishi.baijiaxing.bean.UserBean;
import com.lishi.baijiaxing.utils.LocalUserInfo;
import com.lishi.baijiaxing.wxapi.model.QQUserInfo;
import com.lishi.baijiaxing.wxapi.model.WXUserInfo;

/**
 * 登录
 * Created by Administrator on 2016/8/22.
 */
public interface LoginPresenter {
    void wxChatLogin(WXUserInfo userBean);

    void qqLogin(QQUserInfo userInfo,String openid);

    void localLogin(String name,String  paw);

    void loginFailed(String error);
}
