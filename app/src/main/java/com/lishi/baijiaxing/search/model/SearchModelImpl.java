package com.lishi.baijiaxing.search.model;

import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.search.SearchCallback;
import com.lishi.baijiaxing.search.network.SearchService;
import com.lishi.baijiaxing.utils.Status;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/19.
 */

public class SearchModelImpl extends BaseModel implements SearchModel {
    private SearchService mSearchService;

    public SearchModelImpl() {
        mSearchService = (SearchService) getRetrofitManager().getHomeService(SearchService.class);
    }

    @Override
    public void getHotSearchCommodity(final SearchCallback callback) {
        mSearchService.getHotSearchCommodity().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HotSearch>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.getHotSearchFailed(e.toString());
                    }

                    @Override
                    public void onNext(HotSearch search) {
                        if (search.getStatus().equals(Status.STATUS_GETUSERINFO_SUCCESS)) {
                            callback.getHotSearchSuccess(search);
                        }else{
                            callback.getHotSearchFailed(search.getMsg());
                        }
                    }
                });
    }
}
