package com.lishi.baijiaxing.search;

import com.lishi.baijiaxing.search.model.HotSearch;
import com.lishi.baijiaxing.search.model.SearchList;

/**
 * Created by Administrator on 2016/12/19.
 */

public interface SearchCallback {
    void getHotSearchSuccess(HotSearch search);

    void getHotSearchFailed(String error);
}
