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
import com.lishi.baijiaxing.base.BaseFragmentV4;
import com.lishi.baijiaxing.myOrders.adapter.OrderFormAdapter;
import com.lishi.baijiaxing.myOrders.model.MyOrderFormBean;
import com.lishi.baijiaxing.myOrders.model.MyOrderList;
import com.lishi.baijiaxing.myOrders.presenter.StayPaymentPresenterImpl;
import com.lishi.baijiaxing.myOrders.presenter.StayTakeGoodsPresenterImpl;
import com.lishi.baijiaxing.pay.PayActivity;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;
import com.lishi.baijiaxing.shoppingCart.model.StoreBean;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrder;
import com.lishi.baijiaxing.utils.ProgressBarUtil;
import com.lishi.baijiaxing.view.MyDefaultFooter;
import com.lishi.baijiaxing.view.MyListView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 代付款
 * Created by Administrator on 2016/8/3.
 */
@SuppressLint("ValidFragment")
public class Fragment_StayPayment extends BaseFragmentV4 implements StayPaymentView, OrderFormAdapter.OnStayPaymentItemClick, View.OnClickListener, SpringView.OnFreshListener {
    private static final String TAG = "Fragment_StayPayment";
    private static final int STAY_PAYMENT = 0x002;
    private View view;
    private boolean isPrepared;//是否准备好
    private ListView mListView;
    private ProgressBarUtil progressBarUtil;
    private OrderFormAdapter adapter;
    private int mPosition = -1;
    private TextView cancel_ok, cancel_no;
    private PopupWindow pop_cancelDialog;
    private View cancel_root;
    private LinearLayout order_null;
    private List<MyOrderList.DataBean.OrderListBean> mDataBeens = new ArrayList<>();
    private StayPaymentPresenterImpl mStayPaymentPresenter;
    private SpringView springView_order_stayPayment;
    private boolean isLoadMore = false;
    private MyDefaultFooter defaultFooter;

    public static Fragment_StayPayment newInstantiation() {
        return new Fragment_StayPayment();
    }

    public Fragment_StayPayment() {

    }

    /**
     * 调用此方法时，已经调用过onCreateView，所以在这里初始化isPrepared和加载数据
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_stay_payment, container, false);

        return view;
    }

    @Override
    public void onInvisible() {

    }

    /**
     * 第一次进入fragment时，会调用两次lazyLoad，第一次为setUserVisibleHint的时候，当时isPrepared为false，
     * 所以当前方法会return，第二次是在onActivityCreated时，调用onactivitycreated时，isPrepared为true,
     * 所以第一次进入时，第二次调用lazyLoad时，才是真正加载数据的时候，
     * 当不是第一次进入fragment时，不会调用onActivityCreated的lazyLoad，只会调用setUserVisibleHint的lazyLoad，
     * 这也保证了每次fragment要显示时在加载数据，实现fragment的懒加载
     */
    @Override
    public void lazyLoad() {
        Log.i("Fragment_StayPayment", "isVisible——————————" + isVisible + "————————isPrepared——————————" + isPrepared);
        if (!isVisible || !isPrepared) {
            return;
        }
        findId();
        initView(2);
    }

    private void initView(int page) {
        if (mStayPaymentPresenter == null) {
            mStayPaymentPresenter = new StayPaymentPresenterImpl(this);
        }
        mStayPaymentPresenter.loadOrderList(page);
    }

    private void findId() {
        mListView = (ListView) view.findViewById(R.id.listview_myorderform_staypayment);
        cancel_root = LayoutInflater.from(getActivity()).inflate(R.layout.cancel_order_dialog, null, false);

        cancel_ok = (TextView) cancel_root.findViewById(R.id.cancel_yes);
        cancel_no = (TextView) cancel_root.findViewById(R.id.cancel_no);
        order_null = (LinearLayout) view.findViewById(R.id.stay_payment_order_null);
        springView_order_stayPayment = (SpringView) view.findViewById(R.id.springView_order_staypayment);

        cancel_no.setOnClickListener(this);
        cancel_ok.setOnClickListener(this);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void loadOrderListSuccess(List<MyOrderList.DataBean.OrderListBean> dataBeens) {
        this.mDataBeens.clear();
        this.mDataBeens.addAll(dataBeens);

        if (dataBeens == null || dataBeens.size() == 0) {
            order_null.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.GONE);
            Logger.d("size1:" + dataBeens.size());
        } else {
            Logger.d("size2:" + dataBeens.size());
            order_null.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            if (isLoadMore) {
                isLoadMore = false;
                springView_order_stayPayment.onFinishFreshAndLoad();
                adapter.notifyDataSetChanged();
                return;
            }
            defaultFooter = new MyDefaultFooter(getActivity());
            springView_order_stayPayment.setFooter(defaultFooter);
            springView_order_stayPayment.setType(SpringView.Type.FOLLOW);
            springView_order_stayPayment.setListener(this);
            adapter = new OrderFormAdapter(getActivity(), mDataBeens);
            mListView.setAdapter(adapter);
            adapter.setOnItemClickListener(new OrderFormAdapter.OnListItemClickListener() {
                @Override
                public void onListItemClickListener(View v, String oid, int state, int position) {
                    Intent startOrderDetails = new Intent(getActivity(), OrderDetailsActivity.class);
                    startOrderDetails.putExtra("oid", oid);
                    startActivityForResult(startOrderDetails, STAY_PAYMENT);
                }
            });
            adapter.setOnStayPaymentItemClick(this);
        }
    }

    /**
     * 显示取消订单对话框
     */
    private void showCancelOrderWindow() {
        pop_cancelDialog = new PopupWindow(cancel_root, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        pop_cancelDialog.setFocusable(true);
        pop_cancelDialog.setBackgroundDrawable(new BitmapDrawable());
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

    @Override
    public void loadOrderListFailed(String error) {
        if (isLoadMore) {
            isLoadMore = false;
            springView_order_stayPayment.onFinishFreshAndLoad();
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
    public void onLastPage(String status) {
        defaultFooter.setMoreLoad(false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == STAY_PAYMENT) {
//                int position = data.getIntExtra("position", -1);
//                mMyOrderFormBeen.remove(position);
//                adapter.notifyDataSetChanged();
            }
        }
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
    public void onDestroy() {
        super.onDestroy();
        progressBarUtil = null;
        mDataBeens = null;
        mListView = null;
    }

    @Override
    public void onListItemClickListener(View v, StoreBean storeBean) {

    }

    @Override
    public void onBottom1(View v, int position) {
        mPosition = position;
        showCancelOrderWindow();
    }

    @Override
    public void onBottom2(View v, int position) {
        this.mPosition = position;
        SubmitOrder.DataBean sd = new SubmitOrder.DataBean();
        String orderNumber = mDataBeens.get(mPosition).getOrderNumber();
        String totalPrice = mDataBeens.get(mPosition).getTotalPrice();
        Logger.d("position:" + position + "totalPrice" + totalPrice + "orderNumber:" + orderNumber);
        sd.setSn(orderNumber);
        sd.setPrice(totalPrice);
        Intent startPayActivity = new Intent(getActivity(), PayActivity.class);
        startPayActivity.putExtra("order", sd);
        startActivity(startPayActivity);
    }

    private void cancelOrder() {
        String oid = mDataBeens.get(mPosition).getOid();

        Logger.d("orderNumber:" + oid);

        if (oid != null && !oid.equals("")) {
            if (mStayPaymentPresenter == null) {
                mStayPaymentPresenter = new StayPaymentPresenterImpl(this);
            }
            mStayPaymentPresenter.changeOrderStatus(oid, "" + MyOrderFormBean.CANCELORDER);
        }
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
