package com.lishi.baijiaxing.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.view.TopNavigationBar;

/**
 * 礼品卡
 */
public class GiftCardActivity extends BaseActivity {
    private TopNavigationBar mTopNavigationBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_gift_card);

        findId();
        initView();
    }

    private void initView() {
        mTopNavigationBar.setOnTopClick(new TopNavigationBar.OnTopClick() {
            @Override
            public void onTopLeftClick(View view) {
                finish();
            }

            @Override
            public void onTopRightClick(View view) {

            }
        });
    }

    private void findId() {
        mTopNavigationBar= (TopNavigationBar) findViewById(R.id.navigation_gificard);
    }
}
