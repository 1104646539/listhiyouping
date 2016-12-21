package com.lishi.baijiaxing.search.model;

import com.lishi.baijiaxing.search.SearchCallback;
import com.lishi.baijiaxing.search.SearchResultCallback;

/**
 * Created by Administrator on 2016/12/19.
 */

public interface SearchResultModel {
    void searchCommodity(SearchResultCallback callback, String name,int page);

}
