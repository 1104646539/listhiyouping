package com.lishi.baijiaxing.yiyuan;

import com.lishi.baijiaxing.base.BaseRequestCallBack;
import com.lishi.baijiaxing.yiyuan.model.HotList;
import com.lishi.baijiaxing.yiyuan.model.LotteryList;
import com.lishi.baijiaxing.yiyuan.model.YiYuanHotBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/24.
 */
public interface YiYuanHotCallback extends BaseRequestCallBack {
    void loadHotListSuccess(HotList hotList);

    void loadHotListFailed(String error);
}
