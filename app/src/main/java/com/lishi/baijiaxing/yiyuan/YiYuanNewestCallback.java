package com.lishi.baijiaxing.yiyuan;

import com.lishi.baijiaxing.base.BaseRequestCallBack;
import com.lishi.baijiaxing.yiyuan.model.LotteryList;
import com.lishi.baijiaxing.yiyuan.model.YiYuanHotBean;
import com.lishi.baijiaxing.yiyuan.model.YiYuanNewestBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/24.
 */
public interface YiYuanNewestCallback extends BaseRequestCallBack {
    void loadLotteryListSuccess(LotteryList lotteryList);

    void loadLotteryListFailed(String error);

    void onLastPage(String status);
}
