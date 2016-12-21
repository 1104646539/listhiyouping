package com.lishi.baijiaxing.pay;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.pay.adapter.PayAdapter;
import com.lishi.baijiaxing.pay.model.PayType;
import com.lishi.baijiaxing.pay.model.WxPrepay;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrder;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrderBean;
import com.lishi.baijiaxing.utils.ActivityManager;
import com.lishi.baijiaxing.utils.DividerItemDecoration;
import com.lishi.baijiaxing.utils.PayActivityManager;
import com.lishi.baijiaxing.utils.WXUtils;
import com.lishi.baijiaxing.view.TopNavigationBar;
import com.lishi.baijiaxing.wxapi.WXPayEntryActivity;
import com.lishi.baijiaxing.wxapi.presenter.WxPayPresenterImpl;
import com.lishi.baijiaxing.wxapi.view.WxPayView;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 支付
 */
public class PayActivity extends BaseActivity implements YiYuanHotAdapter.OnItemClickListener, WxPayView {
    private static SubmitOrder.DataBean mOrder;
    private TopNavigationBar mTopNavigationBar;
    private RecyclerView mRecyclerView;
    private List<PayType> mPayTypes;
    private String[] payNames = new String[]{"微信支付"};
    private String[] payBriefs = new String[]{"微信安全支付"};
    private int[] payIcons = new int[]{R.drawable.pay_wechat_icon};
    private PayAdapter adapter;
    private IWXAPI api;
    private WxPayPresenterImpl mWxPayPresenterImpl;
    public static String RESULT_PAY_SUCCESS = "pay_success";
    public static String RESULT_PAY_FAILED = "pay_failed";

    public static void setOrder(SubmitOrder.DataBean order) {
        mOrder = order;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        api = WXAPIFactory.createWXAPI(this, WXUtils.WXAPP_ID);
        api.registerApp(WXUtils.WXAPP_ID);
        init();

        PayActivityManager.getInstance().setPayActivity(this);

    }

    private void init() {
        getOrderInfo();
        if (mOrder != null) {
            initView();
        }
    }

    private void initView() {
        findId();
        initData();
        initRecyclerView();
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

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        adapter = new PayAdapter(this, mPayTypes);
        adapter.setOrder(mOrder);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
    }

    private void initData() {
        mPayTypes = new ArrayList<>();
        for (int i = 0; i < 1.; i++) {
            mPayTypes.add(new PayType(payNames[i], payIcons[i], payBriefs[i]));
        }
    }

    private void findId() {
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.navigation_pay);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_pay);
    }

    public void getOrderInfo() {
        mOrder = getIntent().getParcelableExtra("order");
    }

    @Override
    public void onClickListener(View view, int position) {
        switch (position) {
            case 1://微信支付
//                Logger.d("微信支付" + "订单号:" + mOrder.getSn() + "价格:" + mOrder.getPrice());
//                Intent startWxPayActivity = new Intent(this, WXPayEntryActivity.class);
//                startWxPayActivity.putExtra("orderNumber",mOrder.getSn());
//                startActivity(startWxPayActivity);
                if (mWxPayPresenterImpl == null) {
                    mWxPayPresenterImpl = new WxPayPresenterImpl(this);
                }
                mWxPayPresenterImpl.getPrepayInfo(mOrder.getSn());
                break;
        }
    }

    @Override
    public void getPrepayInfoSuccess(WxPrepay prepay) {
        startWxPay(prepay);
    }

    @Override
    public void getPrepayInfoFailed(String error) {

    }

    private void startWxPay(WxPrepay prepay) {
        if (api != null) {
            if (api.isWXAppInstalled()) {
                PayReq req = new PayReq();
                req.appId = prepay.getData().getAppid();//appid
                req.partnerId = prepay.getData().getPartnerid();//商户号
                req.prepayId = prepay.getData().getPrepayid();//预支付交易会话id
                req.packageValue = prepay.getData().getPackageX();//扩展字段
                req.nonceStr = prepay.getData().getNoncestr();//随机字符串
                req.timeStamp = prepay.getData().getTimestamp();//时间戳
                req.sign = prepay.getData().getPaySign();//签名

//                req.appId =WXUtils.WXAPP_ID;//appid
//                req.partnerId = "1411589602";//商户号
//                req.prepayId ="wx20161215154200b88652395c0341725185";//预支付交易会话id
//                req.packageValue = "Sign=WXPay";//扩展字段
//                req.nonceStr = "3rvfq4645wijj7o2lkjki0grfz0buubj";//随机字符串
//                req.timeStamp ="Sign=WXPay";//时间戳
//                req.sign = "3413C43B98201F97E9E1C794D9908193";//签名
                api.sendReq(req);
            }
        }
    }

    /**
     * 解析
     *
     * @param packageX
     * @return
     */
    private String analysisPrepayId(String packageX) {
        if (packageX == null) {
            return packageX;
        }
        String temp = packageX;
        return temp.substring(10, temp.length());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PayActivityManager.getInstance().clear();
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

    public PayActivity getI() {
        return this;
    }

    public static class PayBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("result");
            if (result.equals(RESULT_PAY_SUCCESS)) {
                if (PayActivityManager.getInstance().getPayActivity() != null) {
                    Intent startPayResultActivity = new Intent(PayActivityManager.getInstance().getPayActivity(), PayResultActivity.class);
                    startPayResultActivity.putExtra("price", mOrder.getPrice());
                    startPayResultActivity.putExtra("mode", "微信支付");
                    PayActivityManager.getInstance().getPayActivity().startActivity(startPayResultActivity);
                    PayActivityManager.getInstance().getPayActivity().finish();
                    PayActivityManager.getInstance().clear();
                }
            } else if (result.equals(RESULT_PAY_FAILED)) {
                if (PayActivityManager.getInstance().getPayActivity() != null) {
                    PayActivityManager.getInstance().getPayActivity().finish();
                    PayActivityManager.getInstance().clear();
                }
            }
        }
    }
}
