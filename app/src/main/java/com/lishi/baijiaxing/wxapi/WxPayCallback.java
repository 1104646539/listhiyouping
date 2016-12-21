package com.lishi.baijiaxing.wxapi;

import com.lishi.baijiaxing.pay.model.WxPrepay;

/**
 * Created by Administrator on 2016/12/14.
 */

public interface WxPayCallback {
    void getPrepayInfoSuccess(WxPrepay prepay);

    void getPrepayInfoFailed(String error);
}
