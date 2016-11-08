package com.lishi.baijiaxing.retrievePassword.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.register.RegisterManger;
import com.lishi.baijiaxing.view.TopNavigationBar;

/**
 * 找回密码第1步，输入要找回密码的帐号
 */
public class RetrieveActivity1 extends BaseActivity implements View.OnClickListener {
    private TextView tv_next;
    private TextView tv_retrieve1_next;
    private TopNavigationBar mTopNavigationBar;
    private EditText edit_code_retrieve1;
    private double phoneNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve1);
        RegisterManger.getInstantion().addActivity(this);
        findId();
        initView();
    }

    private void initView() {
        tv_next.setOnClickListener(this);
        tv_retrieve1_next.setOnClickListener(this);
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
        tv_next = (TextView) findViewById(R.id.tv_retrieve1_next);
        tv_retrieve1_next = (TextView) findViewById(R.id.tv_retrieve1_next);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.navigation_retrieve1);
        edit_code_retrieve1 = (EditText) findViewById(R.id.edit_code_retrieve1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_retrieve1_next:
                phoneNumber = Double.valueOf(edit_code_retrieve1.getText().toString());
                if (phoneNumber != 0) {
                    Intent startRetrieveActivity2 = new Intent(this, RetrieveActivity2.class);
                    startRetrieveActivity2.putExtra("number", phoneNumber);
                    startActivity(startRetrieveActivity2);
                }
                break;
        }
    }
}
