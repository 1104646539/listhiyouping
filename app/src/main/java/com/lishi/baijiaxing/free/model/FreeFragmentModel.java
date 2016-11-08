package com.lishi.baijiaxing.free.model;

import com.lishi.baijiaxing.free.FreeCallback;
import com.lishi.baijiaxing.free.FreeFragmentCallback;
import com.lishi.baijiaxing.free.view.FreeFragmentView;

/**
 * Created by Administrator on 2016/10/18.
 */
public interface FreeFragmentModel {
    void loadData(FreeFragmentCallback callback, int type);
}
