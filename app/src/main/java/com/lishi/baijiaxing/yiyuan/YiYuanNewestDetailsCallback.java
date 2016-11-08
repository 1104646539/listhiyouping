package com.lishi.baijiaxing.yiyuan;

import com.lishi.baijiaxing.base.BaseRequestCallBack;
import com.lishi.baijiaxing.yiyuan.model.YiYuanHotDetailsBean;
import com.lishi.baijiaxing.yiyuan.model.YiYuanNewestDetailsBean;

/**
 * Created by Administrator on 2016/10/24.
 */
public interface YiYuanNewestDetailsCallback extends BaseRequestCallBack {
    void loadDataNewestDetailsSuccess(YiYuanNewestDetailsBean bean);
}
