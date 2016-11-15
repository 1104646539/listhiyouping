package com.lishi.baijiaxing.customize.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.customize.adapter.MagazineAboutUsAdapter;
import com.lishi.baijiaxing.view.TopNavigationBar;

/**
 * 杂志定制->关于我们
 */
public class MagazineAboutUsActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private TopNavigationBar mTopNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magazine_about_us);

        init();
    }

    private void init() {
        findId();
        initView();
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);

        MagazineAboutUsAdapter adapter = new MagazineAboutUsAdapter(this);
        mRecyclerView.setAdapter(adapter);

        mTopNavigationBar.setOnTopClick(new TopNavigationBar.OnTopClick() {
            @Override
            public void onTopLeftClick(View view) {
                finish();
            }

            @Override
            public void onTopRightClick(View view) {

            }
        });
        adapter.setOnCustomizeClickListener(new MagazineAboutUsAdapter.OnCustomizeClickListener() {
            @Override
            public void onCustomizeClickListener() {
                Intent startCustomizeMagazine2Activity = new Intent(MagazineAboutUsActivity.this, CustomizeMagazine2Activity.class);
                startActivity(startCustomizeMagazine2Activity);
            }
        });
    }

    private void findId() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_magazineAboutUs);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.topbar_customize_magazineAboutUs);
    }
}
