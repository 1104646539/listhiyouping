package com.lishi.baijiaxing.myOrders.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.widget.SpringView;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseFragmentV4;
import com.lishi.baijiaxing.myOrders.adapter.OrderFormAdapter;
import com.lishi.baijiaxing.myOrders.model.MyOrderList;
import com.lishi.baijiaxing.myOrders.presenter.ReturnedGoodsPresenterImpl;
import com.lishi.baijiaxing.myOrders.presenter.StayTakeGoodsPresenterImpl;
import com.lishi.baijiaxing.refund.RefundProgressActivity;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;
import com.lishi.baijiaxing.shoppingCart.model.StoreBean;
import com.lishi.baijiaxing.utils.ProgressBarUtil;
import com.lishi.baijiaxing.view.MyDefaultFooter;
import com.lishi.baijiaxing.view.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 退货，返修
 * Created by Administrator on 2016/8/3.
 */
@SuppressLint("ValidFragment")
public class Fragment_ReturnedGoods extends BaseFragmentV4 implements ReturnedGoodsView, OrderFormAdapter.OnReturnedGoodsItemClick, SpringView.OnFreshListener {
    private static final String TAG = "Fragment_ReturnedGoods";
    private static final int RETURNED_GOORD = 0X0001;
    private View view;
    private boolean isPrepared;
    private ListView mMyListView;
    private ProgressBarUtil progressBarUtil;
    private OrderFormAdapter adapter;
    private LinearLayout order_null;
    private List<MyOrderList.DataBean.OrderListBean> mDataBeens = new ArrayList<>();
    private ReturnedGoodsPresenterImpl mReturnedGoodsPresenter;
    private SpringView springView_order_returnedGoods;
    private boolean isLoadMore = false;
    private MyDefaultFooter defaultFooter;

    public static Fragment_ReturnedGoods newInstantiation() {
        return new Fragment_ReturnedGoods();
    }

    public Fragment_ReturnedGoods() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_returned_goods, container, false);

        return view;
    }

    @Override
    public void onInvisible() {

    }

    @Override
    public void lazyLoad() {
        Log.i("Fragment_ReturnedGoods", "isVisible——————————" + isVisible + "————————isPrepared——————————" + isPrepared);
        if (!isVisible || !isPrepared) {
            return;
        }
        findId();
        initView(2);
    }

    private void initView(int page) {

        if (mReturnedGoodsPresenter == null) {
            mReturnedGoodsPresenter = new ReturnedGoodsPresenterImpl(this);
        }
        mReturnedGoodsPresenter.loadOrderList(page);
    }

    private void findId() {
        mMyListView = (ListView) view.findViewById(R.id.listview_myorderform_returnedgoods);
        order_null = (LinearLayout) view.findViewById(R.id.returned_goods_order_null);
        springView_order_returnedGoods = (SpringView) view.findViewById(R.id.springView_order_returnedgoods);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void loadOrderListSuccess(List<MyOrderList.DataBean.OrderListBean> dataBeens) {
        this.mDataBeens.clear();
        this.mDataBeens.addAll(dataBeens);

        if (mDataBeens == null || mDataBeens.size() == 0) {
            order_null.setVisibility(View.VISIBLE);
            mMyListView.setVisibility(View.GONE);
        } else {
            order_null.setVisibility(View.GONE);
            mMyListView.setVisibility(View.VISIBLE);
            if (isLoadMore) {
                isLoadMore = false;
                springView_order_returnedGoods.onFinishFreshAndLoad();
                adapter.notifyDataSetChanged();
                return;
            }
            adapter = new OrderFormAdapter(getActivity(), mDataBeens);
            mMyListView.setAdapter(adapter);
            Log.i(TAG, "加载数据完成" + TAG + "==============");
            defaultFooter = new MyDefaultFooter(getActivity());
            springView_order_returnedGoods.setFooter(defaultFooter);
            springView_order_returnedGoods.setListener(this);
            springView_order_returnedGoods.setType(SpringView.Type.OVERLAP);
            adapter.setOnItemClickListener(new OrderFormAdapter.OnListItemClickListener() {
                @Override
                public void onListItemClickListener(View v, String oid, int state, int position) {
                    Intent startOrderDetails = new Intent(getActivity(), OrderDetailsActivity.class);
                    startOrderDetails.putExtra("oid", oid);
                    startActivityForResult(startOrderDetails, RETURNED_GOORD);
                }
            });
            adapter.setOnReturnedGoodsItemClick(this);
        }

    }

    @Override
    public void loadOrderListFailed(String error) {
        if (isLoadMore) {
            isLoadMore = false;
            springView_order_returnedGoods.onFinishFreshAndLoad();
            return;
        }
    }

    @Override
    public void changeOrderStatusSuccess(SCOperation orderList) {

    }

    @Override
    public void changeOrderStatusFailed(String error) {

    }

    @Override
    public void onLastPage(String status) {
        defaultFooter.setMoreLoad(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDataBeens = null;
        progressBarUtil = null;
        mMyListView = null;
    }

    @Override
    public void showDialog() {
        if (progressBarUtil == null) {
            progressBarUtil = new ProgressBarUtil(getActivity());
        }
        progressBarUtil.show();
    }

    @Override
    public void closeDialog() {
        if (progressBarUtil != null) {
            progressBarUtil.dismiss();
        }
    }

    @Override
    public void loadDataSuccess(Object data) {

    }

    @Override
    public void loadDataFailed(String error) {

    }

    @Override
    public void onListItemClickListener(View v, StoreBean storeBean) {

    }


    @Override
    public void onBottom1(View v, int position) {

    }

    /**
     * 进度查询
     *
     * @param v
     * @param position
     */
    @Override
    public void onBottom2(View v, int position) {
        Intent startRefundProgressActivity = new Intent(getActivity(), RefundProgressActivity.class);
        startActivity(startRefundProgressActivity);
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
