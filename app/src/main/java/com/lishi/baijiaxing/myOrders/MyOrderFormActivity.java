package com.lishi.baijiaxing.myOrders;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.myOrders.view.Fragment_ReturnedGoods;
import com.lishi.baijiaxing.myOrders.view.Fragment_StayEvaluate;
import com.lishi.baijiaxing.myOrders.view.Fragment_StayPayment;
import com.lishi.baijiaxing.myOrders.view.Fragment_StayTakeGoods;
import com.lishi.baijiaxing.personal.adapter.MyOrderFormFragmentAdapter;
import com.lishi.baijiaxing.view.TopNavigationBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的订单
 */
public class MyOrderFormActivity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private MyOrderFormFragmentAdapter mAdapter;
    private List<Fragment> mFragments;
    private int page = 0;
    private TopNavigationBar mTopNavigationBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_form);

        findId();
        initView();
    }

    private void initView() {
        initData();
        mAdapter = new MyOrderFormFragmentAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mAdapter);
        mTablayout.setupWithViewPager(mViewPager);

        mViewPager.setCurrentItem(page);

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
        mFragments = new ArrayList<>();
        mFragments.add(Fragment_StayPayment.newInstantiation());
        mFragments.add(Fragment_StayTakeGoods.newInstantiation());
        mFragments.add(Fragment_StayEvaluate.newInstantiation());
        mFragments.add(Fragment_ReturnedGoods.newInstantiation());

        page = getIntent().getIntExtra("page", 0);
    }

    private void findId() {
        mTablayout = (TabLayout) findViewById(R.id.tablayout_myorderform);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_myorderform);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.topbar_myOrder);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}
