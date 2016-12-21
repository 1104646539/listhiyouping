package com.lishi.baijiaxing.wxapi;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.pay.PayActivity;
import com.lishi.baijiaxing.pay.PayResultActivity;
import com.lishi.baijiaxing.utils.PayActivityManager;
import com.lishi.baijiaxing.utils.WXUtils;
import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pay_result);

        api = WXAPIFactory.createWXAPI(this, WXUtils.WXAPP_ID);
        api.handleIntent(getIntent(), this);
        api.registerApp(WXUtils.WXAPP_ID);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) { 
        Logger.d("onPayFinish, errCode = " + resp.errCode+"openId:"+resp.openId+"transaction:"+resp.transaction);

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle(R.string.app_tip);
//            builder.setMessage(getString(R.string.pay_result_callback_msg, String.valueOf(resp.errCode)));
//            builder.show();
            Intent intent = new Intent("lishi.intent.action.PAY");
            if (resp.errCode == 0) {
                intent.putExtra("result", PayActivity.RESULT_PAY_SUCCESS);

//                Intent startPayResultActivity = new Intent(PayActivityManager.getInstance().getPayActivity(), PayResultActivity.class);
//                startPayResultActivity.putExtra("price", mOrder.getPrice());
//                startPayResultActivity.putExtra("mode", "微信");
//                PayActivityManager.getInstance().getPayActivity().startActivity(startPayResultActivity);

                Toast.makeText(this, "resp:" + "支付成功" + resp.errCode+"str:"+resp.errStr, Toast.LENGTH_SHORT).show();
            } else {
                intent.putExtra("result", PayActivity.RESULT_PAY_FAILED);
                Toast.makeText(this, "resp:" + "支付失败" + resp.errCode+"str:"+resp.errStr, Toast.LENGTH_SHORT).show();
            }
            sendBroadcast(intent);
            finish();
        }
    }
}