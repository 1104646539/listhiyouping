package com.lishi.baijiaxing.integral;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.details.CommodityDetailsActivity;
import com.lishi.baijiaxing.integral.adapter.IntegralAdapter;
import com.lishi.baijiaxing.integral.model.IntegralBean;
import com.lishi.baijiaxing.view.TopNavigationBar;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 积分兑换
 */
public class IntegralActivity extends BaseActivity implements YiYuanHotAdapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private TopNavigationBar mTopNavigationBar;
    private IntegralAdapter adapter;
    private List<IntegralBean> mIntegralBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral);

        init();


    }

    private void init() {
        findId();
        initData();
        mTopNavigationBar.setOnTopClick(new TopNavigationBar.OnTopClick() {
            @Override
            public void onTopLeftClick(View view) {
                finish();
            }

            @Override
            public void onTopRightClick(View view) {

            }
        });

        adapter = new IntegralAdapter(this, mIntegralBeans);
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
    }

    private void initData() {
        mIntegralBeans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            IntegralBean integral = new IntegralBean("", "万仟堂 陶瓷同心杯" + i, 230);
            mIntegralBeans.add(integral);
        }
    }

    private void findId() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_integral);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.topbar_integral);
    }

    @Override
    public void onClickListener(View view, int position) {
        switch (position) {
            case 0://海报
                break;
            default:
                Intent startDetailsActivity = new Intent(this, CommodityDetailsActivity.class);
                startActivity(startDetailsActivity);
        }
    }
}
