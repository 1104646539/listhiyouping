package com.lishi.baijiaxing.utils;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;

/**
 * 验证码倒计时
 * Created by Administrator on 2016/7/27.
 */
public class CountDownUtil {
    private Handler mHandler;
    public static int CoutDown = 1;
    public static int NotCoutDown = 0;

    public CountDownUtil() {
    }

    public Handler getHandler() {
        return mHandler;
    }

    public void setHandler(Handler handler) {
        mHandler = handler;
    }


    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long time = 60;
                while (true) {
                    try {
                        Thread.sleep(1000);
                        time--;
                        if (time > 0) {
                            if (mHandler != null) {
                                mHandler.sendMessage(Message.obtain(mHandler, CoutDown, time));
                            }
                        } else if (time == 0) {
                            if (mHandler != null) {
                                mHandler.sendMessage(Message.obtain(mHandler, NotCoutDown, time));
                                return;
                            }

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }

}
