package com.lishi.baijiaxing.myCollect;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.adapter.MyCollectAdapter;
import com.lishi.baijiaxing.fragment.Fragment_MyCollect_Commodity;
import com.lishi.baijiaxing.fragment.Fragment_MyCollect_Store;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的收藏
 */
public class MyCollectActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> mFragments;
    private TextView tv_edit;
    private Fragment_MyCollect_Commodity collect_commodity;
    private Fragment_MyCollect_Store collect_store;
    public boolean is_edit = false;//是否是编辑状态
    private ImageView back_mycollect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_collect);

        initView();
    }

    private void initView() {
        initData();

        mTabLayout = (TabLayout) findViewById(R.id.tablayout_mycollect);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_mycollect);
        tv_edit = (TextView) findViewById(R.id.tv_mycollect_edit);
        back_mycollect = (ImageView) findViewById(R.id.back_mycollect);

        MyCollectAdapter adapter = new MyCollectAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeAllChecked();
                if (!is_edit) {//不是编辑状态
                    tv_edit.setText("完成");
                    is_edit = true;
                    if (mViewPager.getCurrentItem() == 0) {
                        collect_commodity.CheckChange(true);

                    } else if (mViewPager.getCurrentItem() == 1) {
                        collect_store.CheckChange(true);
                    }
                } else {
                    tv_edit.setText("编辑");
                    is_edit = false;
                    if (mViewPager.getCurrentItem() == 0) {
                        collect_commodity.CheckChange(false);
                    } else {
                        collect_store.CheckChange(false);
                    }
                }

            }
        });
        back_mycollect.setClickable(true);
        back_mycollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    /**
     * 取消所有选择状态
     */
    private void ChangeAllChecked() {
        if (mViewPager.getCurrentItem() == 0) {
            collect_commodity.ChangeAll();
        } else if (mViewPager.getCurrentItem() == 1) {
            collect_store.ChangeAll();
        }

    }

    private void initData() {
        mFragments = new ArrayList<>();
        collect_commodity = new Fragment_MyCollect_Commodity();
        mFragments.add(collect_commodity);
        collect_store = new Fragment_MyCollect_Store();
        mFragments.add(collect_store);
    }

    /**
     * 取消编辑状态
     */
    public void cancelEdit() {
        is_edit = false;
        tv_edit.setText("编辑");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTabLayout = null;
        mViewPager = null;

        collect_store = null;
        collect_commodity = null;
    }
}
