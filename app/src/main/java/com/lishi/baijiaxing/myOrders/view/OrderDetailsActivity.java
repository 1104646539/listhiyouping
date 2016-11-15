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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.myOrders.adapter.OrderDetailsAdapter;
import com.lishi.baijiaxing.myOrders.model.MyOrderFormBean;
import com.lishi.baijiaxing.shoppingCart.model.StoreBean;
import com.lishi.baijiaxing.submitOrder.adapter.SubmitOrderAdapter;
import com.lishi.baijiaxing.view.MyListView;

/**
 * 订单详情
 */
public class OrderDetailsActivity extends BaseActivity implements View.OnClickListener {
    private MyListView mMyListView;
    private StoreBean mStoreBean;
    private int mState;
    private int mPosition;
    private TextView order_state;
    private LinearLayout bottom_ll1, bottom_ll2, bottom_ll3, bottom_ll4, bottom_ll5;
    private LinearLayout order_details_endTime;

    private TextView bt1_cancel_order, bt1_pay;
    private TextView bt2_deleteOrder, bt2_again;
    private TextView bt3_remind, bt3_apply;
    private TextView bt4_checkLogistics, bt4_affirm;
    private TextView bt5_deleteOrder, bt5_payment;

    private PopupWindow pop_cancelDialog;
    private View cancel_root;
    /**
     * 是否取消
     */
    private TextView yes, no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        init();
    }

    private void init() {
        findId();
        initData();
        initView();
    }

    private void initView() {
        if (mStoreBean != null) {
            OrderDetailsAdapter adapter = new OrderDetailsAdapter(this, mStoreBean.getCommodityBeanList());
            mMyListView.setAdapter(adapter);
        }
    }

    private void initData() {
        mStoreBean = getIntent().getParcelableExtra("data");
        mState = getIntent().getIntExtra("state", -1);
        mPosition = getIntent().getIntExtra("position", -1);
        switchState();
    }

    private void switchState() {
        switch (mState) {
            case -1:
                break;
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

        bottom_ll1 = (LinearLayout) findViewById(R.id.order_details__bottom1);
        bottom_ll2 = (LinearLayout) findViewById(R.id.order_details__bottom2);
        bottom_ll3 = (LinearLayout) findViewById(R.id.order_details__bottom3);
        bottom_ll4 = (LinearLayout) findViewById(R.id.order_details__bottom4);
        bottom_ll5 = (LinearLayout) findViewById(R.id.order_details__bottom5);
        order_details_endTime = (LinearLayout) findViewById(R.id.order_details_endTime);
        order_state = (TextView) findViewById(R.id.order_details_state);


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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_yes://确定
                Toast.makeText(this, "确定取消", Toast.LENGTH_SHORT).show();
                pop_cancelDialog.dismiss();
                Intent result = new Intent();
                result.putExtra("position", mPosition);
                setResult(RESULT_OK,result);
                finish();
                break;
            case R.id.cancel_no://取消
                pop_cancelDialog.dismiss();
                Toast.makeText(this, "取消", Toast.LENGTH_SHORT).show();
                break;
            case R.id.order_details__bottom1_cancel://弹出取消订单对话框
                showCancelOrderWindow();
                break;
            case R.id.order_details__bottom1_pay:
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
}
