package com.lishi.baijiaxing.wxapi;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.register.RegisterManger;
import com.lishi.baijiaxing.utils.LocalUserInfo;
import com.lishi.baijiaxing.utils.WXUtils;
import com.lishi.baijiaxing.wxapi.model.WXTokenBean;
import com.lishi.baijiaxing.wxapi.model.WXUserInfo;
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

public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    private IWXAPI api;//微信
    private OkHttpClient httpClient;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                Intent ss = new Intent();
                ss.putExtra("result", "success");
                setResult(RESULT_OK, ss);
                Log.e("handleMessage", "页面关闭页面关闭页面关闭页面关闭");
            }
            Log.i("handleMessage","");
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        //注册到微信
        if (api == null) {
            registerWxChar(WXUtils.WXAPP_ID);
            api.handleIntent(getIntent(), this);
        }
        if (!api.isWXAppInstalled()) {
            Toast.makeText(this, "请先安装微信", Toast.LENGTH_SHORT).show();
            return;
        }
        wxLogin();
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
        handleIntent(intent);
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
        api = WXAPIFactory.createWXAPI(this, wxappId);
        api.registerApp(wxappId);
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {
//        switch (req.getType()) {
//        }
        Log.e("onReq", "ddsf" + req.getType());
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
                Toast.makeText(this, "result=" + "发送成功", Toast.LENGTH_SHORT).show();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = R.string.errcode_cancel;
                Toast.makeText(this, "result=" + "发送取消", Toast.LENGTH_SHORT).show();
                mHandler.sendEmptyMessage(2);
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = R.string.errcode_deny;
                Toast.makeText(this, "result=" + "发送拒绝", Toast.LENGTH_SHORT).show();
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
                Log.e("onFailure", "error=" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                WXTokenBean wxToken = gson.fromJson(response.body().string(), WXTokenBean.class);
                Log.e("onResponse", " =" + wxToken.toString());
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
                Log.e("onFailure", "userInfo error=" + e.toString());
                mHandler.sendEmptyMessage(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                WXUserInfo wxUserInfo = gson.fromJson(response.body().string(), WXUserInfo.class);
                Log.e("onResponse", "userInfo success=" + wxUserInfo.toString());
                LocalUserInfo.getInstance().setNickName(wxUserInfo.getNickname());
                LocalUserInfo.getInstance().setNid("wx" + wxUserInfo.getNickname());
                LocalUserInfo.getInstance().setSex(wxUserInfo.getSex() == 0 ? "女" : "男");
                LocalUserInfo.getInstance().setPhotoUrl(wxUserInfo.getHeadimgurl());
                mHandler.sendEmptyMessage(0);
            }
        });
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
