package com.lishi.baijiaxing.search.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.search.SearchCallback;
import com.lishi.baijiaxing.search.model.HotSearch;
import com.lishi.baijiaxing.search.model.SearchList;
import com.lishi.baijiaxing.search.model.SearchModelImpl;
import com.lishi.baijiaxing.search.view.SearchView;

/**
 * Created by Administrator on 2016/12/19.
 */

public class SearchPresenterImpl extends BasePresenter implements SearchPresenter, SearchCallback {
    private SearchView mSearchView;
    private SearchModelImpl mSearchModel;

    public SearchPresenterImpl(BaseView baseView) {
        super(baseView);
        mSearchModel = new SearchModelImpl();
        mSearchView = (SearchView) baseView;
    }

    @Override
    public void getHotSearchSuccess(HotSearch search) {
        mSearchView.getHotSearchSuccess(search);
    }

    @Override
    public void getHotSearchFailed(String error) {
        mSearchView.getHotSearchFailed(error);
    }

    @Override
    public void getHotSearchCommodity() {
        mSearchView.showDialog();
        mSearchModel.getHotSearchCommodity(this);
    }
}
