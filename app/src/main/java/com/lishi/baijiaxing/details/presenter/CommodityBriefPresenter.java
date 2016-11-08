package com.lishi.baijiaxing.details.presenter;

import com.lishi.baijiaxing.details.view.CommodityBriefView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/1.
 */
public interface CommodityBriefPresenter {
    void loadData();

    void loadDataSuccess(ArrayList<Integer> integers);

    void loadDataFailed(String error);
}
