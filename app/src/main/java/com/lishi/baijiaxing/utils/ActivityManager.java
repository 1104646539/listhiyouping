package com.lishi.baijiaxing.utils;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Activity管理类
 * Created by Administrator on 2016/10/8.
 */
public class ActivityManager {
    ArrayList<Activity> mActivityArrayList;

    public synchronized static ActivityManager newInstance() {
        return new ActivityManager();
    }

    public void addActivity(Activity activity) {
        if (activity != null) {
            mActivityArrayList.add(activity);
        }
    }

    public void removeActivity(Activity activity) {
        if (activity != null) {
            mActivityArrayList.remove(activity);
        }
    }

    private ActivityManager() {
        mActivityArrayList = new ArrayList<>();
    }

    public ArrayList<Activity> getList() {
        return mActivityArrayList;
    }

}
