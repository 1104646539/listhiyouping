package com.lishi.baijiaxing.wxapi.view;

import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.pay.model.WxPrepay;

/**
 * Created by Administrator on 2016/12/14.
 */

public interface WxPayView extends BaseView {
    void getPrepayInfoSuccess(WxPrepay prepay);

    void getPrepayInfoFailed(String error);
}
