package com.lishi.baijiaxing.submitOrder;

import com.lishi.baijiaxing.base.BaseRequestCallBack;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrder;
import com.lishi.baijiaxing.submitOrder.model.WriteOrder;

/**
 * Created by Administrator on 2016/12/9.
 */

public interface SubmitOrderCallback extends BaseRequestCallBack {
    void onLoadOrderDataSuccess(WriteOrder writeOrder);

    void onLoadOrderDataFailed(String error);

    void onSubmitOrderSuccess(SubmitOrder submitOrder);

    void onSubmitOrderFailed(String error);
}
