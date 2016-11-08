package com.lishi.baijiaxing.retrievePassword.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.register.RegisterManger;
import com.lishi.baijiaxing.retrievePassword.presenter.RetrievePresenterImpl;
import com.lishi.baijiaxing.utils.CountDownUtil;
import com.lishi.baijiaxing.view.TopNavigationBar;

/**
 * 找回密码第2步，验证手机，填写验证码
 */
public class RetrieveActivity2 extends BaseActivity implements View.OnClickListener, RetrieveView {
    private TextView tv_testgetcode;
    private CountDownUtil mCoutDownUtil = new CountDownUtil();
    private TextView tv_retrieve2_next;
    private double phoneNumber = 0;
    private TopNavigationBar mTopNavigationBar;
    private boolean isSendCode = false;
    private EditText ed_code;
    private RetrievePresenterImpl mRetrievePresenter;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == CountDownUtil.CoutDown) {//正在倒计时
                long time = (long) msg.obj;
                tv_testgetcode.setText(time + "");
                tv_testgetcode.setEnabled(false);
                tv_testgetcode.setBackgroundColor(Color.rgb(230, 235, 235));
                tv_testgetcode.setTextColor(Color.rgb(153, 153, 153));
            } else if (msg.what == CountDownUtil.NotCoutDown) {//倒计时完毕
                long time = (long) msg.obj;
                tv_testgetcode.setText("重发");
                tv_testgetcode.setEnabled(true);
                tv_testgetcode.setBackgroundColor(Color.rgb(166, 99, 74));
                tv_testgetcode.setTextColor(Color.rgb(255, 255, 255));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve2);
        RegisterManger.getInstantion().addActivity(this);
        phoneNumber = getIntent().getDoubleExtra("number", 0);
        findId();
        initView();
    }

    private void initView() {
        mRetrievePresenter = new RetrievePresenterImpl(this);
        tv_testgetcode.setOnClickListener(this);
        tv_retrieve2_next.setOnClickListener(this);
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
        ed_code = (EditText) findViewById(R.id.edit_code_retrieve2);
        tv_testgetcode = (TextView) findViewById(R.id.tv_retrieve2_testgetcode);
        tv_retrieve2_next = (TextView) findViewById(R.id.tv_retrieve2_next);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.navigation_retrieve2);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_retrieve2_testgetcode://发送验证码
                if (phoneNumber != 0) {
                    mRetrievePresenter.sendCode(phoneNumber);
                }
                break;
            case R.id.tv_retrieve2_next://下一步
                if (ed_code.getText().toString().length() > 0 && isSendCode) {
                    double code = Double.valueOf(ed_code.getText().toString());
                    mRetrievePresenter.verificationCode(code);
                }
                break;
        }
    }

    /**
     * 开始倒计时
     */
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
        isSendCode = true;
        mCoutDownUtil.setHandler(mHandler);
        mCoutDownUtil.start();
    }

    @Override
    public void onSendCodeFailed() {
        isSendCode = false;
    }

    @Override
    public void onRetrieveSuccess() {

    }

    @Override
    public void onRetrieveFailed() {

    }

    @Override
    public void onVerificationCodeSuccess() {
        Intent startRetrieveActivity3 = new Intent(this, RetrieveActivity3.class);
        startRetrieveActivity3.putExtra("number", phoneNumber);
        startActivity(startRetrieveActivity3);
    }

    @Override
    public void onVerificationCodeFailed() {

    }
}
