package com.lishi.baijiaxing.free.model;

import com.google.gson.Gson;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.free.FreeDetailsCallback;
import com.lishi.baijiaxing.free.network.FreeService;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrder;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrderModelImpl;
import com.lishi.baijiaxing.utils.LoginUtil;
import com.lishi.baijiaxing.utils.Status;
import com.lishi.baijiaxing.wxapi.model.Login;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/21.
 */
public class FreeDetailsModelImpl extends BaseModel implements FreeDetailsModel {
    private FreeService mFreeService;

    public FreeDetailsModelImpl() {
        mFreeService = (FreeService) getRetrofitManager().getHomeService(FreeService.class);
    }


    @Override
    public void loadData(final FreeDetailsCallback callback, String type, String gid, String zid) {
        if (!LoginUtil.getInstance().isLogin()) {
            mFreeService.getFreeDetailsNotLogin(zid, gid, type).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<FreeDetails>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            callback.loadDetailsFailed(e.toString());
                        }

                        @Override
                        public void onNext(FreeDetails freeDetails) {
                            if (freeDetails.getStatus().equals(Status.STATUS_GETUSERINFO_SUCCESS)) {
                                callback.loadDetailsSuccess(freeDetails);
                            } else {
                                callback.loadDetailsFailed(freeDetails.getMsg());
                            }
                        }
                    });
        } else {
            mFreeService.getFreeDetailsLogin(LoginUtil.getInstance().getLogin().getData().getUid()
                    , LoginUtil.getInstance().getLogin().getData().getToken(), LoginUtil.getInstance().getLogin().getData().getType(),
                    zid, gid, type).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<FreeDetails>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            callback.loadDetailsFailed(e.toString());
                        }

                        @Override
                        public void onNext(FreeDetails freeDetails) {
                            if (freeDetails.getStatus().equals(Status.STATUS_GETUSERINFO_SUCCESS)) {
                                callback.loadDetailsSuccess(freeDetails);
                            } else {
                                callback.loadDetailsFailed(freeDetails.getMsg());
                            }
                        }
                    });
        }
    }

    @Override
    public void submitApply(final FreeDetailsCallback callback, FreeDetails freeDetails, String aid, String type) {
        if (!LoginUtil.getInstance().isLogin()) {
            callback.submitApplyFailed("未登录");
        }
        String str;
        List<FreeSubmit> freeSubmits = new ArrayList<>();
        FreeSubmit freeSubmit = new FreeSubmit();
        freeSubmit.setGid(freeDetails.getData().getGid());
        freeSubmit.setZid(freeDetails.getData().getZid());
        freeSubmit.setType(type);
        freeSubmits.add(freeSubmit);
        str = freeSubmits.toString();
        Logger.d("str:" + str + "aid:" + aid);

        Gson gson = new Gson();
        String data = gson.toJson(freeSubmits);
        Logger.d(data);
        mFreeService.submitApply(LoginUtil.getInstance().getLogin().getData().getUid(), LoginUtil.getInstance().getLogin().getData().getToken()
                , LoginUtil.getInstance().getLogin().getData().getType(), data, aid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<SubmitOrder>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.submitApplyFailed(e.toString());
            }

            @Override
            public void onNext(SubmitOrder submitOrder) {
                if (submitOrder.getStatus().equals(Status.STATUS_ORDER_SUCCESS)) {
                    callback.submitApplySuccess(submitOrder);
                } else {
                    callback.submitApplyFailed(submitOrder.getMsg());
                }
            }
        });
    }

    public class FreeSubmit {
        String zid;
        String gid;
        String type;

        public String getZid() {
            return zid;
        }

        public void setZid(String zid) {
            this.zid = zid;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
