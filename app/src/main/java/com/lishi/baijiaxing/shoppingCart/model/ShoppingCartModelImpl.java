package com.lishi.baijiaxing.shoppingCart.model;

import android.util.Log;

import com.facebook.stetho.common.LogUtil;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.bean.HomeRecommendBean;
import com.lishi.baijiaxing.shoppingCart.ShoppingCartCallback;
import com.lishi.baijiaxing.shoppingCart.network.ShoppingCartService;
import com.lishi.baijiaxing.utils.LoginUtil;
import com.lishi.baijiaxing.utils.UserUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/8/18.
 */
public class ShoppingCartModelImpl extends BaseModel implements ShoppingCartModel {
    private static final String TAG = "ShoppingCartModelImpl";
    private final ShoppingCartService mShoppingCartService;

    public ShoppingCartModelImpl() {
        mShoppingCartService = (ShoppingCartService) getRetrofitManager().getHomeService(ShoppingCartService.class);
    }


    @Override
    public void loadCommodityList(final ShoppingCartCallback callback) {
        if (!LoginUtil.getInstance().isLogin()) {
            callback.loadCommodityListFailed("未登录");
            return;
        }
        mShoppingCartService.getCommodityList(LoginUtil.getInstance().getLogin().getData().getUid(), LoginUtil.getInstance().getLogin().getData().getToken(),
                LoginUtil.getInstance().getLogin().getData().getType()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SCCommodityList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("mShoppingCartService", "onError" + e.toString());
                        callback.loadCommodityListFailed(e.toString());
                    }

                    @Override
                    public void onNext(SCCommodityList commodityList) {
                        Log.i("mShoppingCartService", "status:" + commodityList.getData().size() + "msg:" + commodityList.getData().size());
                        callback.loadCommodityListSuccess(commodityList);
                    }
                });
    }

    @Override
    public void loadRecommendList(final ShoppingCartCallback callback) {
        mShoppingCartService.getRecommendList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SCRecommendList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("mShoppingCartService", "onError" + e.toString());
                        callback.loadRecommendListFailed(e.toString());
                    }

                    @Override
                    public void onNext(SCRecommendList commodityList) {
                        Log.i("mShoppingCartService", "status:" + commodityList.getStatus() + "msg:" + commodityList.getMsg());
                        callback.loadRecommendListSuccess(commodityList);
                    }
                });
    }

    @Override
    public void removeCommodity(final ShoppingCartCallback callback, List<String> deleteIds) {
        if (!LoginUtil.getInstance().isLogin()) {
            callback.removeCommodityFailed("未登录");
            return;
        }
        mShoppingCartService.removeCommodity(LoginUtil.getInstance().getLogin().getData().getUid(), LoginUtil.getInstance().getLogin().getData().getToken(),
                LoginUtil.getInstance().getLogin().getData().getType(), deleteIds.toString()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SCOperation>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("mShoppingCartService", "onError" + e.toString());
                        callback.removeCommodityFailed(e.toString());
                    }

                    @Override
                    public void onNext(SCOperation scOperation) {
                        Log.i("mShoppingCartService", "status:" + scOperation.getStatus() + "msg:" + scOperation.getMsg());
                        callback.removeCommoditySuccess(scOperation);
                    }
                });
    }


    @Override
    public void pullDownLoad(ShoppingCartCallback cartCallback) {

    }

    @Override
    public void changeCommodity(final ShoppingCartCallback callback, SCCommodityList.DataBean dataBean, String number) {
        if (!LoginUtil.getInstance().isLogin()) {
            callback.changeCommodityFailed("未登录");
            return;
        }
        mShoppingCartService.changeCommodity(LoginUtil.getInstance().getLogin().getData().getUid(), LoginUtil.getInstance().getLogin().getData().getToken(),
                LoginUtil.getInstance().getLogin().getData().getType(), dataBean.getCid(), number).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SCOperation>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("mShoppingCartService", "onError" + e.toString());
                        callback.changeCommodityFailed(e.toString());
                    }

                    @Override
                    public void onNext(SCOperation scOperation) {
                        Log.i("mShoppingCartService", "status:" + scOperation.getStatus() + "msg:" + scOperation.getMsg());
                        callback.changeCommoditySuccess(scOperation);
                    }
                });
    }

    @Override
    public void upCommodityInfo(final ShoppingCartCallback callback, SCCommodityList.DataBean dataBean) {
        if (!LoginUtil.getInstance().isLogin()) {
            callback.upCommodityInfoFailed("未登录");
            return;
        }
        mShoppingCartService.upCommodityInfo(LoginUtil.getInstance().getLogin().getData().getUid(), LoginUtil.getInstance().getLogin().getData().getToken(),
                LoginUtil.getInstance().getLogin().getData().getType(), dataBean.getCid()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SCCommodityList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("mShoppingCartService", "onError" + e.toString());
                        callback.upCommodityInfoFailed(e.toString());
                    }

                    @Override
                    public void onNext(SCCommodityList commodityList) {
                        Log.i("mShoppingCartService", "status:" + commodityList.getData().size() + "msg:" + commodityList.getData().size());
                        callback.upCommodityInfoSuccess(commodityList);
                    }
                });
    }
}
