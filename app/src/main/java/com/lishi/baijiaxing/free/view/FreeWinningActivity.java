package com.lishi.baijiaxing.free.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.free.adapter.FreeWinningAdapter;
import com.lishi.baijiaxing.free.model.FreeWinningBean;
import com.lishi.baijiaxing.utils.DividerItemDecoration;
import com.lishi.baijiaxing.view.TopNavigationBar;

import java.util.ArrayList;

public class FreeWinningActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private TopNavigationBar mTopNavigationBar;
    private ArrayList<FreeWinningBean> freeWinningBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_winning);

        initView();
    }

    private void initView() {
        findId();
        mTopNavigationBar.setOnTopClick(new TopNavigationBar.OnTopClick() {
            @Override
            public void onTopLeftClick(View view) {
                finish();
            }

            @Override
            public void onTopRightClick(View view) {

            }
        });
        initData();
        LinearLayoutManager maganer = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(maganer);


        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        FreeWinningAdapter adapter = new FreeWinningAdapter(this, freeWinningBeans);
        mRecyclerView.setAdapter(adapter);

    }

    private void initData() {
        freeWinningBeans = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            FreeWinningBean freeWinningBean = new FreeWinningBean("150****1212", "中国台湾省高雄区", "");
            freeWinningBeans.add(freeWinningBean);
        }
    }

    private void findId() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_free_winning);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.topbar_free_winning);
    }
}
