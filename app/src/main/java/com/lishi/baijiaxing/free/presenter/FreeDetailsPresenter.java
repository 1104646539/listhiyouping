package com.lishi.baijiaxing.free.presenter;

import com.lishi.baijiaxing.free.model.FreeDetails;

/**
 * Created by Administrator on 2016/10/21.
 */
public interface FreeDetailsPresenter {
    void loadData(String type, String gid, String zid);

    void submitApply(FreeDetails freeDetails,String aid,String type);
}
