package com.lishi.baijiaxing.personal.model;

import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.bean.UserBean;
import com.lishi.baijiaxing.personal.PersonalCallback;
import com.lishi.baijiaxing.personal.network.PersonalService;

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
        personalCallback.onLoadBefore();
        mPersonalService.isLogin("phone.get", "18696287339", "10003", "b59bc3ef6191eb9f747dd4e83c99f2a4", "json").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        personalCallback.isLogin(false);
                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        personalCallback.isLogin(true);
                    }
                });
    }

    @Override
    public void Login(final PersonalCallback personalCallback, final UserBean userBean) {
        personalCallback.onLoadBefore();
        mPersonalService.login("phone.get", "18696287339", "10003", "b59bc3ef6191eb9f747dd4e83c99f2a4", "json").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        personalCallback.loginFailed(e.toString());
                    }

                    @Override
                    public void onNext(UserBean userBean1) {
                        personalCallback.loginSuccess(userBean);
                    }
                });


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
}
