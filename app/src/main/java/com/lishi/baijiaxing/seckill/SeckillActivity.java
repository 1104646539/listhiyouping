package com.lishi.baijiaxing.seckill;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.details.CommodityDetailsActivity;
import com.lishi.baijiaxing.seckill.adapter.SeckilAdapter;
import com.lishi.baijiaxing.seckill.model.SSeckilBean;
import com.lishi.baijiaxing.view.TopNavigationBar;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.iwgang.countdownview.CountdownView;

/**
 * 秒杀专区
 */
public class SeckillActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private TopNavigationBar mTopNavigationBar;
    private List<SSeckilBean> mSeckils;
    private SeckilAdapter adapter;
    private CountdownView mCountdownView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seckill);

        init();
    }

    private void init() {
        findId();
        initData();
        initView();
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
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);

        adapter = new SeckilAdapter(this, mSeckils);
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new YiYuanHotAdapter.OnItemClickListener() {
            @Override
            public void onClickListener(View view, int position) {
                Intent startSeckilDetailsActivity = new Intent(SeckillActivity.this, CommodityDetailsActivity.class);
                startActivity(startSeckilDetailsActivity);
            }
        });
        mCountdownView.start(22111212);

    }

    private void initData() {
        mSeckils = new ArrayList<>();
        SSeckilBean sb;
        for (int i = 0; i < 10; i++) {
            sb = new SSeckilBean("", "", "【利世优品】万仟堂陶瓷同心杯带盖过 滤办公茶杯水杯包邮过滤办公茶杯水...", "200", "300", "100", "50");
            mSeckils.add(sb);
        }


    }

    private void findId() {
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.navigation_seckill);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_seckil);
        mCountdownView = (CountdownView) findViewById(R.id.seckil_countDown);
    }
}
