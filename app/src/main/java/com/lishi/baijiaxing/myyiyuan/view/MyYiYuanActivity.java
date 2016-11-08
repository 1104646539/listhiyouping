package com.lishi.baijiaxing.myyiyuan.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.myyiyuan.adapter.MyYiYuanAdapter;
import com.lishi.baijiaxing.myyiyuan.model.MyYiYuanBean;
import com.lishi.baijiaxing.myyiyuan.presenter.MyYiYuanPresenterImpl;
import com.lishi.baijiaxing.view.TopNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class MyYiYuanActivity extends BaseActivity implements MyYiYuanView {
    private TopNavigationBar mTopNavigationBar;
    private RecyclerView mRecyclerView;
    private List<MyYiYuanBean> mYiYuanBeans;
    private MyYiYuanPresenterImpl mMyYiYuanPresenterImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_yi_yuan);

        if (mMyYiYuanPresenterImpl == null) {
            mMyYiYuanPresenterImpl = new MyYiYuanPresenterImpl(this);
        }
        mMyYiYuanPresenterImpl.loadData();
    }

    private void init() {
        findId();
        initDada();
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

        MyYiYuanAdapter adapter = new MyYiYuanAdapter(this, mYiYuanBeans);
        mRecyclerView.setAdapter(adapter);
    }

    private void initDada() {
//        mYiYuanBeans = new ArrayList<>();
//        for (int i = 0; i < 2; i++) {
//            MyYiYuanBean myYiYuan = new MyYiYuanBean(200, "", "【利世优品】万仟堂陶瓷同心杯 带盖过滤办公茶杯水杯包邮", 250, 30, 1);
//            mYiYuanBeans.add(myYiYuan);
//        }
    }

    private void findId() {
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.topbar_myyiyuan);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_myyiyuan);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void closeDialog() {

    }

    @Override
    public void loadDataSuccess(ArrayList<MyYiYuanBean> data) {
        mYiYuanBeans = data;
        init();
    }

    @Override
    public void loadDataFailed(String error) {

    }
}
