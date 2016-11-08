package com.lishi.baijiaxing.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * toast 统一管理类
 *
 * @author ouyangbin
 */
public class T {
    private static Toast t;

    private T() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }


    /**
     * 短时间显示toast
     *
     * @param c
     * @param s
     */
    public static void ShowToastForShort(Context c, String s) {
        if (t == null) {
            t = Toast.makeText(c, s, Toast.LENGTH_SHORT);
        } else {
            t.setText(s);
            t.setDuration(Toast.LENGTH_SHORT);
        }
        t.show();
    }

    /**
     * 长时间显示toast
     *
     * @param c
     * @param s
     */
    public static void ShowToastForLong(Context c, String s) {
        if (t == null) {
            t = Toast.makeText(c, s, Toast.LENGTH_LONG);
        } else {
            t.setText(s);
            t.setDuration(Toast.LENGTH_LONG);
        }
        t.show();
    }

    /**
     * 自定义时间显示toast
     *
     * @param c
     * @param s
     * @param time
     */
    public static void ShowToast(Context c, String s, int time) {
        if (t == null) {
            t = Toast.makeText(c, s, time);
        } else {
            t.setText(s);
            t.setDuration(time);
        }
        t.show();
    }
}
