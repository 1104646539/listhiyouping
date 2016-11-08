package com.lishi.baijiaxing.utils;

import android.content.Context;
import android.content.res.Configuration;

public class SystemUtil {

    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public static String IMEI;
    public static float DENSITY;
    public static String OsInfo;

    /**
     * 退出程序
     */
    public static void exitApplication() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    /**
     * 判断是不是平板
     *
     * @param context
     * @return
     */
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}