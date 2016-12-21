package com.lishi.baijiaxing.bookmagazine.view;

import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.hot.model.HotCommodity;

/**
 * Created by Administrator on 2016/12/8.
 */

public interface BookMagazineView extends BaseView{
    void loadData();
    void loadDataSuccess(HotCommodity commodity);

    void loadDataFailed(String error);
}
