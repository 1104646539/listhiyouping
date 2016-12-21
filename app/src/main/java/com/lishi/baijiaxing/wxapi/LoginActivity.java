package com.lishi.baijiaxing.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.activity.MainActivity;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.manager.LocalToken;
import com.lishi.baijiaxing.register.RegisterManger;
import com.lishi.baijiaxing.register.view.RegisterActivity1;
import com.lishi.baijiaxing.retrievePassword.view.RetrieveActivity1;
import com.lishi.baijiaxing.utils.LocalUserInfo;
import com.lishi.baijiaxing.utils.LoginUtil;
import com.lishi.baijiaxing.view.TopNavigationBar;
import com.lishi.baijiaxing.wxapi.model.Login;
import com.lishi.baijiaxing.wxapi.model.QQTokenBean;
import com.lishi.baijiaxing.wxapi.model.QQUserInfo;
import com.lishi.baijiaxing.wxapi.model.WXUserInfo;
import com.lishi.baijiaxing.wxapi.presenter.LoginPresenterImpl;
import com.lishi.baijiaxing.wxapi.view.LoginView;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginView {
    private TextView tv_login_register;//注册
    private TextView tv_login_retrievepassword;
    private TopNavigationBar mTopNavigationBar;
    private ImageView login_wxChar, login_qq;
    private TextView tv_login_local;
    private EditText ed_name, ed_paw;
    private BaseUiListener mBaseUiListener;
    private BaseUserListener mBaseUserListener;
    private LoginPresenterImpl mLoginPresenterImpl;
    private UserInfo userinfo;
    private String scope = "all";
    private QQUserInfo mQQUserInfo;
    private String openid;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                mQQUserInfo = (QQUserInfo) msg.obj;
                mLoginPresenterImpl.qqLogin(mQQUserInfo, openid);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (MainActivity.mTencent != null) {
            if (MainActivity.mTencent.isSessionValid()) {
                MainActivity.mTencent.logout(this);
            }
        }

        if (mLoginPresenterImpl == null) {
            mLoginPresenterImpl = new LoginPresenterImpl(LoginActivity.this);
        }
        MainActivity.mTencent = Tencent.createInstance("1105736969", getApplicationContext());

        RegisterManger.getInstantion().addActivity(this);
        findId();
        initView();
        mBaseUiListener = new BaseUiListener();
    }

    @Override
    public void wxChatLoginSuccess(Login login) {
        Intent success = new Intent();
        success.putExtra("status", "weChat");
        success.putExtra("login", login);
        setResult(RESULT_OK, success);
        Log.i("handleMessage", "微信登录成功" + login.toString());
        Toast.makeText(this, "微信登录成功", Toast.LENGTH_SHORT).show();
        saveToken(login);
        finish();
    }

    /**
     * 保存token
     *
     * @param login
     */
    private void saveToken(Login login) {
        LoginUtil.getInstance().setLogin(login);
    }

    @Override
    public void qqLoginSuccess(Login login) {
        Intent success = new Intent();
        success.putExtra("status", "qq");
        success.putExtra("login", login);
        setResult(RESULT_OK, success);
        Log.i("handleMessage", "qq登录成功" + login.toString());
        Toast.makeText(this, "qq登录成功", Toast.LENGTH_SHORT).show();
        saveToken(login);
        finish();
    }

    @Override
    public void localLoginSuccess(Login login) {
        Intent success = new Intent();
        success.putExtra("status", "local");
        success.putExtra("login", login);
        setResult(RESULT_OK, success);
        Log.i("handleMessage", "帐号登录成功" + login.toString());
        Toast.makeText(this, "帐号登录成功", Toast.LENGTH_SHORT).show();
        saveToken(login);
        finish();
    }

    @Override
    public void loginFailed(String error) {
        Log.i("handleMessage", "登录失败" + error);
        Toast.makeText(this, "登录失败\n" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void closeDialog() {

    }

    @Override
    public void loadDataSuccess(Object data) {

    }

    @Override
    public void loadDataFailed(String error) {

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
        openid = qqTokenBean.getOpenid();

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
        tv_login_local.setOnClickListener(this);
    }

    private void findId() {
        tv_login_register = (TextView) findViewById(R.id.tv_login_register);
        tv_login_retrievepassword = (TextView) findViewById(R.id.tv_login_retrievepassword);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.navigation_login);
        login_wxChar = (ImageView) findViewById(R.id.login_wxChar);
        login_qq = (ImageView) findViewById(R.id.login_qq);
        tv_login_local = (TextView) findViewById(R.id.tv_login_local);
        ed_name = (EditText) findViewById(R.id.ed_login_name);
        ed_paw = (EditText) findViewById(R.id.ed_login_paw);
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
            case R.id.login_wxChar://微信登录
                Intent startWxActivity = new Intent(this, WXEntryActivity.class);
                startActivityForResult(startWxActivity, 0);
                break;
            case R.id.login_qq://qq登录
                qqLogin();
                break;
            case R.id.tv_login_local://帐号登录
                localLogin();
                break;
        }
    }

    /**
     * 帐号登录
     */
    private void localLogin() {
        String name = ed_name.getText().toString().trim();
        String paw = ed_paw.getText().toString().trim();
        if (name.length() >= 6 && paw.length() >= 6) {
            mLoginPresenterImpl.localLogin(name, paw);
            Log.i("localLogin", "登录中");
        } else {
            Toast.makeText(this, "帐号或密码格式错误", Toast.LENGTH_SHORT).show();
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
    protected void onResume() {
        super.onResume();
        if (mLoginPresenterImpl == null) {
            mLoginPresenterImpl = new LoginPresenterImpl(LoginActivity.this);
        }
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
                if (data.getStringExtra("state") != null && data.getStringExtra("state").equals("success")) {
                    WXUserInfo wxUserInfo = data.getParcelableExtra("result");
                    wxLogin(wxUserInfo);
                    Log.i("onActivityResult", "获取用户信息成功" + wxUserInfo.toString());
                }
            }
        } else if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.RESULT_LOGIN) {
                Tencent.handleResultData(data, mBaseUiListener);
            }
        }
    }

    /**
     * 微信已获取授权，发送登录信息到服务端
     *
     * @param wxUserInfo
     */
    private void wxLogin(WXUserInfo wxUserInfo) {
        if (wxUserInfo != null) {
            mLoginPresenterImpl.wxChatLogin(wxUserInfo);
        }
    }


    /**
     * 获取用户数据成功/失败
     */
    private class BaseUserListener implements IUiListener {
        @Override
        public void onComplete(Object o) {
            Gson gson = new Gson();
            QQUserInfo qqUserInfo = gson.fromJson(o.toString(), QQUserInfo.class);
            Log.i("BaseApiListener", "qqUserInfo=" + qqUserInfo.toString());
            mHandler.sendMessage(mHandler.obtainMessage(1, qqUserInfo));
        }

        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }
    }

}
