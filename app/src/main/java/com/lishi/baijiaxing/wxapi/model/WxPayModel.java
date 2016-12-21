package com.lishi.baijiaxing.wxapi.model;

import com.lishi.baijiaxing.wxapi.WxPayCallback;

/**
 * Created by Administrator on 2016/12/14.
 */

public interface WxPayModel {
    void getPrepayInfo(WxPayCallback callback, String orderNumber);
}
