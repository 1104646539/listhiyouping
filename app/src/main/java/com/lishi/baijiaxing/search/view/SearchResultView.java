package com.lishi.baijiaxing.search.view;

import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.search.model.SearchList;

/**
 * Created by Administrator on 2016/12/19.
 */

public interface SearchResultView extends BaseView{
    void searchCommoditySuccess(SearchList searchList);

    void searchCommodityFailed(String error);

}
