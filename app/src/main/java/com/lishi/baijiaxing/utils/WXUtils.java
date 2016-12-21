package com.lishi.baijiaxing.utils;

import android.content.Context;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * 微信工具类
 * Created by Administrator on 2016/11/7.
 */
public class WXUtils {
    /**
     * 微信APP_ID
     */
    public static final String WXAPP_ID = "wxaa99a1b35b04eb47";
    /**
     * secret_Id
     */
    public static final String APP_SECRET_ID = "508ef3e9145f049a49e8bf00db116321";

    public static String GET_REQUEST_ACCESS_TOKEN =
            "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    public static String GET_REQUEST_USER_INFO =
            "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    public static String GET_WXPAY_INFO = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}
