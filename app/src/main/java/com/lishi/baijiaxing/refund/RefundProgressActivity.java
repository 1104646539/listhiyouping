package com.lishi.baijiaxing.refund;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.refund.adapter.RefundProgressAdapter;
import com.lishi.baijiaxing.refund.model.RefundProgressBean;
import com.lishi.baijiaxing.view.TopNavigationBar;

public class RefundProgressActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private TopNavigationBar mTopNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_progress);

        init();
    }

    private void init() {
        findId();
        initData();
        initView();
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);

        RefundProgressAdapter adapter = new RefundProgressAdapter(this, new RefundProgressBean(RefundProgressBean.TYPE_DISPOSE_ING));
        mRecyclerView.setAdapter(adapter);

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

    private void initData() {
    }

    private void findId() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_refundProgress);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.navigation_refundProgress);
    }
}
