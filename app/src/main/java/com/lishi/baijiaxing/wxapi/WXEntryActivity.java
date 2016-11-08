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
public class WXEntryActivity extends BaseActivity implements View.OnClickListener, SignInView, IWXAPIEventHandler {
    private String TAG = "WXEntryActivity";
    private TextView tv_login_register;//注册
    private TextView tv_login_retrievepassword;
    private TopNavigationBar mTopNavigationBar;
    private ImageView login_wxChar, login_qq;
    private IWXAPI api;//微信
    private OkHttpClient httpClient;
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
        //注册到微信
        if (api == null) {
            registerWxChar(WXUtils.WXAPP_ID);
            api.handleIntent(getIntent(), this);
        }

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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
        handleIntent(intent);
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
            case R.id.login_wxChar://微信登录
                if (!api.isWXAppInstalled()) {
                    Toast.makeText(this, "请先安装微信", Toast.LENGTH_SHORT).show();
                    return;
                }
                wxLogin();
                break;
            case R.id.login_qq:
                break;
        }
    }

    /**
     * 微信登录
     */
    private void wxLogin() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        api.sendReq(req);
    }

    /**
     * 注册到微信
     *
     * @param wxappId
     */
    private void registerWxChar(String wxappId) {
        api = WXAPIFactory.createWXAPI(this, wxappId, true);
        api.registerApp(wxappId);
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

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {
        switch (req.getType()) {
        }
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    @Override
    public void onResp(BaseResp resp) {
        int result = 0;
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = R.string.errcode_success;
                String code = ((SendAuth.Resp) resp).code;
                startWxToken(code);
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = R.string.errcode_cancel;
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = R.string.errcode_deny;
                break;
            default:
                result = R.string.errcode_unknown;
                break;
        }
    }

    /**
     * 获取token凭证,openid等
     *
     * @param code
     */
    private void startWxToken(String code) {
        String tokenUrl = getTokenRequest(code);
        httpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(tokenUrl).build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("onFailure", "error=" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                WXTokenBean wxToken = gson.fromJson(response.body().string(), WXTokenBean.class);
                Log.i("onResponse", "gson=" + wxToken.toString());
                getUserInfo(wxToken);
            }
        });
    }

    /**
     * 获取用户信息
     *
     * @param wxUserInfo
     */
    private void getUserInfo(WXTokenBean wxUserInfo) {
        String userInfoUrl = getUserInfoRequest(wxUserInfo);
        if (httpClient == null) {
            httpClient = new OkHttpClient();
        }
        final Request request = new Request.Builder().url(userInfoUrl).build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("onFailure", "userInfo error=" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                WXUserInfo wxUserInfo = gson.fromJson(response.body().string(), WXUserInfo.class);
                Log.i("onResponse", "userInfo success=" + wxUserInfo.toString());
                LocalUserInfo.getInstance().setNickName(wxUserInfo.getNickname());
                LocalUserInfo.getInstance().setNid("wx"+wxUserInfo.getNickname());
                LocalUserInfo.getInstance().setSex(wxUserInfo.getSex() == 0 ? "女" : "男");
                LocalUserInfo.getInstance().setPhotoUrl(wxUserInfo.getHeadimgurl());
               RegisterManger.getInstantion().closeAll();
            }
        });
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

    /**
     * 获取根据Token,OpenId得到的微信授权Token的Get请求地址
     *
     * @param wxUserInfo
     * @return
     */
    private String getUserInfoRequest(WXTokenBean wxUserInfo) {
        String tokenRequest = WXUtils.GET_REQUEST_USER_INFO.replace("ACCESS_TOKEN", wxUserInfo.getAccess_token()).
                replace("OPENID", wxUserInfo.getOpenid());
        return tokenRequest;
    }

    private void handleIntent(Intent intent) {
        SendAuth.Resp resp = new SendAuth.Resp(intent.getExtras());
        if (resp.errCode == BaseResp.ErrCode.ERR_OK) {
//            String code = resp.code;
//            Toast.makeText(this, "errcode_success" + code, Toast.LENGTH_SHORT).show();
//            startWxToken(code);
            finish();
        }
    }

    /**
     * 获取根据CODE得到的微信授权Token的Get请求地址
     *
     * @param code
     * @return
     */
    private static String getTokenRequest(String code) {
        String tokenRequest = WXUtils.GET_REQUEST_ACCESS_TOKEN.replace("APPID", WXUtils.WXAPP_ID).
                replace("SECRET", WXUtils.APP_SECRET_ID).
                replace("CODE", code);
        return tokenRequest;
    }
}
