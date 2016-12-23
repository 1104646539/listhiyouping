package com.lishi.baijiaxing.yiyuan.model;

import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.utils.LoginUtil;
import com.lishi.baijiaxing.utils.Status;
import com.lishi.baijiaxing.yiyuan.YiYuanHotCallback;
import com.lishi.baijiaxing.yiyuan.YiYuanNewestCallback;
import com.lishi.baijiaxing.yiyuan.network.YiYuanService;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/24.
 */
public class YiYuanNewestModelImpl extends BaseModel implements YiYuanNewestModel {
    private YiYuanService mYiYuanService;
    private String status = "2";
    private int mPage = 0;
    private boolean isFirst;

    public YiYuanNewestModelImpl() {
        mYiYuanService = (YiYuanService) getRetrofitManager().getHomeService(YiYuanService.class);
    }

    @Override
    public void loadData(final YiYuanNewestCallback callback, final int page) {
        final String sPage;
        if (page == -1) {
            sPage = mPage + 1 + "";//上拉加载
        } else if (page == 0) {
            sPage = mPage + "";//刷新(延续上次的页数)
        } else {
            sPage = "1";////第一次加载，刷新
            isFirst = true;
        }
        if (LoginUtil.getInstance().isLogin()) {
            mYiYuanService.getLotteryList(LoginUtil.getInstance().getLogin().getData().getUid(), LoginUtil.getInstance().getLogin().getData().getToken()
                    , LoginUtil.getInstance().getLogin().getData().getType(), status, sPage)
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<LotteryList>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    Logger.d("error:" + e.toString());
                    callback.loadLotteryListFailed(e.toString());
                }

                @Override
                public void onNext(LotteryList lotteryList) {
                    if (lotteryList.getStatus().equals(Status.STATUS_GETUSERINFO_SUCCESS)) {
                        if (page == -1 || isFirst) {
                            if (lotteryList.getData().getPageNum().equals(String.valueOf(mPage))) {
                                isFirst = false;
                            } else {
                                mPage = mPage + 1;//上拉加载
                                isFirst = false;
                            }
                        }
                        callback.loadLotteryListSuccess(lotteryList);
                        if (lotteryList.getData().getPage().equals(lotteryList.getData().getPageNum())) {
                            callback.onLastPage(sPage);
                        }
                        Logger.d("page:" + sPage);
                    } else {
                        Logger.d("error:" + lotteryList.getMsg());
                        callback.loadLotteryListFailed(lotteryList.getMsg());
                    }
                }
            });
        } else {
            mYiYuanService.getLotteryListNotLogin(status, sPage).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<LotteryList>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    Logger.d("error:" + e.toString());
                    callback.loadLotteryListFailed(e.toString());
                }

                @Override
                public void onNext(LotteryList lotteryList) {
                    if (lotteryList.getStatus().equals(Status.STATUS_GETUSERINFO_SUCCESS)) {
                        if (page == -1 || isFirst) {
                            if (lotteryList.getData().getPageNum().equals(String.valueOf(mPage))) {
                                isFirst = false;
                            } else {
                                mPage = mPage + 1;//上拉加载
                                isFirst = false;
                            }
                        }
                        callback.loadLotteryListSuccess(lotteryList);
                        if (lotteryList.getData().getPage().equals(lotteryList.getData().getPageNum())) {
                            callback.onLastPage(sPage);
                        }
                        Logger.d("page:" + sPage);

                    } else {
                        Logger.d("error:" + lotteryList.getMsg());
                        callback.loadLotteryListFailed(lotteryList.getMsg());
                    }
                }
            });
        }
    }
}
