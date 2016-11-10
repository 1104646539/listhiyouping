package com.lishi.baijiaxing.shoppingCart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.activity.MainActivity;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.bean.HomeRecommendBean;
import com.lishi.baijiaxing.home.adater.GridRecommendAdapter;
import com.lishi.baijiaxing.shoppingCart.adapter.ShoppingCartAdapter;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.shoppingCart.model.ShoppingBean;
import com.lishi.baijiaxing.shoppingCart.model.StoreBean;
import com.lishi.baijiaxing.shoppingCart.presenter.ShoppingCartPresenterImpl;
import com.lishi.baijiaxing.shoppingCart.view.ShoppingCartView;
import com.lishi.baijiaxing.submitOrder.view.SubmitOrderActivity;
import com.lishi.baijiaxing.utils.ShoppingBadgeUtil;
import com.lishi.baijiaxing.view.MyGridView;

import java.util.ArrayList;
import java.util.Iterator;

public class ShoppingCartActivity extends BaseActivity implements View.OnClickListener, ShoppingCartView, ShoppingCartAdapter.OnItemClick {


    private ArrayList<StoreBean> mStoreBeens = new ArrayList<StoreBean>();//购物车数据
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView tv_cart_totalprice;//总价
    private CheckBox check_cartall, check_cartall_edit;//全选按钮
    private TextView tv_edit;//编辑
    private boolean isEdit = false;//是否在编辑状态
    private LinearLayout ll_count, ll_edit;
    private MyGridView mGridView;
    private GridRecommendAdapter mGridRecommendAdapter;
    private ArrayList<HomeRecommendBean> mHomeRecommends = new ArrayList<HomeRecommendBean>();
    private ArrayList<CommodityBean> mCommodityBeens = new ArrayList<>();
    private boolean isLoading = false;
    private ScrollView mScroll;
    private TextView tv_load;
    private LinearLayout ll_gridview;
    private LinearLayout ll_footer, ll_root;
    private Button btn_delete;
    private TextView tv_count;
    private ShoppingCartPresenterImpl mShoppingCartPresenterImpl;
    private boolean isPrepare;

    private RecyclerView mRecyclerView;
    private ShoppingCartAdapter adapter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 001) {
                mSwipeRefreshLayout.setRefreshing(false);
                mShoppingCartPresenterImpl.loadData();
            } else if (msg.what == 2) {
                mShoppingCartPresenterImpl.pullLoad(mHomeRecommends);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        findId();
        initView();
    }

    private void findId() {
        tv_count = (TextView) findViewById(R.id.tv_cart_count);
        tv_cart_totalprice = (TextView) findViewById(R.id.tv_cartbom_num);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.cart_swipeRefreshLayout);
        check_cartall = (CheckBox) findViewById(R.id.checkbox_cart_bottom_all);
        tv_edit = (TextView) findViewById(R.id.tv_cart_topnavigation_edit);
        ll_count = (LinearLayout) findViewById(R.id.ll_cart_count);
        ll_edit = (LinearLayout) findViewById(R.id.ll_cart_edit);
        check_cartall_edit = (CheckBox) findViewById(R.id.checkbox_cart_all_edit);
        mGridView = (MyGridView) findViewById(R.id.gridview_cart);
        mScroll = (ScrollView) findViewById(R.id.scroll_cart);
        tv_load = (TextView) findViewById(R.id.tv_cart_gridview_load);
        ll_gridview = (LinearLayout) findViewById(R.id.ll_cart_gridview);
        ll_footer = (LinearLayout) findViewById(R.id.ll_cart_footer);
        ll_root = (LinearLayout) findViewById(R.id.ll_cart_gridview_root);
        btn_delete = (Button) findViewById(R.id.btn_cart_delete);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_shoppingcart);

        check_cartall.setOnClickListener(this);
        tv_edit.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        tv_count.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        tv_cart_totalprice.setText("0.0");
        if (mStoreBeens != null) {
            ShoppingBadgeUtil.getInstance().setBadgeCount(mCommodityBeens.size());
            if (ShoppingBadgeUtil.getInstance().getBadgeCount() != 0) {
                MainActivity.mBadgeView.setBadgeCount(ShoppingBadgeUtil.getInstance().getBadgeCount());
            }
        }
    }

    private void initView() {
        if (mShoppingCartPresenterImpl == null) {
            mShoppingCartPresenterImpl = new ShoppingCartPresenterImpl(this);
        }
        mShoppingCartPresenterImpl.loadData();//开始加载数据
    }


    /**
     * 计算总价
     */
    private void countTotalPrice() {
        float totalPrice = 0;
        int size = mCommodityBeens.size();
        for (int i = 0; i < size; i++) {
            if (mCommodityBeens.get(i).isChecked()) {
                totalPrice += mCommodityBeens.get(i).getCommNum() * mCommodityBeens.get(i).getCommPrice();
            }
        }
        tv_cart_totalprice.setText(totalPrice + "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cart_topnavigation_edit://编辑
                if (!isEdit) {
                    tv_edit.setText("完成");
                    isEdit = true;
                    ll_edit.setVisibility(View.VISIBLE);
                    ll_count.setVisibility(View.INVISIBLE);
                    check_cartall.setChecked(false);
                    tv_cart_totalprice.setText("0.0");
                    ll_root.setVisibility(View.GONE);
                } else {
                    tv_edit.setText("编辑");
                    isEdit = false;
                    ll_edit.setVisibility(View.INVISIBLE);
                    ll_count.setVisibility(View.VISIBLE);
                    check_cartall_edit.setChecked(false);
                    ll_root.setVisibility(View.VISIBLE);
                    tv_cart_totalprice.setText("0.0");
                }
                allChecked(false);
                break;
            case R.id.btn_cart_delete://删除
                delete();
                if (mStoreBeens != null) {
                    ShoppingBadgeUtil.getInstance().setBadgeCount(mCommodityBeens.size());
                    if (ShoppingBadgeUtil.getInstance().getBadgeCount() != 0) {
                        MainActivity.mBadgeView.setBadgeCount(ShoppingBadgeUtil.getInstance().getBadgeCount());
                    }
                }
                break;
            case R.id.tv_cart_count://去结算
                startSubmitOrderActivity();
                break;
            case R.id.checkbox_cart_bottom_all://全选、取消全选
                if (check_cartall.isChecked()) {
                    allChecked(true);
                } else {
                    allChecked(false);
                }
                break;

        }
    }

    /**
     * 启动确认订单页面
     *
     * @param
     * @param
     */
    private void startSubmitOrderActivity() {
        ArrayList<CommodityBean> submitOrderData = new ArrayList<>();
        for (int i = 0; i < mCommodityBeens.size(); i++) {
            if (mCommodityBeens.get(i).isChecked()) {
                submitOrderData.add(mCommodityBeens.get(i));
            }
        }
        if (submitOrderData.size() != 0) {
            Intent startSubmitOrderActivity = new Intent(this, SubmitOrderActivity.class);
            startSubmitOrderActivity.putExtra("list", submitOrderData);
            startActivity(startSubmitOrderActivity);
        }
    }

    /**
     * 全选/取消全选
     */
    private void allChecked(boolean isChecked) {
        for (int i = 0; i < mCommodityBeens.size(); i++) {
            mCommodityBeens.get(i).setChecked(isChecked);
        }
        adapter.notifyDataSetChanged();
        countTotalPrice();
        Log.e("allChecked", "allChecked=" + isChecked);
    }

    /**
     * 删除
     */
    private void delete() {
        Iterator<CommodityBean> cit = mCommodityBeens.listIterator();
        while (cit.hasNext()) {
            CommodityBean c = cit.next();
            if (c.isChecked()) {
                cit.remove();
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAddDataSuccess(ArrayList<CommodityBean> commodityBeen) {

    }

    @Override
    public void onAddDataFailed(String error) {

    }

    @Override
    public void onPullLoadDataSuccess(ArrayList<HomeRecommendBean> homeRecommend) {
        this.mHomeRecommends = homeRecommend;
        mGridRecommendAdapter.notifyDataSetChanged();
        tv_load.setText("上拉加载更多");
        isLoading = false;
    }

    @Override
    public void onPullLoadDataFailed(String error) {
    }

    @Override
    public void onDeleteDataSuccess(ArrayList<CommodityBean> commodityBeen) {

    }


    @Override
    public void onDeleteDataFailed(String error) {
        Log.d("onDeleteDataFailed", "onDeleteDataFailed+  " + error);
    }

    @Override
    public void onChangeStoreSuccess(ArrayList<CommodityBean> commodityBeen) {

    }

    @Override
    public void onChangeStoreFailed(String error) {
        Log.d("onChangeStoreFailed", "onChangeStoreFailed+  " + error);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void closeDialog() {

    }

    @Override
    public void loadDataSuccess(ShoppingBean data) {
        mHomeRecommends = data.getHomeRecommendBeen();
        mCommodityBeens = data.getCommodityBeen();

        mGridRecommendAdapter = new GridRecommendAdapter(this, mHomeRecommends);
        mGridView.setAdapter(mGridRecommendAdapter);

        adapter = new ShoppingCartAdapter(this, data.getCommodityBeen());
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClick(this);

        countTotalPrice();
    }

    @Override
    public void loadDataFailed(String error) {
    }

    @Override
    public void onCommodityMinus(View v, int position) {
        int num = mCommodityBeens.get(position).getCommNum();
        if (num > 1) {
            mCommodityBeens.get(position).setCommNum(--num);
            adapter.notifyItemChanged(position);
        }
        countTotalPrice();
    }

    @Override
    public void onCommodityPlus(View v, int position) {
        int num = mCommodityBeens.get(position).getCommNum();
        mCommodityBeens.get(position).setCommNum(++num);
        adapter.notifyItemChanged(position);
        countTotalPrice();
    }

    @Override
    public void onCommodityCheck(boolean isChecked, int position) {
        mCommodityBeens.get(position).setChecked(isChecked);
        Log.e("onCommodityCheck", "isChecked=" + isChecked + position);
//        adapter.notifyItemChanged(position);
        countTotalPrice();
        countIsAllChecked(isChecked);
    }

    /**
     * 计算是否触发全选
     *
     * @param isChecked
     */
    private void countIsAllChecked(boolean isChecked) {
        int max = 0;
        for (int i = 0; i < mCommodityBeens.size(); i++) {
            if (mCommodityBeens.get(i).isChecked() == isChecked) {
                max++;
            }
            Log.e("mCommodityBeens", "mCommodityBeens=" + i + mCommodityBeens.get(i).isChecked() + isChecked);
        }
        if (max == mCommodityBeens.size()) {
            allChecked(isChecked);
            if (!isEdit) {
                check_cartall.setChecked(isChecked);
            } else {
                check_cartall_edit.setChecked(isChecked);
            }
        }
        if (max < mCommodityBeens.size()) {
            if (!isEdit) {
                check_cartall.setChecked(false);
            } else {
                check_cartall_edit.setChecked(false);
            }
        }
    }

}
