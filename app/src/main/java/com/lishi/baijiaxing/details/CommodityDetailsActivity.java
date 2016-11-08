package com.lishi.baijiaxing.details;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.details.adapter.CommodityDetailsAdapter;
import com.lishi.baijiaxing.details.view.Fragment_Commodity_Brief;
import com.lishi.baijiaxing.details.view.Fragment_Commodity_Comment;
import com.lishi.baijiaxing.details.view.Fragment_Commodity_Details;

import java.util.ArrayList;
import java.util.List;

/**
 * 普通商品详情
 */
public class CommodityDetailsActivity extends BaseActivity {
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private TextView title1, title2, title3;
    private List<Fragment> mFragments;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_details2);
        init();

    }

    private void init() {
        findId();
        initData();
        initTabLayout();
        initViewPager();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViewPager() {
        mFragments = new ArrayList<>();
        mFragments.add(Fragment_Commodity_Details.newInstance());
        mFragments.add(Fragment_Commodity_Brief.newInstance());
        mFragments.add(Fragment_Commodity_Comment.newInstance());

        CommodityDetailsAdapter adapter = new CommodityDetailsAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(adapter);

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

    private void initTabLayout() {
        setTitle("");

        mTabLayout.addTab(mTabLayout.newTab().setCustomView(R.layout.free_tablayout_title));
        title1 = (TextView) mTabLayout.findViewById(R.id.tv_free_details_title);
        title1.setText("商品");
        title1.setTextSize(16);

        mTabLayout.addTab(mTabLayout.newTab().setCustomView(R.layout.free_tablayout_title2));
        title2 = (TextView) mTabLayout.findViewById(R.id.tv_free_details_title2);
        title2.setText("详情");
        title2.setTextSize(14);

        mTabLayout.addTab(mTabLayout.newTab().setCustomView(R.layout.free_tablayout_title3));
        title3 = (TextView) mTabLayout.findViewById(R.id.tv_free_details_title3);
        title3.setText("评论");
        title3.setTextSize(14);

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    title1.setTextSize(16);
                    title2.setTextSize(14);
                    title3.setTextSize(14);
                } else if (tab.getPosition() == 1) {
                    title1.setTextSize(14);
                    title2.setTextSize(16);
                    title3.setTextSize(14);
                } else if (tab.getPosition() == 2) {
                    title1.setTextSize(14);
                    title2.setTextSize(14);
                    title3.setTextSize(16);
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

    private void initData() {
    }

    private void findId() {
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout_details);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_details);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_details);
        back = (ImageView) findViewById(R.id.back_details);
    }
}
