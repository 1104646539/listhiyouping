package com.lishi.baijiaxing.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/16.
 */

public class SharedPreferencesHelp {
    /**
     * 保存搜索历史
     */
    public static boolean saveHistory(Context context, List<String> datas) {
        SharedPreferences spf = context.getSharedPreferences("history", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        editor.putInt("historySize", datas.size());
        int hSize = datas.size();
        for (int i = 0; i < hSize; i++) {
            editor.remove("history" + i);
            editor.putString("history" + i, datas.get(i));
        }
        return editor.commit();
    }

    /**
     * 读取搜索历史
     */
    public static List<String> readHistory(Context context) {
        List<String> datas = new ArrayList<>();
        SharedPreferences spf = context.getSharedPreferences("history", Activity.MODE_PRIVATE);
        int hSize = spf.getInt("historySize", -1);
        for (int i = 0; i < hSize; i++) {
            String data = spf.getString("history" + i, "");
            datas.add(data);
        }
        return datas;
    }
}
