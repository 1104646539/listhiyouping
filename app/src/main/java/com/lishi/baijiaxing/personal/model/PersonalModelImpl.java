package com.lishi.baijiaxing.personal.model;

import android.util.Log;

import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.bean.UserBean;
import com.lishi.baijiaxing.personal.PersonalCallback;
import com.lishi.baijiaxing.personal.network.PersonalService;
import com.lishi.baijiaxing.utils.LoginUtil;
import com.lishi.baijiaxing.wxapi.model.Login;
import com.lishi.baijiaxing.utils.Status;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/8/18.
 */
public class PersonalModelImpl extends BaseModel implements PersonalModel {
    private PersonalService mPersonalService;

    public PersonalModelImpl() {
        mPersonalService = (PersonalService) getRetrofitManager().getHomeService(PersonalService.class);
    }

    @Override
    public void obtainLogin(final PersonalCallback personalCallback) {
        
        personalCallback.isLogin(LoginUtil.getInstance().isLogin());
    }

    @Override
    public void Login(final PersonalCallback personalCallback, final UserBean userBean) {
        personalCallback.loginSuccess(userBean);
    }

    @Override
    public void loadData(final PersonalCallback personalCallback) {
//        final ArrayList<IntegralBean> integrals = new ArrayList<>();
//        IntegralBean integral1 = new IntegralBean(IntegralBean.TYPE_FUBI, 321);
//        IntegralBean integral2 = new IntegralBean(IntegralBean.TYPE_VOUCHER, 121);
//        IntegralBean integral3 = new IntegralBean(IntegralBean.TYPE_INTEGRAL, 21);
//        integrals.add(integral1);
//        integrals.add(integral2);
//        integrals.add(integral3);
//
//        personalCallback.onLoadBefore();
//        mPersonalService.loadData("phone.get", "18696287339", "10003", "b59bc3ef6191eb9f747dd4e83c99f2a4", "json").subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<IntegralBean>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        personalCallback.onLoadFailed(e.toString());
//                    }
//
//                    @Override
//                    public void onNext(IntegralBean integralBean) {
//                        personalCallback.onLoadSuccess(integrals);
//                    }
//                });
    }

    @Override
    public void getUserInfo(final PersonalCallback personalCallback, final Login login) {
        mPersonalService.getUserInfo("showInfo", login.getData().getUid(), login.getData().getToken(), login.getData().getType())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<com.lishi.baijiaxing.personal.model.LocalUserInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("getUserInfo", "登录失败:" + e.toString());
                        personalCallback.getUserInfoFailed(e.toString());
                    }

                    @Override
                    public void onNext(com.lishi.baijiaxing.personal.model.LocalUserInfo localUserInfo) {
                        Log.i("getUserInfo", "登录成功:" + localUserInfo.getMsg());
                        switch (localUserInfo.getStatus()) {
                            case Status.STATUS_GETUSERINFO_SUCCESS:
                                personalCallback.getUserInfoSuccess(localUserInfo);
                                break;
                            default:
                                personalCallback.getUserInfoFailed(localUserInfo.getMsg());
                                break;
                        }

                    }
                });
    }
}
