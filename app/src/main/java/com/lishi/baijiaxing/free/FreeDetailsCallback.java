package com.lishi.baijiaxing.free;

import com.lishi.baijiaxing.base.BaseRequestCallBack;
import com.lishi.baijiaxing.free.model.FreeDetails;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrder;

/**
 * Created by Administrator on 2016/10/21.
 */
public interface FreeDetailsCallback extends BaseRequestCallBack {
    void loadDetailsSuccess(FreeDetails freeDetails);

    void loadDetailsFailed(String error);

    void submitApplySuccess(SubmitOrder submitOrder);

    void submitApplyFailed(String error);
}
