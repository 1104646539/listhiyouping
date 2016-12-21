package com.lishi.baijiaxing.free.model;

import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.free.FreeCallback;
import com.lishi.baijiaxing.free.network.FreeService;
import com.lishi.baijiaxing.utils.Status;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/17.
 */
public class FreeModelImpl extends BaseModel implements FreeModel {
    private FreeService mFreeService;

    public FreeModelImpl() {
        mFreeService = (FreeService) getRetrofitManager().getHomeService(FreeService.class);
    }

    @Override
    public void loadData(final FreeCallback callback) {
        mFreeService.getFreeAdList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FreeAdList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.loadAdListFailed(e.toString());
                    }

                    @Override
                    public void onNext(FreeAdList list) {
                        if (list.getStatus().equals(Status.STATUS_GETUSERINFO_SUCCESS)) {
                            callback.loadAdListSuccess(list);
                        } else {
                            callback.loadAdListFailed(list.getMsg());
                        }
                    }
                });
    }

}
