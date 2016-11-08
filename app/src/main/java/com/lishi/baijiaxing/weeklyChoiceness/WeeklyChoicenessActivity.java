package com.lishi.baijiaxing.weeklyChoiceness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.details.CommodityDetailsActivity;
import com.lishi.baijiaxing.free.model.FreeModelImpl;
import com.lishi.baijiaxing.integral.adapter.IntegralAdapter;
import com.lishi.baijiaxing.integral.model.IntegralBean;
import com.lishi.baijiaxing.view.TopNavigationBar;
import com.lishi.baijiaxing.weeklyChoiceness.adapter.WeeklyChoicenessAdapter;
import com.lishi.baijiaxing.weeklyChoiceness.model.WeeklyBean;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.internal.operators.OnSubscribeFromIterable;

/**
 * 每周精选
 */
public class WeeklyChoicenessActivity extends BaseActivity implements YiYuanHotAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private TopNavigationBar mTopNavigationBar;
    private WeeklyChoicenessAdapter adapter;
    private List<WeeklyBean> mWeeklyBeen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_choiceness);

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

        adapter = new WeeklyChoicenessAdapter(this, mWeeklyBeen);
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
    }

    private void initData() {
        mWeeklyBeen = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            WeeklyBean weekly = new WeeklyBean("", "NIVEA控油洗面奶", 201);
            mWeeklyBeen.add(weekly);
        }
    }

    private void findId() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_weekly_choiceness);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.topbar_weekly_choiceness);
    }

    @Override
    public void onClickListener(View view, int position) {
        switch (position) {
            case 0://海报
                break;
            default:
                Intent startDetailsActivity = new Intent(this, CommodityDetailsActivity.class);
                startActivity(startDetailsActivity);
        }
    }
}
