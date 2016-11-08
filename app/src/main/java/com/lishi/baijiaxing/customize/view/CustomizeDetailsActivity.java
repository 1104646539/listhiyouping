package com.lishi.baijiaxing.customize.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.view.TopNavigationBar;

import java.util.ArrayList;

public class CustomizeDetailsActivity extends BaseActivity {
    //    private RecyclerView mRecyclerView;
//    private CustomizeCommodityBean mCustomizeCommodityBean;
//    private CustomizeDetailsAdapter adapter;
//    private PopupWindow mPopupWindow;
//    private ImageView minus;
//    private ImageView plus;
//    private TextView tv_num;
//    private TextView tv_submit;
//    private TextView config_norms;
//    private TextView uploadLogo;
//    private CustomizeDetailsPresenterImpl mCustomizeDetailsPresenterImpl;
    private TopNavigationBar mTopNavigationBar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Toolbar mToolbar;
    private ArrayList<Fragment> mFragments;
    private ImageView topBar_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_details);

        init();
    }

    private void init() {
        findId();
        initToolbar();

        mFragments = new ArrayList<>();
        mFragments.add(Fragment_CustomizeDetails.newInstance());
        mFragments.add(Fragment_CustomizeComment.newInstance());

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

        topBar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

    private void findId() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager_customize_details);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_customize_details);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout_customize_details);
        topBar_back = (ImageView) findViewById(R.id.back_customizeDetails);
    }
}
