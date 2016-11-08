package com.lishi.baijiaxing.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.adapter.CouponsAdapter;
import com.lishi.baijiaxing.bean.CouponsBean;
import com.lishi.baijiaxing.submitOrder.view.SubmitOrderActivity;
import com.lishi.baijiaxing.view.MyGridView;
import com.lishi.baijiaxing.view.TopNavigationBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 优惠券
 */
public class CouponsActivity extends Activity {
    private MyGridView mMyGridView;
    private List<CouponsBean> mCouponsBean;
    private TopNavigationBar mTopNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_coupons);
        FindId();
        initView();
    }

    private void FindId() {
        mMyGridView = (MyGridView) findViewById(R.id.gridview_coupons);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.navigation_coupons);
    }

    private void initView() {
        initData();

        CouponsAdapter adapter = new CouponsAdapter(this, mCouponsBean);
        mMyGridView.setAdapter(adapter);
        mTopNavigationBar.setOnTopClick(new TopNavigationBar.OnTopClick() {
            @Override
            public void onTopLeftClick(View view) {
                finish();
            }

            @Override
            public void onTopRightClick(View view) {

            }
        });
        //选择优惠券
        mMyGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CouponsBean c = mCouponsBean.get(position);
                if (c.isAvailable()) {
                    Intent intent = new Intent(CouponsActivity.this, SubmitOrderActivity.class);
                    setResult(RESULT_OK, intent.putExtra("coupons", c));
                    finish();
                }
            }
        });
    }

    private void initData() {
        mCouponsBean = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            CouponsBean c = new CouponsBean("2016-9-8", 50, "全平台可用", 50, true);
            if (i == 4 || i == 5) {
                c.setAvailable(false);
            }
            if (i == 2){
                c.setMoney(100);
            }
            mCouponsBean.add(c);
        }
    }
}
