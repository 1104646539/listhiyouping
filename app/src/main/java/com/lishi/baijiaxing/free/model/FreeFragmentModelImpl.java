package com.lishi.baijiaxing.free.model;

import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.free.FreeCallback;
import com.lishi.baijiaxing.free.FreeFragmentCallback;
import com.lishi.baijiaxing.free.network.FreeService;
import com.lishi.baijiaxing.free.presenter.FreeFragmentPresenterImpl;
import com.lishi.baijiaxing.utils.LoginUtil;
import com.lishi.baijiaxing.utils.Status;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/17.
 */
public class FreeFragmentModelImpl extends BaseModel implements FreeFragmentModel {
    private FreeService mFreeService;

    public FreeFragmentModelImpl() {
        mFreeService = (FreeService) getRetrofitManager().getHomeService(FreeService.class);
    }

    @Override
    public void loadData(final FreeFragmentCallback callback, int type) {
        if (!LoginUtil.getInstance().isLogin()) {
            mFreeService.getFreeListNotLogin(String.valueOf(type)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<FreeList>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            callback.loadFreeListFailed(e.toString());
                        }

                        @Override
                        public void onNext(FreeList list) {
                            if (list.getStatus().equals(Status.STATUS_GETUSERINFO_SUCCESS)) {
                                callback.loadFreeListSuccess(list);
                            } else {
                                callback.loadFreeListFailed(list.getMsg());
                            }
                        }
                    });
        } else {
            mFreeService.getFreeListLogin(LoginUtil.getInstance().getLogin().getData().getUid()
                    , LoginUtil.getInstance().getLogin().getData().getToken(), LoginUtil.getInstance().getLogin().getData().getType()
                    , String.valueOf(type)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<FreeList>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            callback.loadFreeListFailed(e.toString());
                        }

                        @Override
                        public void onNext(FreeList list) {
                            if (list.getStatus().equals(Status.STATUS_GETUSERINFO_SUCCESS)) {
                                callback.loadFreeListSuccess(list);
                            } else {
                                callback.loadFreeListFailed(list.getMsg());
                            }
                        }
                    });
        }
    }
}
