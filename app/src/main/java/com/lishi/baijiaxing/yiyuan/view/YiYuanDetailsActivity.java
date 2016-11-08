package com.lishi.baijiaxing.yiyuan.view;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.free.view.Fragment_FreeDetails_Comment;
import com.lishi.baijiaxing.free.view.Fragment_FreeDetails_Details;

import java.util.ArrayList;

public class YiYuanDetailsActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ArrayList<Fragment> mFragments;
    private ImageView topBar_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yi_yuan_details);

        findId();
        setSupportActionBar(mToolbar);
        initView();
    }

    private void initView() {
        initToolbar();
        initViewPager();
        topBar_back.setOnClickListener(this);
    }

    private void initViewPager() {
        mFragments = new ArrayList<>();
        mFragments.add(Fragment_YiYuanDetails.newInstance());
        mFragments.add(Fragment_YiYuanComment.newInstance());

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void findId() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_yiyuan_details);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_yiyuan_details);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout_yiyuan_details);
        topBar_back = (ImageView) findViewById(R.id.back_yiyuanDetails);
    }

    private void initToolbar() {
        setTitle("");

        mTabLayout.addTab(mTabLayout.newTab().setCustomView(R.layout.free_tablayout_title));
        final TextView tvTitle1 = (TextView) mTabLayout.findViewById(R.id.tv_free_details_title);
        tvTitle1.setText("详情");
        tvTitle1.setTextColor(Color.BLACK);
        tvTitle1.setTextSize(16);

        mTabLayout.addTab(mTabLayout.newTab().setCustomView(R.layout.free_tablayout_title2));
        final TextView tvTitle2 = (TextView) mTabLayout.findViewById(R.id.tv_free_details_title2);
        tvTitle2.setTextColor(Color.BLACK);
        tvTitle2.setText("评论");
        tvTitle2.setTextSize(14);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    tvTitle1.setTextSize(16);
                    tvTitle2.setTextSize(14);
                } else if (tab.getPosition() == 1) {
                    tvTitle1.setTextSize(14);
                    tvTitle2.setTextSize(16);
                }
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_yiyuanDetails:
                finish();
                break;
        }
    }
}
