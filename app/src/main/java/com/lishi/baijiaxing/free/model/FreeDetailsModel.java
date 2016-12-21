package com.lishi.baijiaxing.free.model;

import com.lishi.baijiaxing.free.FreeDetailsCallback;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrder;

/**
 * Created by Administrator on 2016/10/21.
 */
public interface FreeDetailsModel {
    void loadData(FreeDetailsCallback callback, String type, String gid, String zid);
    void submitApply(FreeDetailsCallback callback, FreeDetails freeDetails,String aid,String type);
}
