package com.lishi.baijiaxing.free;

import com.lishi.baijiaxing.base.BaseRequestCallBack;
import com.lishi.baijiaxing.free.model.FreeAdList;
import com.lishi.baijiaxing.free.model.FreeBean;
import com.lishi.baijiaxing.free.model.FreeCommodityBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/17.
 */
public interface FreeCallback extends BaseRequestCallBack {
    void loadAdListSuccess(FreeAdList list);

    void loadAdListFailed(String error);
}
