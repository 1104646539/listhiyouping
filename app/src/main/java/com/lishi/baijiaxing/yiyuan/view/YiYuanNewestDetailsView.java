package com.lishi.baijiaxing.yiyuan.view;

import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.yiyuan.model.YiYuanHotDetailsBean;
import com.lishi.baijiaxing.yiyuan.model.YiYuanNewestDetailsBean;

/**
 * Created by Administrator on 2016/10/24.
 */
public interface YiYuanNewestDetailsView extends BaseView {
    void loadDataNewestSuccess(YiYuanNewestDetailsBean bean);
}
