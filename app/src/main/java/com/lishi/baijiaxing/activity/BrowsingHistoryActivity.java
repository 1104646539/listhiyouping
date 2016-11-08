package com.lishi.baijiaxing.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.adapter.BrowsingHistoryAdapter;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.view.MyListView;
import com.lishi.baijiaxing.view.TopNavigationBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 浏览记录
 */
public class BrowsingHistoryActivity extends BaseActivity {
    private MyListView mListView;
    private List<CommodityBean> commodityBeans;
    private TopNavigationBar topbar_browsinghistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_browsing_history);
        initView();

    }

    private void initView() {
        initData();
        mListView = (MyListView) findViewById(R.id.listview_browsinghistory);
        BrowsingHistoryAdapter browsingHistoryAdapter = new BrowsingHistoryAdapter(this, commodityBeans);
        mListView.setAdapter(browsingHistoryAdapter);

        topbar_browsinghistory = (TopNavigationBar) findViewById(R.id.topbar_browsinghistory);

        topbar_browsinghistory.setFocusable(true);
        topbar_browsinghistory.setFocusableInTouchMode(true);
        topbar_browsinghistory.requestFocus();

        topbar_browsinghistory.setOnTopClick(new TopNavigationBar.OnTopClick() {
            @Override
            public void onTopLeftClick(View view) {
                finish();
            }

            @Override
            public void onTopRightClick(View view) {

            }
        });
    }

    /**
     * 模拟数据
     */
    private void initData() {
        commodityBeans = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            CommodityBean cbean = new CommodityBean("", "联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救", "颜色：6代新平台15.6寸6代新平台15.6寸6代新平台15.6寸6代新平台15.6寸", 5200, 1000, 2, false);
            commodityBeans.add(cbean);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mListView = null;
    }
}
