package com.lishi.baijiaxing.myOrders.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseFragmentV4;
import com.lishi.baijiaxing.home.presenter.HomePresenterImpl;
import com.lishi.baijiaxing.myOrders.adapter.OrderDetailsAdapter;
import com.lishi.baijiaxing.myOrders.adapter.OrderFormAdapter;
import com.lishi.baijiaxing.myOrders.model.MyOrderFormBean;
import com.lishi.baijiaxing.myOrders.model.MyOrderList;
import com.lishi.baijiaxing.myOrders.presenter.StayTakeGoodsPresenterImpl;
import com.lishi.baijiaxing.refund.NotShipmentsRefundActivity;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;
import com.lishi.baijiaxing.shoppingCart.model.StoreBean;
import com.lishi.baijiaxing.utils.ProgressBarUtil;
import com.lishi.baijiaxing.utils.ScreenUtils;
import com.lishi.baijiaxing.view.ListScrollView;
import com.lishi.baijiaxing.view.MyDefaultFooter;
import com.lishi.baijiaxing.view.MyListView;
import com.lishi.baijiaxing.view.MyScrollView;
import com.lishi.baijiaxing.view.WListView;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

/**
 * 待收货(已发货，未发货)
 * Created by Administrator on 2016/8/3.
 */
@SuppressLint("ValidFragment")
public class Fragment_StayTakeGoods extends BaseFragmentV4 implements StayTakeGoodsView, OrderFormAdapter.OnStayTakeGoodsItemClick, SpringView.OnFreshListener, View.OnClickListener {
    private static final String TAG = "Fragment_StayTakeGoods";
    private View view;
    private boolean isPrepared;
    private List<MyOrderList.DataBean.OrderListBean> mMyOrderList = new ArrayList<>();
    private ListView mListView;
    private ProgressBarUtil progressBarUtil;
    private StayTakeGoodsPresenterImpl mStayTakeGoodsPresenter;
    private LinearLayout order_null;
    private int STAY_TAKEGOODS = 0X1001;
    private OrderFormAdapter adapter;
    private SpringView springView_order_stayTakeGoods;
    private boolean isLoadMore = false;
    private TextView cancel_ok, cancel_no;
    private PopupWindow pop_cancelDialog;
    private View cancel_root;
    private TextView order_cancel_dialog_title;
    private int mPosition;
    private MyDefaultFooter defaultFooter;
    public static Fragment_StayTakeGoods newInstantiation() {
        return new Fragment_StayTakeGoods();
    }

    public Fragment_StayTakeGoods() {

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
        view = inflater.inflate(R.layout.fragment_stay_take_goods, container, false);

        return view;
    }

    @Override
    public void onInvisible() {

    }

    @Override
    public void lazyLoad() {
        Log.i("Fragment_StayTakeGoods", "isVisible——————————" + isVisible + "————————isPrepared——————————" + isPrepared);
        if (!isVisible || !isPrepared) {
            return;
        }
        findId();
        initView(2);
    }

    private void initView(int page) {

        if (mStayTakeGoodsPresenter == null) {
            mStayTakeGoodsPresenter = new StayTakeGoodsPresenterImpl(this);
        }
        mStayTakeGoodsPresenter.loadOrderList(page);
    }

    private void findId() {
        mListView = (ListView) view.findViewById(R.id.listview_myorderform_stayshopment);
        order_null = (LinearLayout) view.findViewById(R.id.stay_takeGoods_order_null);
        springView_order_stayTakeGoods = (SpringView) view.findViewById(R.id.springView_order_stayTakeGoods);
        cancel_root = LayoutInflater.from(getActivity()).inflate(R.layout.cancel_order_dialog, null, false);

        cancel_ok = (TextView) cancel_root.findViewById(R.id.cancel_yes);
        cancel_no = (TextView) cancel_root.findViewById(R.id.cancel_no);
        cancel_ok.setOnClickListener(this);
        cancel_no.setOnClickListener(this);
    }

    @Override
    public void loadData() {
    }

    @Override
    public void loadOrderListSuccess(List<MyOrderList.DataBean.OrderListBean> dataBeens) {
        this.mMyOrderList.clear();
        this.mMyOrderList.addAll(dataBeens);

        if (mMyOrderList == null || mMyOrderList.size() == 0) {
            order_null.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.GONE);
        } else {
            order_null.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            if (isLoadMore) {
                isLoadMore = false;
                springView_order_stayTakeGoods.onFinishFreshAndLoad();
                adapter.notifyDataSetChanged();
                return;
            }
            defaultFooter= new MyDefaultFooter(getActivity());
            springView_order_stayTakeGoods.setFooter(defaultFooter);
            springView_order_stayTakeGoods.setListener(this);
            springView_order_stayTakeGoods.setType(SpringView.Type.FOLLOW);
            adapter = new OrderFormAdapter(getActivity(), mMyOrderList);
            mListView.setAdapter(adapter);

            adapter.setOnItemClickListener(new OrderFormAdapter.OnListItemClickListener() {
                @Override
                public void onListItemClickListener(View v, String oid, int state, int position) {
                    Intent startOrderDetails = new Intent(getActivity(), OrderDetailsActivity.class);
                    startOrderDetails.putExtra("oid", oid);
                    startActivityForResult(startOrderDetails, STAY_TAKEGOODS);
                }
            });
            adapter.setOnStayTakeGoodsItemClick(this);
        }
    }
    

    @Override
    public void loadOrderListFailed(String error) {
        if (isLoadMore) {
            isLoadMore = false;
            springView_order_stayTakeGoods.onFinishFreshAndLoad();
            return;
        }
    }

    @Override
    public void changeOrderStatusSuccess(SCOperation orderList) {
        initView(0);
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
        mListView = null;
        mMyOrderList = null;
        progressBarUtil = null;
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

    /**
     * 申请售后(未发货)
     */
    @Override
    public void onBottom1(View v, int position) {
        this.mPosition = position;
        Intent startNotShipmentsRefund1Activity = new Intent(getActivity(), NotShipmentsRefundActivity.class);
        startActivity(startNotShipmentsRefund1Activity);
    }

    /**
     * 提醒发货
     */
    @Override
    public void onBottom2(View v, int position) {
        this.mPosition = position;
    }

    /**
     * 申请售后
     */
    @Override
    public void onBottom3(View v, int position) {
        this.mPosition = position;
    }

    /**
     * 查看物流
     */
    @Override
    public void onBottom4(View v, int position) {
        this.mPosition = position;
    }

    /**
     * 确定收货
     */
    @Override
    public void onBottom5(View v, int position) {
        this.mPosition = position;
        showCancelOrderWindow();
    }

    @Override
    public void onRefresh() {
    }

    @Override
    public void onLoadmore() {
        isLoadMore = true;
        initView(-1);
    }
    /**
     * 显示删除订单对话框
     */
    private void showCancelOrderWindow() {
        pop_cancelDialog = new PopupWindow(cancel_root, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        pop_cancelDialog.setFocusable(true);
        pop_cancelDialog.setBackgroundDrawable(new BitmapDrawable());
        order_cancel_dialog_title = (TextView) cancel_root.findViewById(R.id.order_cancel_dialog_title);
        order_cancel_dialog_title.setText("是否确认收货?");
       
        setAlpha(0.4F);
        pop_cancelDialog.showAtLocation(cancel_root, Gravity.CENTER, 0, 0);
        pop_cancelDialog.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setAlpha(1.0F);
                pop_cancelDialog.dismiss();
            }
        });
    }
    /**
     * 修改窗体透明度
     *
     * @param alpha
     */
    private void setAlpha(float alpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = alpha;
        getActivity().getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_yes://确定
                pop_cancelDialog.dismiss();
                cancelOrder();
                break;
            case R.id.cancel_no://取消
                pop_cancelDialog.dismiss();
                break;
        }
    }

    private void cancelOrder() {
        if (mStayTakeGoodsPresenter == null) {
            mStayTakeGoodsPresenter = new StayTakeGoodsPresenterImpl(this);
        }
        mStayTakeGoodsPresenter.changeOrderStatus(mMyOrderList.get(mPosition).getOid(), "" + MyOrderFormBean.STAYEVALUATE);
    }
}
