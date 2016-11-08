
package com.lishi.baijiaxing.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lishi.baijiaxing.utils.ActivityManager;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        InitView();
        super.onCreate(savedInstanceState);
    }

    private void InitView() {
        // 取消标题
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ActivityManager.newInstance().addActivity(this);

        //状态栏透明
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            View decorView = getWindow().getDecorView();
//            int option = View.SYSTEM_UI_FLAG_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WindowManager.LayoutParams wmLayoutParams = getWindow().getAttributes();
//            wmLayoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                    | wmLayoutParams.flags;
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //强制竖屏
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.newInstance().removeActivity(this);
    }
}
