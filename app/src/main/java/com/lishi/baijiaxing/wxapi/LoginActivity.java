package com.lishi.baijiaxing.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.activity.MainActivity;
import com.lishi.baijiaxing.register.RegisterManger;
import com.lishi.baijiaxing.register.view.RegisterActivity1;
import com.lishi.baijiaxing.retrievePassword.view.RetrieveActivity1;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.utils.LocalUserInfo;
import com.lishi.baijiaxing.wxapi.model.WXTokenBean;
import com.lishi.baijiaxing.wxapi.model.WXUserInfo;
import com.lishi.baijiaxing.wxapi.view.SignInView;
import com.lishi.baijiaxing.utils.WXUtils;
import com.lishi.baijiaxing.view.TopNavigationBar;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, SignInView {
    private TextView tv_login_register;//注册
    private TextView tv_login_retrievepassword;
    private TopNavigationBar mTopNavigationBar;
    private ImageView login_wxChar, login_qq;

    /**
     * 是否已经登录
     */
    private boolean isEntrySuccess = false;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        RegisterManger.getInstantion().addActivity(this);
        findId();
        initView();
    }

    private void initView() {
        tv_login_register.setOnClickListener(this);
        tv_login_retrievepassword.setOnClickListener(this);
        mTopNavigationBar.setOnTopClick(new TopNavigationBar.OnTopClick() {
            @Override
            public void onTopLeftClick(View view) {
                finish();
            }

            @Override
            public void onTopRightClick(View view) {

            }
        });

        login_wxChar.setOnClickListener(this);
        login_qq.setOnClickListener(this);
    }

    private void findId() {
        tv_login_register = (TextView) findViewById(R.id.tv_login_register);
        tv_login_retrievepassword = (TextView) findViewById(R.id.tv_login_retrievepassword);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.navigation_login);
        login_wxChar = (ImageView) findViewById(R.id.login_wxChar);
        login_qq = (ImageView) findViewById(R.id.login_qq);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login_register://注册
                Intent startRegisterActivity = new Intent(this, RegisterActivity1.class);
                startActivity(startRegisterActivity);
                break;
            case R.id.tv_login_retrievepassword://找回密码
                Intent startRetrieveActivity = new Intent(this, RetrieveActivity1.class);
                startActivity(startRetrieveActivity);
                break;
            case R.id.login_wxChar:
                Intent startWxActivity = new Intent(this, WXEntryActivity.class);
                startActivityForResult(startWxActivity, 0);
                break;
            case R.id.login_qq:
                break;
        }
    }


    @Override
    public void onSignIn() {

    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignInFailed() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("onResume", "onResume ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("onPause", "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("onRestart", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("onDestroy", "onDestroy");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data.getStringExtra("result").equals("success")) {
                Intent success = new Intent();
                success.putExtra("result", "success");
                setResult(RESULT_OK, success);
                finish();
            }
        }
    }
}
