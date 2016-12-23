package com.lishi.baijiaxing.yiyuan.view;

import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.yiyuan.model.HotList;
import com.lishi.baijiaxing.yiyuan.model.LotteryList;
import com.lishi.baijiaxing.yiyuan.model.YiYuanHotBean;
import com.lishi.baijiaxing.yiyuan.model.YiYuanNewestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/24.
 */
public interface YiYuanNewestView extends BaseView {
    void loadLotteryListSuccess(List<LotteryList.DataBean.CommodityListBean> lotteryList);

    void loadLotteryListFailed(String error);

    void onLastPage(String status);
}
