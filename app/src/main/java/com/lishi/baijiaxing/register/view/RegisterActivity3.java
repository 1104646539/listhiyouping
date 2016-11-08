package com.lishi.baijiaxing.register.view;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.bean.UserBean;
import com.lishi.baijiaxing.register.RegisterManger;
import com.lishi.baijiaxing.register.presenter.RegisterPresenterImpl;
import com.lishi.baijiaxing.view.TopNavigationBar;

/**
 * 注册第3步，填写密码
 */
public class RegisterActivity3 extends BaseActivity implements CompoundButton.OnCheckedChangeListener, RegisterView, View.OnClickListener {
    private EditText edit_password;
    private CheckBox isVisibility;
    private TextView tv_next3;
    private TopNavigationBar mTopNavigationBar;
    private RegisterPresenterImpl mRegisterPresenter;
    private UserBean mUserBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);
        RegisterManger.getInstantion().addActivity(this);
        mUserBean = getIntent().getParcelableExtra("user");
        findId();
        initView();
    }

    private void initView() {
        mRegisterPresenter = new RegisterPresenterImpl(this);
        isVisibility.setOnCheckedChangeListener(this);
        mTopNavigationBar.setOnTopClick(new TopNavigationBar.OnTopClick() {
            @Override
            public void onTopLeftClick(View view) {
                finish();
            }

            @Override
            public void onTopRightClick(View view) {

            }
        });
        tv_next3.setOnClickListener(this);
    }

    private void findId() {
        edit_password = (EditText) findViewById(R.id.edit_password_regisier3);
        isVisibility = (CheckBox) findViewById(R.id.checkbox_register3_isvisibility);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.navigation_register3);
        tv_next3 = (TextView) findViewById(R.id.tv_register3_next);
    }

    /**
     * 密码的可见性
     *
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {//设置加密
            Log.i("as", "____________" + isChecked);
            edit_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {//设置不加密
            Log.i("as", "____________" + isChecked);
            edit_password.setTransformationMethod(PasswordTransformationMethod
                    .getInstance());
        }
        String text = edit_password.getText().toString();
        edit_password.setSelection(text.length());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register3_next:
                if (mUserBean != null) {
                    mUserBean.setUserPassWord(edit_password.getText().toString());
                    mRegisterPresenter.registerUser(mUserBean);
                }
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

    }

    @Override
    public void onSendCodeFailed() {

    }

    @Override
    public void registerUserSuccess() {
        RegisterManger.getInstantion().closeAll();
    }

    @Override
    public void registerUserFailed() {

    }

    @Override
    public void onVerificationCodeSuccess() {

    }

    @Override
    public void onVerificationCodeFailed() {

    }

}
