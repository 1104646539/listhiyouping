package com.lishi.baijiaxing.search.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.search.SearchCallback;
import com.lishi.baijiaxing.search.SearchResultCallback;
import com.lishi.baijiaxing.search.model.HotSearch;
import com.lishi.baijiaxing.search.model.SearchList;
import com.lishi.baijiaxing.search.model.SearchModelImpl;
import com.lishi.baijiaxing.search.model.SearchResultModelImpl;
import com.lishi.baijiaxing.search.view.SearchResultView;
import com.lishi.baijiaxing.search.view.SearchView;

/**
 * Created by Administrator on 2016/12/19.
 */

public class SearchResultPresenterImpl extends BasePresenter implements SearchResultPresenter, SearchResultCallback {
    private SearchResultView mSearchView;
    private SearchResultModelImpl mSearchModel;

    public SearchResultPresenterImpl(BaseView baseView) {
        super(baseView);
        mSearchModel = new SearchResultModelImpl();
        mSearchView = (SearchResultView) baseView;
    }

    @Override
    public void searchCommoditySuccess(SearchList searchList) {
        mSearchView.closeDialog();
        mSearchView.searchCommoditySuccess(searchList);
    }

    @Override
    public void searchCommodityFailed(String error) {
        mSearchView.closeDialog();
        mSearchView.searchCommodityFailed(error);
    }

    @Override
    public void searchCommodity(String name,int page) {
        mSearchModel.searchCommodity(this, name, page);
    }
}
