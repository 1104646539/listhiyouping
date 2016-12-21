package com.lishi.baijiaxing.myOrders.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.widget.SpringView;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.myOrders.adapter.OrderFormAdapter;
import com.lishi.baijiaxing.myOrders.model.MyOrderList;
import com.lishi.baijiaxing.myOrders.presenter.AllOrderPresenterImpl;
import com.lishi.baijiaxing.shoppingCart.model.StoreBean;
import com.lishi.baijiaxing.utils.ProgressBarUtil;
import com.lishi.baijiaxing.view.MyDefaultFooter;
import com.lishi.baijiaxing.view.MyListView;
import com.lishi.baijiaxing.view.TopNavigationBar;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 全部订单
 */
public class AllOrderActivity extends BaseActivity implements AllOrderView, SpringView.OnFreshListener {
    private ListView mListView;
    private TopNavigationBar mTopNavigationBar;
    private AllOrderPresenterImpl mAllOrderPresenter;
    private LinearLayout order_null;
    private List<MyOrderList.DataBean.OrderListBean> mDataBeens = new ArrayList<>();
    private ProgressBarUtil mProgressBarUtil;
    private OrderFormAdapter adapter;
    private boolean isLoadMore = false;
    private SpringView springView_allorder;
    private int STAY_All = 0X008;
    private MyDefaultFooter defaultFooter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_order);
        init();
    }

    private void init() {
        findId();
        initView(2);
    }

    private void initView(int page) {
        mTopNavigationBar.setOnTopClick(new TopNavigationBar.OnTopClick() {
            @Override
            public void onTopLeftClick(View view) {
                finish();
            }

            @Override
            public void onTopRightClick(View view) {

            }
        });
        if (mAllOrderPresenter == null) {
            mAllOrderPresenter = new AllOrderPresenterImpl(this);
        }
        mAllOrderPresenter.loadOrderList(page);
    }

    private void findId() {
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.navigation_allOrder);
        mListView = (ListView) findViewById(R.id.listView_allOrder);
        order_null = (LinearLayout) findViewById(R.id.allOrder_order_null);
        springView_allorder = (SpringView) findViewById(R.id.springView_order_allOrder);

    }

    @Override
    public void loadData() {

    }

    @Override
    public void loadOrderListSuccess(List<MyOrderList.DataBean.OrderListBean> dataBeen) {
        this.mDataBeens.clear();
        this.mDataBeens.addAll(dataBeen);
        if (mDataBeens == null || mDataBeens.size() == 0) {
            order_null.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.GONE);
        } else {
            order_null.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            if (isLoadMore) {
                isLoadMore = false;
                springView_allorder.onFinishFreshAndLoad();
                adapter.notifyDataSetChanged();
                return;
            }
            adapter = new OrderFormAdapter(this, mDataBeens);
            mListView.setAdapter(adapter);
            defaultFooter =  new MyDefaultFooter(this);
            springView_allorder.setFooter(defaultFooter);
            springView_allorder.setListener(this);
            springView_allorder.setType(SpringView.Type.FOLLOW);
            adapter.setOnItemClickListener(new OrderFormAdapter.OnListItemClickListener() {
                @Override
                public void onListItemClickListener(View v, String oid, int state, int position) {
                    Intent startOrderDetails = new Intent(AllOrderActivity.this, OrderDetailsActivity.class);
                    startOrderDetails.putExtra("oid", oid);
                    startActivityForResult(startOrderDetails, STAY_All);
                }
            });

        }
    }

    @Override
    public void loadOrderListFailed(String error) {
        Logger.d(error);
    }

    @Override
    public void onLastPage(String status) {
        defaultFooter.setMoreLoad(false);
    }

    @Override
    public void showDialog() {
        if (mProgressBarUtil == null) {
            mProgressBarUtil = new ProgressBarUtil(this);
        }
        mProgressBarUtil.show();
    }

    @Override
    public void closeDialog() {
        if (mProgressBarUtil != null) {
            mProgressBarUtil.dismiss();
        }

    }

    @Override
    public void loadDataSuccess(Object data) {

    }

    @Override
    public void loadDataFailed(String error) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadmore() {
        isLoadMore = true;
        initView(-1);
    }

}
