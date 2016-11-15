package com.lishi.baijiaxing.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * 打电话
 * Created by Administrator on 2016/11/15.
 */

public class CallPhone {
    public final static String SERVICE = "18124189687";
    public static void dialPhoneNumber(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }
}
