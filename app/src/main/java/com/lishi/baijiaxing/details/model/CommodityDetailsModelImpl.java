package com.lishi.baijiaxing.details.model;

import android.util.Log;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.customize.model.NormsBean;
import com.lishi.baijiaxing.details.CommodityCommentCallback;
import com.lishi.baijiaxing.details.CommodityDetailsCallback;
import com.lishi.baijiaxing.details.network.DetailsService;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;
import com.lishi.baijiaxing.utils.LoginUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/1.
 */
public class CommodityDetailsModelImpl extends BaseModel implements CommodityDetailsModel {
    private DetailsService mDetailsService;

    public CommodityDetailsModelImpl() {
        mDetailsService = (DetailsService) getRetrofitManager().getHomeService(DetailsService.class);
    }

    @Override
    public void loadData(final CommodityDetailsCallback callback, String gid) {
        mDetailsService.getCommodityDetails("goodsDetails", gid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CommodityDetails>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.loadFailed(e.toString());
            }

            @Override
            public void onNext(CommodityDetails commodityDetails) {
                callback.loadSuccess(commodityDetails.getData());
            }
        });
    }

    @Override
    public void addCart(final CommodityDetailsCallback callback, String gid, String number) {
        if (!LoginUtil.getInstance().isLogin()) {
            callback.addCartFailed("未登录");
        }
        mDetailsService.addCommodity(LoginUtil.getInstance().getLogin().getData().getUid(), LoginUtil.getInstance().getLogin().getData().getToken()
                , LoginUtil.getInstance().getLogin().getData().getType(), gid, number).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SCOperation>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.addCartFailed(e.toString());
                    }

                    @Override
                    public void onNext(SCOperation scOperation) {
                        Log.i("addCart", "onNext:" + scOperation.getStatus() + scOperation.getMsg());
                        callback.addCartSuccess(scOperation);
                    }

                });

    }

}
