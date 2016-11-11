package com.lishi.baijiaxing.freeDesign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.freeDesign.adapter.FreeDesignAdapter;
import com.lishi.baijiaxing.freeDesign.model.FreeDesignBean;
import com.lishi.baijiaxing.freeDesign.view.ServiceProgressActivity;
import com.lishi.baijiaxing.view.TopNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class FreeDesignActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private TopNavigationBar mTopNavigationBar;
    private int[] srcIds = new int[]{R.drawable.freedesign_item1, R.drawable.freedesign_item2, R.drawable.freedesign_item3, R.drawable.freedesign_item4
            , R.drawable.freedesign_item5, R.drawable.freedesign_item6};
    private List<FreeDesignBean> mFreeDesignBeans;
    /**
     * 报名
     */
    private TextView tv_apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_design);
        init();
    }

    private void init() {
        findId();
        initData();
        initView();
        tv_apply.setOnClickListener(this);
    }

    private void initData() {
        mFreeDesignBeans = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            FreeDesignBean fdb = new FreeDesignBean(srcIds[i], "开门红福袋", "含春联、利是封、剪纸、大福字", "100", "12.6", "8.8", "新春纳福,开门迎好运新春纳福,开门迎好运新春纳福,开门迎好运新春纳福,开门迎好运");
            mFreeDesignBeans.add(fdb);
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
        /**
         * 禁止recyclerView的滑动，解决RecyclerView滑动不顺畅
         */
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRecyclerView.setLayoutManager(manager);

        FreeDesignAdapter adapter = new FreeDesignAdapter(this, mFreeDesignBeans);
        mRecyclerView.setAdapter(adapter);

    }

    private void findId() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_freeDesign);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.topbar_freeDesign);
        tv_apply = (TextView) findViewById(R.id.free_design_apply);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.free_design_apply:
                Intent startServiceProgressActivity = new Intent(this, ServiceProgressActivity.class);
                startActivity(startServiceProgressActivity);
                break;
        }
    }
}
