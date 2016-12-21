package com.lishi.baijiaxing.hot;

import com.lishi.baijiaxing.base.BaseRequestCallBack;
import com.lishi.baijiaxing.hot.model.HotCommodity;

/**
 * Created by Administrator on 2016/12/8.
 */

public interface HotCommodityCallback extends BaseRequestCallBack {
    void loadDataSuccess(HotCommodity commodity);

    void loadDataFailed(String error);
}
