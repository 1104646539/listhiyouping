package com.lishi.baijiaxing.details.model;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.details.CommodityBriefCallback;
import com.lishi.baijiaxing.details.network.DetailsService;
import com.lishi.baijiaxing.utils.Status;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/1.
 */
public class CommodityBriefModelImpl extends BaseModel implements CommodityBriefModel {
    private DetailsService mDetailsService;

    public CommodityBriefModelImpl() {
        mDetailsService = (DetailsService) getRetrofitManager().getHomeService(DetailsService.class);
    }

    @Override
    public void loadData(final CommodityBriefCallback callback, String gid) {
        mDetailsService.getCommodityDetails("goodsDetails", gid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CommodityDetails>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Logger.d(e.toString());
                callback.onLoadFailed(e.toString());
            }

            @Override
            public void onNext(CommodityDetails commodityDetails) {
                Logger.d(commodityDetails);
                if (commodityDetails.getStatus().equals(Status.STATUS_GETUSERINFO_SUCCESS)) {
                    callback.loadSuccess(commodityDetails);
                } else {
                    callback.loadFailed(commodityDetails.getMsg());
                }
            }
        });
    }
}
