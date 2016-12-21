package com.lishi.baijiaxing.search;

import com.lishi.baijiaxing.search.model.HotSearch;
import com.lishi.baijiaxing.search.model.SearchList;

/**
 * Created by Administrator on 2016/12/19.
 */

public interface SearchResultCallback {
    void searchCommoditySuccess(SearchList searchList);

    void searchCommodityFailed(String error);
}
