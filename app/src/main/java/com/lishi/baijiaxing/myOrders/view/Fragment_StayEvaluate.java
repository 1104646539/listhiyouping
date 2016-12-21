package com.lishi.baijiaxing.myOrders.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.widget.SpringView;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.activity.EvaluateActivity;
import com.lishi.baijiaxing.base.BaseFragmentV4;
import com.lishi.baijiaxing.myOrders.adapter.OrderFormAdapter;
import com.lishi.baijiaxing.myOrders.model.MyOrderFormBean;
import com.lishi.baijiaxing.myOrders.model.MyOrderList;
import com.lishi.baijiaxing.myOrders.presenter.StayEvaluatePresenterImpl;
import com.lishi.baijiaxing.myOrders.presenter.StayPaymentPresenterImpl;
import com.lishi.baijiaxing.myOrders.presenter.StayTakeGoodsPresenterImpl;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;
import com.lishi.baijiaxing.shoppingCart.model.StoreBean;
import com.lishi.baijiaxing.utils.ProgressBarUtil;
import com.lishi.baijiaxing.view.MyDefaultFooter;
import com.lishi.baijiaxing.view.MyListView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 待评价
 * Created by Administrator on 2016/8/3.
 */
@SuppressLint("ValidFragment")
public class Fragment_StayEvaluate extends BaseFragmentV4 implements StayEvaluateView, View.OnClickListener, OrderFormAdapter.OnStayEvaluateItemClick, SpringView.OnFreshListener {
    private static final String TAG = "Fragment_StayEvaluate";
    private static final int STAY_EVALUATE = 0X003;
    private View view;
    private boolean isPrepared;
    private ListView mMyListView;
    private ProgressBarUtil progressBarUtil;
    private OrderFormAdapter adapter;
    private int mPosition = -1;
    private TextView cancel_ok, cancel_no;
    private PopupWindow pop_cancelDialog;
    private View cancel_root;
    private LinearLayout order_null;
    private StayEvaluatePresenterImpl mStayEvaluatePresenter;
    private TextView order_cancel_dialog_title;
    private List<MyOrderList.DataBean.OrderListBean> mDataBeens = new ArrayList<>();
    private SpringView springView_order_stayEvaluate;
    private boolean isLoadMore = false;
    private MyDefaultFooter defaultFooter;

    public static Fragment_StayEvaluate newInstantiation() {
        return new Fragment_StayEvaluate();
    }

    public Fragment_StayEvaluate() {

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
        view = inflater.inflate(R.layout.fragment_stay_evaluate, container, false);

        return view;
    }

    @Override
    public void onInvisible() {

    }

    @Override
    public void lazyLoad() {
        Log.i("Fragment_StayEvaluate", "isVisible——————————" + isVisible + "————————isPrepared——————————" + isPrepared);
        if (!isVisible || !isPrepared) {
            return;
        }
        findId();
        initView(2);
    }

    private void initView(int page) {

        if (mStayEvaluatePresenter == null) {
            mStayEvaluatePresenter = new StayEvaluatePresenterImpl(this);
        }
        mStayEvaluatePresenter.loadOrderList(page);
    }

    private void findId() {
        mMyListView = (ListView) view.findViewById(R.id.listview_myorderform_stayevaluate);
        cancel_root = LayoutInflater.from(getActivity()).inflate(R.layout.cancel_order_dialog, null, false);
        cancel_ok = (TextView) cancel_root.findViewById(R.id.cancel_yes);
        cancel_no = (TextView) cancel_root.findViewById(R.id.cancel_no);
        order_null = (LinearLayout) view.findViewById(R.id.stay_evaluate_order_null);
        springView_order_stayEvaluate = (SpringView) view.findViewById(R.id.springView_order_stayevaluate);

        cancel_no.setOnClickListener(this);
        cancel_ok.setOnClickListener(this);
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
            mMyListView.setVisibility(View.GONE);
        } else {
            order_null.setVisibility(View.GONE);
            mMyListView.setVisibility(View.VISIBLE);
            if (isLoadMore) {
                isLoadMore = false;
                springView_order_stayEvaluate.onFinishFreshAndLoad();
                adapter.notifyDataSetChanged();
                return;
            }
            defaultFooter = new MyDefaultFooter(getActivity());
            springView_order_stayEvaluate.setFooter(defaultFooter);
            springView_order_stayEvaluate.setType(SpringView.Type.FOLLOW);
            springView_order_stayEvaluate.setListener(this);
            adapter = new OrderFormAdapter(getActivity(), mDataBeens);
            mMyListView.setAdapter(adapter);
            adapter.setOnItemClickListener(new OrderFormAdapter.OnListItemClickListener() {
                @Override
                public void onListItemClickListener(View v, String oid, int state, int position) {
                    Intent startOrderDetails = new Intent(getActivity(), OrderDetailsActivity.class);
                    startOrderDetails.putExtra("oid", oid);
                    startActivityForResult(startOrderDetails, STAY_EVALUATE);
                }
            });
            adapter.setOnStayEvaluateItemClick(this);
        }
    }

    @Override
    public void loadOrderListFailed(String error) {
        if (isLoadMore) {
            isLoadMore = false;
            springView_order_stayEvaluate.onFinishFreshAndLoad();
            return;
        }
    }

    @Override
    public void changeOrderStatusSuccess(SCOperation orderList) {
        initView(0);
    }

    @Override
    public void changeOrderStatusFailed(String error) {
        Toast.makeText(getActivity(), "操作失败:" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteOrderStatusSuccess(SCOperation orderList) {
        initView(0);
    }

    @Override
    public void deleteOrderStatusFailed(String error) {
        Toast.makeText(getActivity(), "操作失败：" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLastPage(String status) {
        defaultFooter.setMoreLoad(false);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == STAY_EVALUATE) {
//                int position = data.getIntExtra("position", -1);
//                mMyOrderList.remove(position);
//                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        progressBarUtil = null;
        mDataBeens = null;
        mMyListView = null;
    }

    /**
     * 显示取消订单对话框
     */
    private void showCancelOrderWindow() {
        pop_cancelDialog = new PopupWindow(cancel_root, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        pop_cancelDialog.setFocusable(true);
        pop_cancelDialog.setBackgroundDrawable(new BitmapDrawable());
        order_cancel_dialog_title = (TextView) cancel_root.findViewById(R.id.order_cancel_dialog_title);
        order_cancel_dialog_title.setText("是否删除订单?");
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

    private void deleteOrder() {
        String oid = mDataBeens.get(mPosition).getOid();

        Logger.d("orderNumber:" + oid);

        if (oid != null && !oid.equals("")) {
            if (mStayEvaluatePresenter == null) {
                mStayEvaluatePresenter = new StayEvaluatePresenterImpl(this);
            }
            mStayEvaluatePresenter.deleteOrder(oid);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_yes://确定
                pop_cancelDialog.dismiss();
                deleteOrder();
                break;
            case R.id.cancel_no://取消
                pop_cancelDialog.dismiss();
                break;
        }
    }

    @Override
    public void onListItemClickListener(View v, StoreBean storeBean) {

    }

    /**
     * 删除订单
     *
     * @param v
     * @param position
     */
    @Override
    public void onBottom1(View v, int position) {
        mPosition = position;
        showCancelOrderWindow();
    }

    /**
     * 评论
     *
     * @param v
     * @param position
     */
    @Override
    public void onBottom2(View v, int position) {
        mPosition = position;
        startEvaluateActivity();
    }

    private void startEvaluateActivity() {
        Intent startEvaluateActivity = new Intent(getActivity(), EvaluateActivity.class);
        startEvaluateActivity.putExtra("data", mDataBeens.get(mPosition).getOid());
        startActivity(startEvaluateActivity);
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
    public void onRefresh() {

    }

    @Override
    public void onLoadmore() {
        isLoadMore = true;
        initView(-1);
    }
}
