package com.lishi.baijiaxing.customize.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.customize.adapter.CustomizeGiftAdapter;
import com.lishi.baijiaxing.customize.model.CustomizeGiftBean;
import com.lishi.baijiaxing.customize.presenter.CustomizeGiftPresenterImpl;
import com.lishi.baijiaxing.view.TopNavigationBar;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomizeGiftActivity extends BaseActivity implements YiYuanHotAdapter.OnItemClickListener, CustomizeGiftView {
    private RecyclerView mRecyclerView;
    private TopNavigationBar mTopNavigationBar;
    private CustomizeGiftBean mCustomizeGiftBean;
    private CustomizeGiftAdapter adapter;
    private CustomizeGiftPresenterImpl mCustomizeGiftPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_gift);

        if (mCustomizeGiftPresenter == null) {
            mCustomizeGiftPresenter = new CustomizeGiftPresenterImpl(this);
        }
        mCustomizeGiftPresenter.loadData();
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

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);

        adapter = new CustomizeGiftAdapter(this, mCustomizeGiftBean);
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
    }

    private void initData() {

    }

    private void findId() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_customizeGift);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.topbar_customizeGift);
    }

    @Override
    public void onClickListener(View view, int position) {
        switch (position) {
            case 0:
                break;
            case 1:
                break;
            default:
                Intent startCustomizeDetails = new Intent(this, CustomizeDetailsActivity.class);
                startActivity(startCustomizeDetails);
                break;
        }
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void closeDialog() {

    }

    @Override
    public void loadDataSuccess(CustomizeGiftBean data) {
        Toast.makeText(this, "加载成功", Toast.LENGTH_SHORT).show();
        mCustomizeGiftBean = data;
        init();
    }

    @Override
    public void loadDataFailed(String error) {

    }
}
