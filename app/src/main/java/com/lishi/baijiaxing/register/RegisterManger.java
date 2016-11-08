package com.lishi.baijiaxing.register;

import android.app.Activity;

import java.util.ArrayList;

/**
 * 注册完毕时关闭3个Activity
 * Created by Administrator on 2016/8/22.
 */
public class RegisterManger {
    private static RegisterManger mRegisterManger;
    private ArrayList<Activity> mActivities;

    public static RegisterManger getInstantion() {
        if (mRegisterManger == null) {
            mRegisterManger = new RegisterManger();
        }
        return mRegisterManger;
    }

    public void addActivity(Activity activity) {
        if (mActivities == null) {
            mActivities = new ArrayList<>();
        }
        mActivities.add(activity);
    }

    public synchronized void closeAll() {
        for (Activity a : mActivities) {
            a.finish();
        }
        mActivities.clear();
    }
}
