package com.lishi.baijiaxing.myfree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.free.model.FreeCommodityBean;
import com.lishi.baijiaxing.myfree.adapter.MyFreeAdapter;
import com.lishi.baijiaxing.myfree.model.MyFreeBean;
import com.lishi.baijiaxing.view.TopNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class MyFreeActivity extends BaseActivity {
    private TopNavigationBar mTopNavigationBar;
    private RecyclerView mRecyclerView;
    private List<MyFreeBean> mMyFreeBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_free);

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

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);

        MyFreeAdapter adapter = new MyFreeAdapter(this, mMyFreeBeans);
        mRecyclerView.setAdapter(adapter);
    }

    private void initData() {
        mMyFreeBeans = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            MyFreeBean mfb = new MyFreeBean(FreeCommodityBean.TYPE_BE_BEING_APPLY_OK, "", "【利世优品】万仟堂陶瓷同心 杯带盖过滤办公茶杯水杯包邮", 200);
            mMyFreeBeans.add(mfb);
        }
    }

    private void findId() {
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.topbar_myfree);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_myfree);
    }
}
