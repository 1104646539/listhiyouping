package com.lishi.baijiaxing.myOrders.view;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.details.CommodityDetailsActivity;
import com.lishi.baijiaxing.myOrders.adapter.OrderDetailsAdapter;
import com.lishi.baijiaxing.myOrders.model.MyOrderFormBean;
import com.lishi.baijiaxing.myOrders.model.MyOrderList;
import com.lishi.baijiaxing.myOrders.model.OrderDetails;
import com.lishi.baijiaxing.myOrders.presenter.OrderDetailsPresenterImpl;
import com.lishi.baijiaxing.pay.PayActivity;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;
import com.lishi.baijiaxing.shoppingCart.model.StoreBean;
import com.lishi.baijiaxing.submitOrder.adapter.SubmitOrderAdapter;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrder;
import com.lishi.baijiaxing.utils.NetUtils;
import com.lishi.baijiaxing.utils.ProgressBarUtil;
import com.lishi.baijiaxing.utils.TimeUtils;
import com.lishi.baijiaxing.view.MyListView;
import com.lishi.baijiaxing.view.TopNavigationBar;
import com.orhanobut.logger.Logger;

import org.w3c.dom.Text;

import cn.iwgang.countdownview.CountdownView;

/**
 * 订单详情
 */
public class OrderDetailsActivity extends BaseActivity implements View.OnClickListener, OrderDetailsView, AdapterView.OnItemClickListener {
    private MyListView mMyListView;
    private TopNavigationBar mTopNavigationBar;
    private int mState;
    private int mPosition;
    private TextView order_state;
    private LinearLayout bottom_ll1, bottom_ll2, bottom_ll3, bottom_ll4, bottom_ll5;
    private LinearLayout order_details_endTime;
    private OrderDetails mOrderDetails;

    private TextView bt1_cancel_order, bt1_pay;
    private TextView bt2_deleteOrder, bt2_again;
    private TextView bt3_remind, bt3_apply;
    private TextView bt4_checkLogistics, bt4_affirm;
    private TextView bt5_deleteOrder, bt5_payment;

    private PopupWindow pop_cancelDialog;
    private View cancel_root;

    private TextView tv_orderNum;//订单号
    private TextView tv_status;//订单状态
    private TextView tv_addr_name;//收货人
    private TextView tv_addr_details;//收货地址
    private TextView tv_addr_number;//收货电话
    private TextView tv_payWay;//支付方式
    private TextView tv_bill;//发票信息
    private TextView tv_remark;//留言
    private TextView tv_commodityPrice;//商品总价
    private TextView tv_freight;//运费
    private TextView tv_totalPrice;//总价
    private TextView tv_orderStartTime;//下单时间
//    private CountdownView mCountdownView;//付款截止时间

//    tv_orderNum
//            tv_status
//    tv_addr_name
//            tv_addr_details
//    tv_addr_number
//            tv_payWay
//    tv_bill
//            tv_remark
//    tv_commodityPrice
//            tv_freight
//    tv_totalPrice

    /**
     * 是否取消
     */
    private TextView yes, no;
    private String mOid;//订单id

    private OrderDetailsPresenterImpl mOrderDetailsPresenter;
    private ProgressBarUtil mProgressBarUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        init();
    }

    private void init() {
        findId();
        initData();
    }

    private void initView() {
        mTopNavigationBar.setOnTopClick(new TopNavigationBar.OnTopClick() {
            @Override
            public void onTopLeftClick(View view) {
                finish();
            }

            @Override
            public void onTopRightClick(View view) {

            }
        });
        if (mOrderDetailsPresenter == null) {
            mOrderDetailsPresenter = new OrderDetailsPresenterImpl(this);
        }
        mOrderDetailsPresenter.loadOrderDetails(mOid);
    }

    private void initData() {
        mOid = getIntent().getStringExtra("oid");
        if (mOid != null && !mOid.equals("")) {
            initView();
        }
    }

    private void switchState() {
        switch (mState) {
            case MyOrderFormBean.STAYPAYMENT://待付款
                bottom_ll1.setVisibility(View.VISIBLE);
                order_details_endTime.setVisibility(View.VISIBLE);
                bottom_ll2.setVisibility(View.GONE);
                bottom_ll3.setVisibility(View.GONE);
                bottom_ll4.setVisibility(View.GONE);
                bottom_ll5.setVisibility(View.GONE);

                order_state.setText("待付款");
                break;
            case MyOrderFormBean.STAYSHIPMENTS://待发货
                bottom_ll1.setVisibility(View.GONE);
                order_details_endTime.setVisibility(View.GONE);
                bottom_ll2.setVisibility(View.GONE);
                bottom_ll3.setVisibility(View.VISIBLE);
                bottom_ll4.setVisibility(View.GONE);
                bottom_ll5.setVisibility(View.GONE);
                order_state.setText("待发货");
                break;
            case MyOrderFormBean.YETSHIPMENTS://已发货
                bottom_ll1.setVisibility(View.GONE);
                bottom_ll2.setVisibility(View.GONE);
                order_details_endTime.setVisibility(View.GONE);
                bottom_ll3.setVisibility(View.GONE);
                bottom_ll4.setVisibility(View.VISIBLE);
                bottom_ll5.setVisibility(View.GONE);
                order_state.setText("等待确认收货");
                break;
            case MyOrderFormBean.STAYEVALUATE://待评价
                bottom_ll1.setVisibility(View.GONE);
                bottom_ll2.setVisibility(View.GONE);
                bottom_ll3.setVisibility(View.GONE);
                order_details_endTime.setVisibility(View.GONE);
                bottom_ll4.setVisibility(View.GONE);
                bottom_ll5.setVisibility(View.VISIBLE);
                order_state.setText("待评价");
                break;
            case MyOrderFormBean.REFUNDFINISH://退款完成
                break;
            case MyOrderFormBean.REFUNDIN://退款中
                break;
            case MyOrderFormBean.DEALFINISH://完成交易
                break;
        }
    }

    private void findId() {
        mMyListView = (MyListView) findViewById(R.id.listview_order_details);

        cancel_root = LayoutInflater.from(this).inflate(R.layout.cancel_order_dialog, null, false);
        yes = (TextView) cancel_root.findViewById(R.id.cancel_yes);
        no = (TextView) cancel_root.findViewById(R.id.cancel_no);
        bt1_cancel_order = (TextView) findViewById(R.id.order_details__bottom1_cancel);
        bt1_pay = (TextView) findViewById(R.id.order_details__bottom1_pay);
        bt2_deleteOrder = (TextView) findViewById(R.id.order_details__bottom2_deleteOrder);
        bt2_again = (TextView) findViewById(R.id.order_details__bottom2_again);
        bt3_remind = (TextView) findViewById(R.id.order_details__bottom3_remind);
        bt3_apply = (TextView) findViewById(R.id.order_details__bottom3_apply);
        bt4_checkLogistics = (TextView) findViewById(R.id.order_details__bottom4_checkLogistics);
        bt4_affirm = (TextView) findViewById(R.id.order_details__bottom4_affirm);
        bt5_deleteOrder = (TextView) findViewById(R.id.order_details__bottom5_deleteOrder);
        bt5_payment = (TextView) findViewById(R.id.order_details__bottom5_payment);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.navigation_orderDetails);

        bottom_ll1 = (LinearLayout) findViewById(R.id.order_details__bottom1);
        bottom_ll2 = (LinearLayout) findViewById(R.id.order_details__bottom2);
        bottom_ll3 = (LinearLayout) findViewById(R.id.order_details__bottom3);
        bottom_ll4 = (LinearLayout) findViewById(R.id.order_details__bottom4);
        bottom_ll5 = (LinearLayout) findViewById(R.id.order_details__bottom5);
        order_details_endTime = (LinearLayout) findViewById(R.id.order_details_endTime);
        order_state = (TextView) findViewById(R.id.order_details_state);


        tv_orderNum = (TextView) findViewById(R.id.order_details_orderNum);
        tv_status = (TextView) findViewById(R.id.order_details_state);
        tv_addr_name = (TextView) findViewById(R.id.order_details_addr_userName);
        tv_addr_details = (TextView) findViewById(R.id.order_details_addrDetails);
        tv_addr_number = (TextView) findViewById(R.id.order_details_photoNum);
        tv_payWay = (TextView) findViewById(R.id.order_details_payWay);
        tv_bill = (TextView) findViewById(R.id.order_details_bill);
        tv_remark = (TextView) findViewById(R.id.order_details_leaveWord);
        tv_commodityPrice = (TextView) findViewById(R.id.order_details_commodityPrice);
        tv_freight = (TextView) findViewById(R.id.order_details_freight);
        tv_totalPrice = (TextView) findViewById(R.id.order_details_buyPrice);
        tv_orderStartTime = (TextView) findViewById(R.id.order_details_startTime);
//        mCountdownView = (CountdownView) findViewById(R.id.order_details_festivalCountDown);


        bt1_cancel_order.setOnClickListener(this);
        bt1_pay.setOnClickListener(this);
        bt2_deleteOrder.setOnClickListener(this);
        bt2_again.setOnClickListener(this);
        bt3_remind.setOnClickListener(this);
        bt3_apply.setOnClickListener(this);
        bt4_checkLogistics.setOnClickListener(this);
        bt4_affirm.setOnClickListener(this);
        bt5_deleteOrder.setOnClickListener(this);
        bt5_payment.setOnClickListener(this);
        bottom_ll1.setOnClickListener(this);
        bottom_ll2.setOnClickListener(this);
        bottom_ll3.setOnClickListener(this);
        bottom_ll4.setOnClickListener(this);
        bottom_ll5.setOnClickListener(this);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_yes://确定
//                Toast.makeText(this, "确定取消", Toast.LENGTH_SHORT).show();
                pop_cancelDialog.dismiss();
//                Intent result = new Intent();
//                result.putExtra("position", mPosition);
//                setResult(RESULT_OK, result);
//                finish();
                cancelOrder();
                break;
            case R.id.cancel_no://取消
                pop_cancelDialog.dismiss();
                Toast.makeText(this, "取消", Toast.LENGTH_SHORT).show();
                break;
            case R.id.order_details__bottom1_cancel://弹出取消订单对话框
                showCancelOrderWindow();
                break;
            case R.id.order_details__bottom1_pay:
                startPayActivity();
                break;
            case R.id.order_details__bottom2_deleteOrder:

                break;
            case R.id.order_details__bottom2_again:
                break;
            case R.id.order_details__bottom3_remind:
                break;
            case R.id.order_details__bottom3_apply:
                break;
            case R.id.order_details__bottom4_checkLogistics:
                break;
            case R.id.order_details__bottom4_affirm:
                break;
            case R.id.order_details__bottom5_deleteOrder:
                showCancelOrderWindow();
                break;
            case R.id.order_details__bottom5_payment:
                break;
        }
    }

    /**
     * 去支付
     */
    private void startPayActivity() {
        SubmitOrder.DataBean sd = new SubmitOrder.DataBean();
        sd.setSn(mOrderDetails.getData().getOrderNumber());
        sd.setPrice(mOrderDetails.getData().getTotalPrice());
        Intent startPayActivity = new Intent(this, PayActivity.class);
        startPayActivity.putExtra("order", sd);
        startActivity(startPayActivity);
        finish();
    }
    /**
     * 取消订单
     */
    private void cancelOrder() {
        if (mOrderDetailsPresenter == null){
            mOrderDetailsPresenter  = new OrderDetailsPresenterImpl(this);
        }
        mOrderDetailsPresenter.changeOrderStatus(mOrderDetails.getData().getOid(), ""+MyOrderFormBean.CANCELORDER);
    }

    @Override
    public void loadOrderDetailsSuccess(OrderDetails orderDetails) {
        this.mOrderDetails = orderDetails;
        switchState();
        if (mOrderDetails == null) {
            return;
        }
        initDetails();
    }

    private void initDetails() {
        OrderDetailsAdapter adapter = new OrderDetailsAdapter(this, mOrderDetails.getData().getCommodityList());
        mMyListView.setAdapter(adapter);

        tv_orderNum.setText("订单号:" + mOrderDetails.getData().getOrderNumber());
        switchStatue();
        tv_addr_name.setText(mOrderDetails.getData().getAddr().getConsigneeName());
        tv_addr_details.setText(mOrderDetails.getData().getAddr().getDetails());
        tv_addr_number.setText(mOrderDetails.getData().getAddr().getConsigneeNumber());
        tv_payWay.setText("在线支付");
        tv_bill.setText(mOrderDetails.getData().getInvoice());
        String remark = mOrderDetails.getData().getRemark() != null ? mOrderDetails.getData().getRemark() : "";
        tv_remark.setText("给商家的留言:" + remark);
        tv_commodityPrice.setText("￥" + Double.valueOf(mOrderDetails.getData().getCommodityTotalPrice()));
        tv_freight.setText("￥" + Double.valueOf(mOrderDetails.getData().getFreight()));
        tv_totalPrice.setText("￥" + Double.valueOf(mOrderDetails.getData().getTotalPrice()));
        tv_orderStartTime.setText(TimeUtils.timeStamp2Date(mOrderDetails.getData().getOrderStartTime(), "yyyy-MM-dd HH:mm:ss"));
//        mOrderDetails.getData().getOrderStartTime() + 86400
        long countTime = Long.valueOf(mOrderDetails.getData().getOrderStartTime()) + 86400 - Long.valueOf(TimeUtils.timeStamp());
//        mCountdownView.start(countTime);
        Logger.d("countTime:" + countTime);
        mMyListView.setOnItemClickListener(this);
    }

    private void switchStatue() {
//        public final static int STAYPAYMENT = 0;//待付款
//        public final static int STAYSHIPMENTS = 1;//待发货
//        public final static int YETSHIPMENTS = 5;//已发货
//        public final static int STAYEVALUATE = 6;//待评价
//        public final static int REFUNDFINISH = 9;//退款完成
//        public final static int REFUNDIN = 4;//退款中
//        public final static int DEALFINISH = 8;//完成交易
//        public final static int CANCELORDER = 7;//取消订单
        int status = Integer.valueOf(mOrderDetails.getData().getOrderStatus());
        String mStatus = "";
        switch (status) {
            case MyOrderFormBean.STAYPAYMENT:
                mStatus = "待付款";
                break;
            case MyOrderFormBean.STAYSHIPMENTS:
                mStatus = "待发货";
                break;
            case MyOrderFormBean.YETSHIPMENTS:
                mStatus = "已发货";
                break;
            case MyOrderFormBean.STAYEVALUATE:
                mStatus = "待评价";
                break;
            case MyOrderFormBean.REFUNDFINISH:
                mStatus = "退款完成";
                break;
            case MyOrderFormBean.REFUNDIN:
                mStatus = "退款中";
                break;
            case MyOrderFormBean.DEALFINISH:
                mStatus = "完成交易";
                break;
            case MyOrderFormBean.CANCELORDER:
                mStatus = "取消订单";
                break;
        }
        tv_status.setText(mStatus);

    }

    @Override
    public void loadOrderDetailsFailed(String error) {
        Logger.d(error);
    }

    @Override
    public void changeOrderStatusSuccess(SCOperation orderList) {
        initView();
    }

    @Override
    public void changeOrderStatusFailed(String error) {

    }

    @Override
    public void showDialog() {
        if (mProgressBarUtil == null) {
            mProgressBarUtil = new ProgressBarUtil(this);
        }
        mProgressBarUtil.show();
    }

    @Override
    public void closeDialog() {
        if (mProgressBarUtil != null) {
            mProgressBarUtil.dismiss();
        }
    }

    @Override
    public void loadDataSuccess(Object data) {

    }

    @Override
    public void loadDataFailed(String error) {

    }

    /**
     * 显示取消订单对话框
     */
    private void showCancelOrderWindow() {
        pop_cancelDialog = new PopupWindow(cancel_root, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        pop_cancelDialog.setFocusable(true);
        pop_cancelDialog.setBackgroundDrawable(new BitmapDrawable());
        setAlpha(0.4F);
        pop_cancelDialog.showAtLocation(cancel_root, Gravity.CENTER, 0, 0);
        pop_cancelDialog.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setAlpha(1.0F);
                pop_cancelDialog.dismiss();
            }
        });
    }

    /**
     * 修改窗体透明度
     *
     * @param alpha
     */
    private void setAlpha(float alpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = alpha;
        getWindow().setAttributes(lp);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent startCommodityDetails = new Intent(this, CommodityDetailsActivity.class);
        if (mOrderDetails.getData().getCommodityList().get(position) != null &&
                !mOrderDetails.getData().getCommodityList().get(position).getGid().equals("0")) {
            String gid = mOrderDetails.getData().getCommodityList().get(position).getGid();
            if (gid != null && !gid.equals("0") && NetUtils.isConnected(this)) {
                startCommodityDetails.putExtra("gid", gid);
                startActivity(startCommodityDetails);
            }
        }
    }
}
