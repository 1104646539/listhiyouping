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

import com.google.gson.JsonArray;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.free.adapter.FreeViewPagerAdapter;
import com.lishi.baijiaxing.free.model.FreeAdList;
import com.lishi.baijiaxing.free.model.FreeBean;
import com.lishi.baijiaxing.free.presenter.FreePresenterImpl;
import com.lishi.baijiaxing.home.widget.Advertisements;
import com.lishi.baijiaxing.view.TopNavigationBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

        findId();
        initView();
    }

    private void initView() {
        iv_changeState.setOnClickListener(this);
        if (mFreePresenter == null) {
            mFreePresenter = new FreePresenterImpl(this);
        }
        mFreePresenter.loadData();
        initFreeTab();
    }

    private void findId() {
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
    }


    @Override
    public void showDialog() {

    }

    @Override
    public void closeDialog() {

    }

    @Override
    public void loadDataSuccess(Object data) {

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
        fragment_free2.setType(1);
        Fragment_Free fragment_free3 = Fragment_Free.newInstance();
        fragments.add(fragment_free3);
        fragment_free3.setType(2);
        FreeViewPagerAdapter freeViewPagerAdapter = new FreeViewPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(freeViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(2);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //重要
                mViewPager.resetHeight(position);
                ((Fragment_Free) fragments.get(position)).setType(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 轮播图
     * @param
     */
    private void initViewPager(List<FreeAdList.DataBean> datas) {
        mLinearLayout.setEnabled(false);
        mLinearLayout.requestFocus();
        JSONArray jsonArray = new JSONArray();
        JSONObject head_img;
        int size = datas.size();
        for (int i = 0; i < size; i++) {
            try {
                head_img = new JSONObject();
                head_img.put("head_img", datas.get(i).getPotpUrl());
                jsonArray.put(head_img);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
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

    @Override
    public void loadAdListSuccess(FreeAdList list) {
        Log.e("loadDataSuccess", "Activity");
        initViewPager(list.getData());
    }

    @Override
    public void loadAdListFailed(String error) {

    }

}
