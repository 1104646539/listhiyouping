package com.lishi.baijiaxing.free.view;

import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.free.model.FreeList;

/**
 * Created by Administrator on 2016/10/17.
 */
public interface FreeFragmentView extends BaseView {
    void loadFreeListSuccess(FreeList freeList);

    void loadFreeListFailed(String error);
}
