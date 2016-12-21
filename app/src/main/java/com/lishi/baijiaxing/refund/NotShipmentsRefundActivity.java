package com.lishi.baijiaxing.refund;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.refund.widget.LoopView;
import com.lishi.baijiaxing.refund.widget.OnItemSelectedListener;
import com.lishi.baijiaxing.view.TopNavigationBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 未发货->退款
 */
public class NotShipmentsRefundActivity extends BaseActivity implements View.OnClickListener {
    private TextView item1_no, item1_yes;
    private TextView item3, item4, item5;
    private LinearLayout ll4, ll5;
    private RelativeLayout ll3;

    private TextView tv_refund;
    private int state = 0;
    private PopupWindow popup;
    private View popupContent;
    private LoopView mLoopView;
    private TextView pop_yes, pop_no;
    private List<String> datas;
    private TopNavigationBar mTopNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund);

        init();

    }

    private void init() {
        findId();
        initData();
        initView();
    }

    private void initData() {
        datas = new ArrayList<>();
        datas.add("其他原因");
        datas.add("不想买了");
        datas.add("该商品利世优品降价了");
        datas.add("其他渠道价格更低");
        datas.add("支付方式有误/无法支付");
        datas.add("重复下单/误下单");
        datas.add("商品买错了（颜色、尺寸、数量等...");
        datas.add("忘记使用优惠券等");
        datas.add("发票信息有误");
        datas.add("配送信息有误");
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

    }

    private void findId() {
        item1_no = (TextView) findViewById(R.id.returned_item1_no);
        item1_yes = (TextView) findViewById(R.id.returned_item1_yes);
        item3 = (TextView) findViewById(R.id.returned_item3_tv2);
        item4 = (TextView) findViewById(R.id.returned_item4_tv);
        item5 = (TextView) findViewById(R.id.returned_item5_tv);
        tv_refund = (TextView) findViewById(R.id.returned_returned1);
        ll3 = (RelativeLayout) findViewById(R.id.returned_ll3);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.navigation_notShipments_refund);

        item1_no.setOnClickListener(this);
        item1_yes.setOnClickListener(this);
        item3.setOnClickListener(this);
        item4.setOnClickListener(this);
        item5.setOnClickListener(this);
        tv_refund.setOnClickListener(this);
        ll3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.returned_item1_no:
                state = 1;
                item1_no.setBackgroundResource(R.drawable.refund_state2);
                item1_yes.setBackgroundResource(R.drawable.refund_state1);
                break;
            case R.id.returned_item1_yes:
                state = 2;
                item1_no.setBackgroundResource(R.drawable.refund_state1);
                item1_yes.setBackgroundResource(R.drawable.refund_state2);
                break;
            case R.id.returned_ll3:
                startReturnedReason();
                break;
            case R.id.returned_item4_tv:
                break;
            case R.id.returned_item5_tv:
                break;
            case R.id.returned_returned1:
                submit();
                break;
            case R.id.reason_no:
                popup.dismiss();
                break;
            case R.id.reason_yes:
                String reason = datas.get(mLoopView.getSelectedItem());
                item3.setText(reason);
                popup.dismiss();
                break;

        }
    }

    /**
     * 退款理由选择
     */
    private void startReturnedReason() {
        initPop();
        popupContent = LayoutInflater.from(this).inflate(R.layout.refund_reason, null);
        View parent = findViewById(R.id.activity_refund);
        popup = new PopupWindow(popupContent, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setFocusable(true);
        setAlpha(0.4F);
        RelativeLayout root = (RelativeLayout) popupContent.findViewById(R.id.loopView_refund_root);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        root.addView(mLoopView, layoutParams);

        pop_yes = (TextView) popupContent.findViewById(R.id.reason_yes);
        pop_no = (TextView) popupContent.findViewById(R.id.reason_no);
        pop_yes.setOnClickListener(this);
        pop_no.setOnClickListener(this);

        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.showAtLocation(parent, Gravity.BOTTOM | Gravity.LEFT, 0, 0);
        popup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setAlpha(1.0F);
                popup.dismiss();
            }
        });
    }

    private void initPop() {
        mLoopView = new LoopView(this);

        mLoopView.setItems(datas);
        mLoopView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                Log.i("mLoopView", "mLoopView=" + datas.get(index));
            }
        });
        mLoopView.setTextSize(16);
    }

    private void setAlpha(float v) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = v;
        getWindow().setAttributes(lp);
    }

    /**
     * 提交
     */
    private void submit() {
        String tv3 = item3.getText().toString().trim();
        String tv4 = item4.getText().toString().trim();
        String tv5 = item5.getText().toString().trim();

        if (tv3.equals("") || tv4.equals("") || tv5.equals("") || state == 0) {
            Toast.makeText(this, "请填写资料", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "申请成功", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}
