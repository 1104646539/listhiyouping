package com.lishi.baijiaxing.customize.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.customize.adapter.CustomizeAdapter;
import com.lishi.baijiaxing.customize.model.CustomizeBean;
import com.lishi.baijiaxing.customize.presenter.CustomizePresenterImpl;
import com.lishi.baijiaxing.view.TopNavigationBar;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.internal.operators.OnSubscribeFromIterable;

public class CustomizeActivity extends BaseActivity implements YiYuanHotAdapter.OnItemClickListener, CustomizeView {
    private CustomizeBean mCustomizeBean;
    private RecyclerView mRecyclerView;
    private TopNavigationBar mTopNavigation;
    private CustomizeAdapter adapter;
    private CustomizePresenterImpl mCustomizePresenterImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);

        if (mCustomizePresenterImpl == null) {
            mCustomizePresenterImpl = new CustomizePresenterImpl(this);
            mCustomizePresenterImpl.loadData();
        }
    }


    private void findId() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_customize);
        mTopNavigation = (TopNavigationBar) findViewById(R.id.topbar_customize);
    }

    @Override
    public void onClickListener(View view, int position) {
        switch (position) {
            case 0:
                break;
            case 1:
                break;
            case 2://杂志定制
                break;
            case 3://相册定制
                break;
            case 4://礼品定制
                Intent startGiftCustomizeActivity = new Intent(this, CustomizeGiftActivity.class);
                startActivity(startGiftCustomizeActivity);
                break;
            case 5://出游定制
                break;
            case 6://50元优惠券
                break;
            default:
                break;
        }
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void closeDialog() {

    }

    private void init() {
        findId();
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        adapter = new CustomizeAdapter(this, mCustomizeBean);
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);

        mTopNavigation.setOnTopClick(new TopNavigationBar.OnTopClick() {
            @Override
            public void onTopLeftClick(View view) {
                finish();
            }

            @Override
            public void onTopRightClick(View view) {

            }
        });
    }

    @Override
    public void loadDataSuccess(CustomizeBean data) {
        mCustomizeBean = data;
        init();
    }

    @Override
    public void loadDataFailed(String error) {

    }
}
