package com.lishi.baijiaxing.yiyuan.view;

import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.yiyuan.model.HotList;
import com.lishi.baijiaxing.yiyuan.model.YiYuanHotBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/24.
 */
public interface YiYuanHotView extends BaseView {
    void loadHotListSuccess(HotList hotList);

    void loadHotListFailed(String error);
}
