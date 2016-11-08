package com.lishi.baijiaxing.yiyuan;

import com.lishi.baijiaxing.base.BaseRequestCallBack;
import com.lishi.baijiaxing.yiyuan.model.YiYuanHotDetailsBean;

/**
 * Created by Administrator on 2016/10/24.
 */
public interface YiYuanHotDetailsCallback extends BaseRequestCallBack {
    void loadDataHotDetailsSuccess(YiYuanHotDetailsBean bean);
}
