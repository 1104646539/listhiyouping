package com.lishi.baijiaxing.search.view;

import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.search.model.HotSearch;
import com.lishi.baijiaxing.search.model.SearchList;

/**
 * Created by Administrator on 2016/12/19.
 */

public interface SearchView extends BaseView {
  
    void getHotSearchSuccess(HotSearch search);

    void getHotSearchFailed(String error);
}
