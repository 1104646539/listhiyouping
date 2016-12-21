package com.lishi.baijiaxing.wxapi.model;

import android.util.Log;

import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.utils.Status;
import com.lishi.baijiaxing.wxapi.LoginCallback;
import com.lishi.baijiaxing.wxapi.network.LoginService;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/8/22.
 */
public class LoginModelImpl extends BaseModel implements LoginModel {
    private LoginService mLoginService;

    public LoginModelImpl() {
        mLoginService = (LoginService) getRetrofitManager().getHomeService(LoginService.class);
    }

    @Override
    public void wxChatLogin(final LoginCallback callback, WXUserInfo userBean) {
        mLoginService.wxChatLogin("userLogin", userBean.getOpenid(), userBean.getNickname(), String.valueOf(userBean.getSex()), userBean.getLanguage(), userBean.getCity()
                , userBean.getProvince(), userBean.getCountry(), userBean.getHeadimgurl(), userBean.getUnionid(), "weChat").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Login>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("wxChatLogin", "登录失败" + e.toString());
                callback.loginFailed(e.toString());
            }

            @Override
            public void onNext(Login login) {
                Log.i("wxChatLogin", "登录成功" + login.toString());
                switch (login.getStatus()) {
                    case Status.STATUS_LOGIN_SUCCESS:
                        callback.wxChatLoginSuccess(login);
                        break;
                    case Status.STATUS_PAW_FAILED:
                        callback.loginFailed(login.getMsg());
                        break;
                    case Status.STATUS_TOKEN_LOSE_EFFICACY:
                        callback.loginFailed(login.getMsg());
                        break;
                    case Status.STATUS_USER_NO_FOUNT:
                        callback.loginFailed(login.getMsg());
                        break;
                    default:
                        callback.loginFailed(login.getMsg());
                }
            }
        });
    }

    @Override
    public void qqLogin(final LoginCallback callback, final QQUserInfo qqUserInfo, String openid) {
        Log.i("qqLogin", openid + qqUserInfo.getNickname() + qqUserInfo.getGender() + qqUserInfo.getProvince() + qqUserInfo.getCity() +
                qqUserInfo.getFigureurl_qq_2() + "qq");
        mLoginService.qqLogin("userLogin", openid, qqUserInfo.getNickname(), qqUserInfo.getGender(), qqUserInfo.getProvince()
                , qqUserInfo.getCity(), qqUserInfo.getFigureurl_qq_2(), "qq")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Login>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("qqLogin", "登录失败" + e.toString());
                        callback.loginFailed(e.toString());
                    }

                    @Override
                    public void onNext(Login login) {
                        Log.i("qqLogin", "登录成功" + login.toString());
                        switch (login.getStatus()) {
                            case Status.STATUS_LOGIN_SUCCESS:
                                callback.qqLoginSuccess(login);
                                break;
                            case Status.STATUS_PAW_FAILED:
                                callback.loginFailed(login.getMsg());
                                break;
                            case Status.STATUS_TOKEN_LOSE_EFFICACY:
                                callback.loginFailed(login.getMsg());
                                break;
                            case Status.STATUS_USER_NO_FOUNT:
                                callback.loginFailed(login.getMsg());
                                break;
                            default:
                                callback.loginFailed(login.getMsg());
                        }
                    }
                });
    }

    @Override
    public void localLogin(final LoginCallback callback, String name,String  paw) {
        mLoginService.localLogin("userLogin", name, paw,
                "local").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Login>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("localLogin", "登录失败" + e.toString());
                    }

                    @Override
                    public void onNext(Login login) {
                        Log.i("localLogin", "登录成功" + login.toString());
                        switch (login.getStatus()) {
                            case Status.STATUS_LOGIN_SUCCESS:
                                callback.localLoginSuccess(login);
                                break;
                            case Status.STATUS_PAW_FAILED:
                                callback.loginFailed(login.getMsg());
                                break;
                            case Status.STATUS_TOKEN_LOSE_EFFICACY:
                                callback.loginFailed(login.getMsg());
                                break;
                            case Status.STATUS_USER_NO_FOUNT:
                                callback.loginFailed(login.getMsg());
                                break;
                            default:
                                callback.loginFailed(login.getMsg());
                        }
                    }
                });
    }
}
