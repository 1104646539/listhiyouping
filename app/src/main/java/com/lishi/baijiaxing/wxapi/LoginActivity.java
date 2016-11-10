package com.lishi.baijiaxing.wxapi;

import android.content.Context;
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
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.register.RegisterManger;
import com.lishi.baijiaxing.register.view.RegisterActivity1;
import com.lishi.baijiaxing.retrievePassword.view.RetrieveActivity1;
import com.lishi.baijiaxing.utils.LocalUserInfo;
import com.lishi.baijiaxing.view.TopNavigationBar;
import com.lishi.baijiaxing.wxapi.model.QQTokenBean;
import com.lishi.baijiaxing.wxapi.model.QQUserInfo;
import com.lishi.baijiaxing.wxapi.view.SignInView;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, SignInView {
    private TextView tv_login_register;//注册
    private TextView tv_login_retrievepassword;
    private TopNavigationBar mTopNavigationBar;
    private ImageView login_wxChar, login_qq;
    private BaseUiListener mBaseUiListener;
    private BaseUserListener mBaseUserListener;
    private UserInfo userinfo;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 2) {
                Intent success = new Intent();
                success.putExtra("result", "qq");
                setResult(RESULT_OK, success);
                Log.i("handleMessage", "qq登录成功");
            }
            finish();
        }
    };
    private String scope = "all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (MainActivity.mTencent != null) {
            if (MainActivity.mTencent.isSessionValid()) {
                MainActivity.mTencent.logout(this);
            }
        }
        MainActivity.mTencent = Tencent.createInstance("1105736969", getApplicationContext());

        RegisterManger.getInstantion().addActivity(this);
        findId();
        initView();
        mBaseUiListener = new BaseUiListener();
    }

    /**
     * qq回调
     */
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object o) {
            getQQUserInfo(o.toString());
            Log.i("BaseUiListener", "调用了");
        }

        @Override
        public void onError(UiError uiError) {
            Log.i("BaseUiListener", "onError");
        }

        @Override
        public void onCancel() {
            Log.i("BaseUiListener", "onCancel");
        }
    }

    /**
     * 获取qq用户信息
     *
     * @param str
     */
    private void getQQUserInfo(String str) {
        Gson gson = new Gson();
        QQTokenBean qqTokenBean = gson.fromJson(str, QQTokenBean.class);
        Log.i("getQQUserInfo", "qqTokenBean=" + qqTokenBean.toString());

        mBaseUserListener = new BaseUserListener();
        UserInfo userInfo = new UserInfo(this, MainActivity.mTencent.getQQToken());
        userInfo.getUserInfo(mBaseUserListener);
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
                qqLogin();
                break;
        }
    }

    /**
     * qq登录
     */
    private void qqLogin() {
        if (!MainActivity.mTencent.isSessionValid()) {
            MainActivity.mTencent.login(this, scope, mBaseUiListener);
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
            if (data != null) {
                if (data.getStringExtra("result") != null && data.getStringExtra("result").equals("success")) {
                    Intent success = new Intent();
                    success.putExtra("result", "wx");
                    setResult(RESULT_OK, success);
                    finish();
                }
            }
        } else if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.RESULT_LOGIN) {
                Tencent.handleResultData(data, mBaseUiListener);
            }
        }
    }

    /**
     * 获取用户数据成功/失败
     */
    private class BaseUserListener implements IUiListener {
        @Override
        public void onComplete(Object o) {
            Log.i("BaseApiListener", "success=" + o.toString());
            Gson gson = new Gson();
            QQUserInfo qqUserInfo = gson.fromJson(o.toString(), QQUserInfo.class);
            LocalUserInfo.getInstance().setNickName(qqUserInfo.getNickname());
            LocalUserInfo.getInstance().setSex(qqUserInfo.getGender());
            LocalUserInfo.getInstance().setPhotoUrl(qqUserInfo.getFigureurl_qq_2());
            LocalUserInfo.getInstance().setSex("QQ" + qqUserInfo.getNickname());
            mHandler.sendEmptyMessage(2);
        }

        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }
    }

}
