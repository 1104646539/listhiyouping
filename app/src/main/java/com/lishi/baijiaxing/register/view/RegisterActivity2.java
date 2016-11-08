package com.lishi.baijiaxing.register.view;

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
import com.lishi.baijiaxing.bean.UserBean;
import com.lishi.baijiaxing.register.RegisterManger;
import com.lishi.baijiaxing.register.presenter.RegisterPresenterImpl;
import com.lishi.baijiaxing.utils.CountDownUtil;
import com.lishi.baijiaxing.view.TopNavigationBar;

/**
 * 注册第1步，填写验证码
 */
public class RegisterActivity2 extends BaseActivity implements View.OnClickListener, RegisterView {
    private TextView tv_register2_next;
    private TextView tv_register2_send;
    private EditText ed_sendCode;
    private CountDownUtil countDownUtil = new CountDownUtil();
    private TopNavigationBar mTopNavigationBar;
    private RegisterPresenterImpl mRegisterPresenter;
    private double phoneNumber = 0;
    private boolean isSendCode = false;//是否发送了验证码
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == CountDownUtil.CoutDown) {//正在倒计时
                long time = (long) msg.obj;
                tv_register2_send.setText(time + "");
                tv_register2_send.setEnabled(false);
                tv_register2_send.setBackgroundColor(Color.rgb(230, 235, 235));
                tv_register2_send.setTextColor(Color.rgb(153, 153, 153));
            } else if (msg.what == CountDownUtil.NotCoutDown) {//倒计时完毕
                long time = (long) msg.obj;
                tv_register2_send.setText("重发");
                tv_register2_send.setEnabled(true);
                tv_register2_send.setBackgroundColor(Color.rgb(166, 99, 74));
                tv_register2_send.setTextColor(Color.rgb(255, 255, 255));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        RegisterManger.getInstantion().addActivity(this);
        phoneNumber = getIntent().getDoubleExtra("number", 0);
        findId();
        initView();
    }

    private void initView() {
        mRegisterPresenter = new RegisterPresenterImpl(this);
        tv_register2_next.setOnClickListener(this);
        tv_register2_send.setOnClickListener(this);
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
        ed_sendCode = (EditText) findViewById(R.id.edit_code_register2);
        tv_register2_next = (TextView) findViewById(R.id.tv_register2_next);
        tv_register2_send = (TextView) findViewById(R.id.tv_register2_send);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.navigation_register2);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register2_next://下一步
                if (phoneNumber != 0 && isSendCode) {
                    mRegisterPresenter.verificationCode(Double.valueOf(ed_sendCode.getText().toString()));
                }
                break;
            case R.id.tv_register2_send://发送验证码
                mRegisterPresenter.sendCode(phoneNumber);
                break;
        }
    }

    @Override
    public void registerUser() {

    }

    @Override
    public void sendCode() {

    }

    @Override
    public void verificationCode() {

    }

    @Override
    public void onSendCodeSuccess() {
        isSendCode = true;
        countDownUtil.setHandler(mHandler);
        countDownUtil.start();
    }

    @Override
    public void onSendCodeFailed() {

    }

    @Override
    public void registerUserSuccess() {

    }

    @Override
    public void registerUserFailed() {

    }

    @Override
    public void onVerificationCodeSuccess() {
        Intent startRegisterActivity2 = new Intent(this, RegisterActivity3.class);
        UserBean userBean = new UserBean();
        userBean.setPhoneNumber(phoneNumber);
        startRegisterActivity2.putExtra("user", userBean);
        startActivity(startRegisterActivity2);
    }

    @Override
    public void onVerificationCodeFailed() {
        isSendCode = false;
    }
}
