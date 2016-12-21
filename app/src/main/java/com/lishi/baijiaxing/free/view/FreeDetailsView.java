package com.lishi.baijiaxing.free.view;

import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.free.model.FreeDetails;
import com.lishi.baijiaxing.free.model.FreeDetailsBean;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrder;

/**
 * Created by Administrator on 2016/10/21.
 */
public interface FreeDetailsView extends BaseView {
    void loadDetailsSuccess(FreeDetails freeDetails);

    void loadDetailsFailed(String error);

    void submitApplySuccess(SubmitOrder submitOrder);

    void submitApplyFailed(String error);
}
