package com.lishi.baijiaxing.pay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.view.TopNavigationBar;

public class PayResultActivity extends BaseActivity implements View.OnClickListener {
    private TopNavigationBar mTopNavigationBar;
    private Button btn_order, btn_close;
    private TextView tv_price, tv_pay_mode;
    private String price, pay_mode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_result);

        price = getIntent().getStringExtra("price");
        pay_mode = getIntent().getStringExtra("mode");
        init();
    }

    private void init() {
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
        btn_order.setOnClickListener(this);
        btn_close.setOnClickListener(this);

        tv_price.setText(String.valueOf(Double.valueOf(price)));
        tv_pay_mode.setText(pay_mode);
    }

    private void findId() {
        btn_order = (Button) findViewById(R.id.pay_result_order);
        btn_close = (Button) findViewById(R.id.pay_result_close);
        tv_price = (TextView) findViewById(R.id.pay_result_pay_price);
        tv_pay_mode = (TextView) findViewById(R.id.pay_result_pay_mode);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.navigation_pay_result);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pay_result_order:
                break;
            case R.id.pay_result_close:
                finish();
                break;
        }
    }
}
