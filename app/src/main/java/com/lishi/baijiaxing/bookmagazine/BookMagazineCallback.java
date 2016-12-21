package com.lishi.baijiaxing.bookmagazine;

import com.lishi.baijiaxing.base.BaseRequestCallBack;
import com.lishi.baijiaxing.hot.model.HotCommodity;

/**
 * Created by Administrator on 2016/12/8.
 */

public interface BookMagazineCallback extends BaseRequestCallBack {
    void loadDataSuccess(HotCommodity commodity);

    void loadDataFailed(String error);
}
