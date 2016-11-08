package com.lishi.baijiaxing.yiyuan.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.view.TopNavigationBar;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanAdapter;

import java.util.ArrayList;

public class YiYuanActivity extends BaseActivity {
    public static int TYPE_HOT = 0X111;
    public static int TYPE_NEWEST = 0X112;

    private TopNavigationBar mTopNavigationBar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ArrayList<Fragment> mFreagment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yi_yuan);

        initView();
    }

    private void initView() {
        findId();

        initData();
        
    }

    private void initData() {
        mFreagment = new ArrayList<>();
        mFreagment.add(Fragment_YiYuanHot.newInstance());
        mFreagment.add(Fragment_YiYuanNewest.newInstance());

        mViewPager.setAdapter(new YiYuanAdapter(getSupportFragmentManager(), mFreagment, new String[]{"热门抢购", "最新揭晓"}));
        mTabLayout.setupWithViewPager(mViewPager);

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
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.topbar_yiyuan);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout_yiyuan);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_yiyuan);
    }
}
