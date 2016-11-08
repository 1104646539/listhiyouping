package com.lishi.baijiaxing.retrievePassword.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.register.RegisterManger;
import com.lishi.baijiaxing.retrievePassword.presenter.RetrievePresenterImpl;
import com.lishi.baijiaxing.view.TopNavigationBar;

/**
 * 找回密码第3步，重置登录密码
 */
public class RetrieveActivity3 extends BaseActivity implements RetrieveView, View.OnClickListener {
    private TopNavigationBar mTopNavigationBar;
    private TextView tv_retrieve3_next;
    private double phoneNumber = 0;
    private RetrievePresenterImpl mRetrievePresenter;
    private EditText edit_code_retrieve3_one;
    private EditText edit_code_retrieve3_two;
    private String password_one = "";
    private String password_two = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve3);
        RegisterManger.getInstantion().addActivity(this);
        phoneNumber = getIntent().getDoubleExtra("number", 0);
        findId();
        initView();
    }

    private void initView() {
        mRetrievePresenter = new RetrievePresenterImpl(this);
        mTopNavigationBar.setOnTopClick(new TopNavigationBar.OnTopClick() {
            @Override
            public void onTopLeftClick(View view) {
                finish();
            }

            @Override
            public void onTopRightClick(View view) {

            }
        });
        tv_retrieve3_next.setOnClickListener(this);
    }

    private void findId() {
        edit_code_retrieve3_one = (EditText) findViewById(R.id.edit_code_retrieve3_one);
        edit_code_retrieve3_two = (EditText) findViewById(R.id.edit_code_retrieve3_two);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.navigation_retrieve3);
        tv_retrieve3_next = (TextView) findViewById(R.id.tv_retrieve3_next);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_retrieve3_next:
                password_one = edit_code_retrieve3_one.getText().toString();
                password_two = edit_code_retrieve3_two.getText().toString();
                if (password_one != "" && password_two.equals(password_one)) {
                    mRetrievePresenter.retrieve(phoneNumber, password_one);
                }
                break;
        }
    }

    @Override
    public void sendCode() {

    }

    @Override
    public void retrieve() {

    }

    @Override
    public void verificationCode() {

    }

    @Override
    public void onSendCodeSuccess() {

    }

    @Override
    public void onSendCodeFailed() {

    }

    @Override
    public void onRetrieveSuccess() {
        RegisterManger.getInstantion().closeAll();
    }

    @Override
    public void onRetrieveFailed() {

    }

    @Override
    public void onVerificationCodeSuccess() {

    }

    @Override
    public void onVerificationCodeFailed() {

    }


}
