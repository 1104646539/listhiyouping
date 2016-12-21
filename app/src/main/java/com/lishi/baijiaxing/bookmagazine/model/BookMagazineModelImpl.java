package com.lishi.baijiaxing.bookmagazine.model;

import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.bookmagazine.BookMagazineCallback;
import com.lishi.baijiaxing.bookmagazine.network.BookMagazineService;
import com.lishi.baijiaxing.hot.HotCommodityCallback;
import com.lishi.baijiaxing.hot.model.HotCommodity;
import com.lishi.baijiaxing.hot.network.HotCommodityService;
import com.lishi.baijiaxing.utils.Status;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/8.
 */

public class BookMagazineModelImpl extends BaseModel implements BookMagazineModel {
    private BookMagazineService mBookMagazineService;

    public BookMagazineModelImpl() {
        mBookMagazineService = (BookMagazineService) getRetrofitManager().getHomeService(BookMagazineService.class);
    }

    @Override
    public void loadData(final BookMagazineCallback callback) {
        mBookMagazineService.getBookMagazineList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
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
