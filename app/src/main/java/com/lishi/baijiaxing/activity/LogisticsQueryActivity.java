package com.lishi.baijiaxing.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.adapter.LogisticsQueryAdapter;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.bean.LogisticsBean;
import com.lishi.baijiaxing.bean.LogisticsStateBean;
import com.lishi.baijiaxing.view.MyListView;
import com.lishi.baijiaxing.view.TopNavigationBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 物流查询
 */
public class LogisticsQueryActivity extends BaseActivity {
    private MyListView mMyListView;
    private List<LogisticsBean> mLogisticsBeans;
    private TopNavigationBar mTopNavigationBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_logistics_query);
        
        findId();
        initView();
    }

    private void findId() {
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.navigation_logisticsquery);
        mMyListView = (MyListView) findViewById(R.id.listview_logisticsquery);
    }

    private void initView() {
        initData();
    
        LogisticsQueryAdapter adapter = new LogisticsQueryAdapter(this, mLogisticsBeans);
        mMyListView.setAdapter(adapter);
        mTopNavigationBar.setOnTopClick(new TopNavigationBar.OnTopClick() {
            @Override
            public void onTopLeftClick(View view) {
                finish();
            }

            @Override
            public void onTopRightClick(View view) {

            }
        });
    }

    private void initData() {
        mLogisticsBeans = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            CommodityBean cbean = new CommodityBean("", "联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救", "颜色：6代新平台15.6寸6代新平台15.6寸6代新平台15.6寸6代新平台15.6寸", 5200 + i, 1000, 2, false);
            LogisticsBean logisticsBean = new LogisticsBean(111, cbean, LogisticsStateBean.Query);
            mLogisticsBeans.add(logisticsBean);
        }
        for (int i = 0; i < 2; i++) {
            CommodityBean cbean = new CommodityBean("", "联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救", "颜色：6代新平台15.6寸6代新平台15.6寸6代新平台15.6寸6代新平台15.6寸", 5200 + i, 1000, 2, false);
            LogisticsBean logisticsBean = new LogisticsBean(111, cbean, LogisticsStateBean.CONFIRM);
            mLogisticsBeans.add(logisticsBean);
        }
        for (int i = 0; i < 2; i++) {
            CommodityBean cbean = new CommodityBean("", "联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救", "颜色：6代新平台15.6寸6代新平台15.6寸6代新平台15.6寸6代新平台15.6寸", 5200 + i, 1000, 2, false);
            LogisticsBean logisticsBean = new LogisticsBean(111, cbean, LogisticsStateBean.WAIT);
            mLogisticsBeans.add(logisticsBean);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMyListView = null;
        mLogisticsBeans.clear();
        mLogisticsBeans = null;
    }
}
