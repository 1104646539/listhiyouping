package com.lishi.baijiaxing.hot.model;

import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.hot.HotCommodityCallback;
import com.lishi.baijiaxing.hot.network.HotCommodityService;
import com.lishi.baijiaxing.utils.Status;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/8.
 */

public class HotCommodityModelImpl extends BaseModel implements HotCommodityModel {
    private HotCommodityService mHotCommodityService;

    public HotCommodityModelImpl() {
        mHotCommodityService = (HotCommodityService) getRetrofitManager().getHomeService(HotCommodityService.class);
    }

    @Override
    public void loadData(final HotCommodityCallback callback) {
        mHotCommodityService.getHotCommodityList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HotCommodity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.loadDataFailed(e.toString());
                    }

                    @Override
                    public void onNext(HotCommodity hotCommodity) {
                        if (hotCommodity.getStatus().equals(Status.STATUS_GETUSERINFO_SUCCESS)) {
                            callback.loadDataSuccess(hotCommodity);
                        } else {
                            callback.loadDataFailed(hotCommodity.getMsg());
                        }
                    }
                });
    }
}
