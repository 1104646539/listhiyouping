package com.lishi.baijiaxing.yiyuan.model;

import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.utils.LoginUtil;
import com.lishi.baijiaxing.utils.Status;
import com.lishi.baijiaxing.yiyuan.YiYuanHotCallback;
import com.lishi.baijiaxing.yiyuan.network.YiYuanService;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/24.
 */
public class YiYuanHotModelImpl extends BaseModel implements YiYuanHotModel {
    private YiYuanService mYiYuanService;
    private String status = "1";

    public YiYuanHotModelImpl() {
        mYiYuanService = (YiYuanService) getRetrofitManager().getHomeService(YiYuanService.class);
    }

    @Override
    public void loadData(final YiYuanHotCallback callback) {
        if (LoginUtil.getInstance().isLogin()) {
            mYiYuanService.getHotList(LoginUtil.getInstance().getLogin().getData().getUid(), LoginUtil.getInstance().getLogin().getData().getToken()
                    , LoginUtil.getInstance().getLogin().getData().getType(), status).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<HotList>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            callback.loadHotListFailed(e.toString());
                        }

                        @Override
                        public void onNext(HotList hotList) {
                            if (hotList.getStatus().equals(Status.STATUS_GETUSERINFO_SUCCESS)) {
                                callback.loadHotListSuccess(hotList);
                            } else {
                                callback.loadHotListFailed(hotList.getMsg());
                            }
                        }
                    });
        } else {
            mYiYuanService.getHotListNotLogin(status).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<HotList>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            callback.loadHotListFailed(e.toString());
                        }

                        @Override
                        public void onNext(HotList hotList) {
                            if (hotList.getStatus().equals(Status.STATUS_GETUSERINFO_SUCCESS)) {
                                callback.loadHotListSuccess(hotList);
                            } else {
                                callback.loadHotListFailed(hotList.getMsg());
                            }
                        }
                    });
        }
    }
}
