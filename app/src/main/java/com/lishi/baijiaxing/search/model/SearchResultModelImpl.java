package com.lishi.baijiaxing.search.model;

import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.search.SearchCallback;
import com.lishi.baijiaxing.search.SearchResultCallback;
import com.lishi.baijiaxing.search.network.SearchService;
import com.lishi.baijiaxing.utils.Status;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/19.
 */

public class SearchResultModelImpl extends BaseModel implements SearchResultModel {
    private SearchService mSearchService;
    private int mPage;
    private boolean isFirst;

    public SearchResultModelImpl() {
        mSearchService = (SearchService) getRetrofitManager().getHomeService(SearchService.class);
    }


    @Override
    public void searchCommodity(final SearchResultCallback callback, String name, final int page) {
        String sPage;

        if (page == -1) {
            sPage = mPage + 1 + "";//上拉加载
        } else if (page == 0) {
            sPage = mPage + "";//刷新(延续上次的页数)
        } else {
            sPage = "1";////第一次加载，刷新
            isFirst = true;
        }

        mSearchService.searchCommodity(name, sPage).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.searchCommodityFailed(e.toString());
                    }

                    @Override
                    public void onNext(SearchList searchList) {
                        if (searchList.getStatus().equals(Status.STATUS_GETUSERINFO_SUCCESS)) {
                            callback.searchCommoditySuccess(searchList);

                            if (page == -1 || isFirst) {
                                mPage = mPage + 1;
                                isFirst = false;
                            }
                        } else {
                            callback.searchCommodityFailed(searchList.getMsg());
                        }
                    }
                });
    }
}
