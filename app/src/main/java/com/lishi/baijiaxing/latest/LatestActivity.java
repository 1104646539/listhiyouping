package com.lishi.baijiaxing.latest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.latest.adapter.LatestAdapter;
import com.lishi.baijiaxing.latest.model.LatestBean;
import com.lishi.baijiaxing.latest.model.LatestCommodityBean;
import com.lishi.baijiaxing.view.TopNavigationBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 最新活动
 */
public class LatestActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private TopNavigationBar mTopNavigationBar;
    private LatestBean mLatestBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest);
        init();
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

        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);

        LatestAdapter adapter = new LatestAdapter(this, mLatestBean);
        mRecyclerView.setAdapter(adapter);

    }

    private void initData() {
        List<String> heads = new ArrayList<>();
        heads.add("开门红爆品专区");
        heads.add("开门红爆品专区");
        heads.add("开门红爆品专区");
        List<LatestCommodityBean> latestCommodityBeans = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            LatestCommodityBean lcb = new LatestCommodityBean("花艺暖茶", "", "", "120", "女性专享收御寒热饮");
            latestCommodityBeans.add(lcb);
        }
        mLatestBean = new LatestBean(heads, latestCommodityBeans);
    }

    private void findId() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_latest);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.topbar_latest);
    }
}
