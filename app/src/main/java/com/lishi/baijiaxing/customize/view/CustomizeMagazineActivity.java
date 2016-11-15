package com.lishi.baijiaxing.customize.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.base.MyRecyclerViewViewHoder;
import com.lishi.baijiaxing.customize.adapter.MagazineAdapter;
import com.lishi.baijiaxing.customize.model.MagazineBean;
import com.lishi.baijiaxing.utils.CallPhone;
import com.lishi.baijiaxing.utils.DividerItemDecoration;
import com.lishi.baijiaxing.view.TopNavigationBar;

import java.util.ArrayList;
import java.util.List;

import rx.internal.operators.OnSubscribeFromIterable;

/**
 * 杂志定制
 */
public class CustomizeMagazineActivity extends BaseActivity implements MagazineAdapter.OnMagazineItemClickListener {
    private TopNavigationBar mTopNavigationBar;
    private MagazineAdapter mMagazineAdapter;
    private List<MagazineBean> mMagazineBeans;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_magazine);

        init();

    }

    private void init() {
        findId();
        initData();
        initView();
    }

    private void initData() {
        mMagazineBeans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MagazineBean mb = new MagazineBean("", "", "");
            mMagazineBeans.add(mb);
        }
    }

    private void initView() {
        mTopNavigationBar.setOnTopClick(new TopNavigationBar.OnTopClick() {
            @Override
            public void onTopLeftClick(View view) {
                finish();
            }

            @Override
            public void onTopRightClick(View view) {

            }
        });

        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mMagazineAdapter = new MagazineAdapter(this, mMagazineBeans);
        mRecyclerView.setAdapter(mMagazineAdapter);
        mMagazineAdapter.setOnMagazineItemClickListener(this);
    }

    private void findId() {
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.topbar_customize_magazine);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_magazine);
    }

    @Override
    public void onStartCustomize() {
        Intent startCustomizeActivity = new Intent(this, CustomizeMagazine2Activity.class);
        startActivity(startCustomizeActivity);
    }

    @Override
    public void onSubmitInfo() {
    }

    @Override
    public void onMore() {
        Intent startMagazineAboutUsActivity = new Intent(this, MagazineAboutUsActivity.class);
        startActivity(startMagazineAboutUsActivity);
    }

    @Override
    public void onService() {
        CallPhone.dialPhoneNumber(this,CallPhone.SERVICE);
    }
}
