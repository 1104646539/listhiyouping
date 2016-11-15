package com.lishi.baijiaxing.myOrders.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.myOrders.adapter.OrderFormAdpter;
import com.lishi.baijiaxing.base.BaseFragmentV4;
import com.lishi.baijiaxing.myOrders.model.MyOrderFormBean;
import com.lishi.baijiaxing.myOrders.presenter.OrdersPresenterImpl;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.shoppingCart.model.StoreBean;
import com.lishi.baijiaxing.utils.ProgressBarUtil;
import com.lishi.baijiaxing.view.MyListView;

import java.util.ArrayList;

/**
 * 代付款
 * Created by Administrator on 2016/8/3.
 */
@SuppressLint("ValidFragment")
public class Fragment_StayPayment extends BaseFragmentV4 implements OrdersView, OrderFormAdpter.OnStayEvaluateItemClick, View.OnClickListener {
    private static final String TAG = "Fragment_StayPayment";
    private static final int STAY_PAYMENT = 0x002;
    private static Fragment_StayPayment mFragment_StayPayment;
    private View view;
    private boolean isPrepared;//是否准备好
    private ArrayList<MyOrderFormBean> mMyOrderFormBeen;
    private MyListView mListView;
    private OrdersPresenterImpl mOrdersPresenter;
    private ProgressBarUtil progressBarUtil;
    private OrderFormAdpter adapter;
    private int mPosition = -1;

    private TextView cancel_ok, cancel_no;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 55) {
                mOrdersPresenter.LoadData(TAG);
            }
        }
    };
    private PopupWindow pop_cancelDialog;
    private View cancel_root;

    public static Fragment_StayPayment newInstantiation() {
        if (mFragment_StayPayment == null) {
            mFragment_StayPayment = new Fragment_StayPayment();
        }
        return mFragment_StayPayment;
    }

    private Fragment_StayPayment() {

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
        initView();
    }

    private void initView() {
        findId();
        if (mOrdersPresenter == null) {
            mOrdersPresenter = new OrdersPresenterImpl(this);
        }
        if (progressBarUtil == null) {
            progressBarUtil = new ProgressBarUtil(getActivity());
        }
        progressBarUtil.show();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(1200);
                    mHandler.sendEmptyMessage(55);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void findId() {
        mListView = (MyListView) view.findViewById(R.id.listview_myorderform_staypayment);
        cancel_root = LayoutInflater.from(getActivity()).inflate(R.layout.cancel_order_dialog, null, false);

        cancel_ok = (TextView) cancel_root.findViewById(R.id.cancel_yes);
        cancel_no = (TextView) cancel_root.findViewById(R.id.cancel_no);

        cancel_no.setOnClickListener(this);
        cancel_ok.setOnClickListener(this);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void onSuccess(ArrayList<MyOrderFormBean> myOrderFormBeen) {
        if (mMyOrderFormBeen == null) {
            mMyOrderFormBeen = new ArrayList<>();
            /**
             * 初始化数据
             */
        } else {
            mMyOrderFormBeen.clear();
        }
        mMyOrderFormBeen = myOrderFormBeen;
        adapter = new OrderFormAdpter(getActivity(), mMyOrderFormBeen);
        mListView.setAdapter(adapter);

        Log.i(TAG, "加载数据完成" + TAG + "==============" + mMyOrderFormBeen.size());
        progressBarUtil.dismiss();

        adapter.setOnItemClickListener(new OrderFormAdpter.OnListItemClickListener() {
            @Override
            public void onListItemClickListener(View v, StoreBean storeBean, int state, int position) {
                Intent startOrderDetails = new Intent(getActivity(), OrderDetailsActivity.class);
                startOrderDetails.putExtra("data", storeBean);
                startOrderDetails.putExtra("state", state);
                startOrderDetails.putExtra("position", position);
                startActivityForResult(startOrderDetails, STAY_PAYMENT);
            }
        });
        adapter.setOnStayEvaluateItemClick(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == STAY_PAYMENT) {
                int position = data.getIntExtra("position", -1);
                mMyOrderFormBeen.remove(position);
                adapter.notifyDataSetChanged();
            }
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
    public void onFailed() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFragment_StayPayment = null;
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

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_yes://确定
                pop_cancelDialog.dismiss();
                mMyOrderFormBeen.remove(mPosition);
                adapter.notifyDataSetChanged();
                break;
            case R.id.cancel_no://取消
                pop_cancelDialog.dismiss();
                break;
        }
    }
}
