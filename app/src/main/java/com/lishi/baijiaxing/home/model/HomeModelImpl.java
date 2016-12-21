package com.lishi.baijiaxing.home.model;

import android.hardware.camera2.params.Face;
import android.util.Log;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.home.HomeCallBack;
import com.lishi.baijiaxing.home.network.HomeService;
import com.lishi.baijiaxing.bean.HomeRecommendBean;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/8/17.
 */
public class HomeModelImpl extends BaseModel implements HomeModel {
    private HomeService mHomeService;
    private int page = 1;
    private boolean isPrepare = true;

    public HomeModelImpl() {
        mHomeService = (HomeService) getRetrofitManager().getHomeService(HomeService.class);
    }

    @Override
    public void pullRecommendData(final HomeCallBack callBack) {
        mHomeService.pullToRefresh("sucpList", String.valueOf(++page)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Commodity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        page--;
                        callBack.pullToRefreshFailed(e.toString());
                    }

                    @Override
                    public void onNext(Commodity commodities) {
                        callBack.pullToRefreshSuccess(commodities.getData());
                    }
                });
    }

    @Override
    public void getAdList(final HomeCallBack callBack) {
        mHomeService.getAdList("adList").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AdList>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("getAdList", "失败数据" + e.toString());callBack.getAdListFailed(e.toString());
                    }

                    @Override
                    public void onNext(AdList adLists) {
//                        Log.i("getAdList", "成功数据" + adLists.get(0).toString());
                        callBack.getAdListSuccess(adLists.getData());
                    }
                });
    }


    @Override
    public void getFestival(final HomeCallBack callBack) {
        mHomeService.getFestival("festival").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Festival>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("getFestival", "失败数据" + e.toString());callBack.getFestivalFailed(e.toString());
                    }

                    @Override
                    public void onNext(Festival festival) {
                        Log.i("getFestival", "成功数据" + festival.toString());
                        callBack.getFestivalSuccess(festival.getData().get(0));
                    }
                });
    }

    @Override
    public void getSeckill(final HomeCallBack callBack) {
        mHomeService.getSeckilBean("seckill").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Seckill>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("getSeckill", "失败数据" + e.toString());callBack.getSeckillFailed(e.toString());
                    }

                    @Override
                    public void onNext(Seckill seckill) {
                        Log.i("getSeckill", "成功数据" + seckill.toString());
                        callBack.getSeckillSuccess(seckill.getData());
                    }
                });
    }

    @Override
    public void getCommodityList(final HomeCallBack callBack) {
        mHomeService.getCommodity("commodityList").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Commodity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("加载失败", "失败数据" + e.toString());
                        if (isPrepare) {
                            isPrepare = false;
                        }callBack.getCommodityListFailed(e.toString());
                    }

                    @Override
                    public void onNext(Commodity commodity) {
                        callBack.getCommodityListSuccess(commodity.getData());
                        Log.i("getCommodityList", "成功数据" + commodity.toString() + "size" + commodity.getMsg());
                        if (isPrepare) {
                            isPrepare = false;
                        } else {
                            page = 1;
                        }
                    }
                });
    }
}
