package com.lishi.baijiaxing.myOrders.view;

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
import com.lishi.baijiaxing.shoppingCart.model.StoreBean;
import com.lishi.baijiaxing.submitOrder.adapter.SubmitOrderAdapter;
import com.lishi.baijiaxing.view.MyListView;

/**
 * 订单详情
 */
public class OrderDetailsActivity extends BaseActivity implements View.OnClickListener {
    private MyListView mMyListView;
    private StoreBean mStoreBean;
    private LinearLayout bottom_ll1, bottom_ll2;
    /**
     * 代付款——取消订单
     */
    private TextView cancel_order;
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
    }

    private void findId() {
        mMyListView = (MyListView) findViewById(R.id.listview_order_details);

        cancel_root = LayoutInflater.from(this).inflate(R.layout.cancel_order_dialog, null, false);
        yes = (TextView) cancel_root.findViewById(R.id.cancel_yes);
        no = (TextView) cancel_root.findViewById(R.id.cancel_no);
        cancel_order = (TextView) findViewById(R.id.order_details__bottom1_cancel);

        cancel_order.setOnClickListener(this);
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
            case R.id.order_details__bottom1_cancel://弹出取消订单对话框
                showCancelOrderWindow();
                break;
            case R.id.cancel_yes://确定
                Toast.makeText(this, "确定取消", Toast.LENGTH_SHORT).show();
                pop_cancelDialog.dismiss();
                break;
            case R.id.cancel_no://取消
                pop_cancelDialog.dismiss();
                Toast.makeText(this, "取消", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
