package com.lishi.baijiaxing.free;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.view.TopNavigationBar;

public class FreeResultActivity extends BaseActivity {
    private String mType;
    private TopNavigationBar mTopNavigationBar;
    private TextView result1, result2;
    private TextView close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_result);

        mType = getIntent().getStringExtra("type");
        init();
    }

    private void init() {
        findId();
        initView();
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

    private void initView() {
        if (mType.equals("free")) {
            result1.setText("免费领申请提交成功");
            result2.setText("请在活动结束后进入个人中心查询中奖名单");
        } else if (mType.equals("yiyuan")) {
            result1.setText("恭喜您成功参加“一元拼”活动");
            result2.setText("幸运女神正在向您靠近，活动结束后， 请关注个人中心，查看是否中奖！");
        }
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void findId() {
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.topbar_free_result);
        result1 = (TextView) findViewById(R.id.free_result1);
        result2 = (TextView) findViewById(R.id.free_result2);
        close = (TextView) findViewById(R.id.free_result_close);

    }
}
