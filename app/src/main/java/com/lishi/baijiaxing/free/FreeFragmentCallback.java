package com.lishi.baijiaxing.free;

import com.lishi.baijiaxing.base.BaseRequestCallBack;
import com.lishi.baijiaxing.free.model.FreeList;

/**
 * Created by Administrator on 2016/10/17.
 */
public interface FreeFragmentCallback extends BaseRequestCallBack {
    void loadFreeListSuccess(FreeList freeList);

    void loadFreeListFailed(String error);
}
