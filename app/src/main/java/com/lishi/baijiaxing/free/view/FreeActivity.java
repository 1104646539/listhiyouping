package com.lishi.baijiaxing.free.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.free.adapter.FreeViewPagerAdapter;
import com.lishi.baijiaxing.free.model.FreeBean;
import com.lishi.baijiaxing.free.presenter.FreePresenterImpl;
import com.lishi.baijiaxing.home.widget.Advertisements;
import com.lishi.baijiaxing.view.TopNavigationBar;

import org.json.JSONArray;

import java.util.ArrayList;

public class FreeActivity extends BaseActivity implements FreeView, View.OnClickListener {
    private TopNavigationBar mTopNavigationBar;
    private LinearLayout mLinearLayout;
    private TabLayout mTabLayout;
    private CustomViewPager mViewPager;
    private FreePresenterImpl mFreePresenter;
    private ImageView iv_changeState;
    final ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free);

        initView();
    }

    private void initView() {
        //topBar
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.topbar_free);
        mTopNavigationBar.setOnTopClick(new TopNavigationBar.OnTopClick() {
            @Override
            public void onTopLeftClick(View view) {
                finish();
            }

            @Override
            public void onTopRightClick(View view) {

            }
        });

        //轮播图
        mLinearLayout = (LinearLayout) findViewById(R.id.ll_free);

        mTabLayout = (TabLayout) findViewById(R.id.tabLayout_free);
        mViewPager = (CustomViewPager) findViewById(R.id.viewpager_free);
        iv_changeState = (ImageView) findViewById(R.id.iv_free_changeState);
        iv_changeState.setOnClickListener(this);
        mFreePresenter = new FreePresenterImpl(this);
        mFreePresenter.loadData(0);
    }


    @Override
    public void showDialog() {

    }

    @Override
    public void closeDialog() {

    }

    @Override
    public void loadDataSuccess(FreeBean data) {
        Log.e("loadDataSuccess", "Activity");
        initViewPager(data.getJsonArray());

        initFreeTab();
    }

    /**
     * 免费领tab
     */
    private void initFreeTab() {
        Fragment_Free fragment_free1 = Fragment_Free.newInstance();
        fragments.add(fragment_free1);
        fragment_free1.setType(0);

        Fragment_Free fragment_free2 = Fragment_Free.newInstance();
        fragments.add(fragment_free2);

        Fragment_Free fragment_free3 = Fragment_Free.newInstance();
        fragments.add(fragment_free3);

        FreeViewPagerAdapter freeViewPagerAdapter = new FreeViewPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(freeViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(1);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((Fragment_Free) fragments.get(position)).setType(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 轮播图
     *
     * @param jsonArray
     */
    private void initViewPager(JSONArray jsonArray) {
        mLinearLayout.setEnabled(false);
        mLinearLayout.requestFocus();
        mLinearLayout.addView(new Advertisements(this, true, LayoutInflater.from(this), 2000).initView(jsonArray));
    }

    @Override
    public void loadDataFailed(String error) {
        Log.e("loadDataFailed", "error= " + error);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_free_changeState:
                if (mViewPager != null) {
                    int page = mViewPager.getCurrentItem();
                    changeLayoutManager(page);
                }
                break;
        }
    }

    /**
     * 更换RecyclerView的layoutManager
     *
     * @param page
     */
    private void changeLayoutManager(int page) {
        if (fragments == null) {
            return;
        }
        ((Fragment_Free) fragments.get(page)).setGrid(!((Fragment_Free) fragments.get(page)).isGrid());
    }
}
