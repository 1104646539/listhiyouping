package com.lishi.baijiaxing.freeDesign.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.freeDesign.adapter.PageTabAdapter;
import com.lishi.baijiaxing.view.TopNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class ServiceProgressActivity extends AppCompatActivity {
    private TopNavigationBar mTopNavigationBar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_progress);

        init();
    }

    private void init() {
        findId();
        initData();
        initView();
    }

    private void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(Fragment_ProgressQuery.newInstance());
        mFragments.add(Fragment_DialogueHelper.newInstance());
    }

    private void initView() {
        mViewPager.setAdapter(new PageTabAdapter(getSupportFragmentManager(), mFragments, new String[]{"服务进度查询", "对话服务助手"}));
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
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout_serviceProgress);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.topbar_serviceProgress);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_serviceProgress);
    }
}
