package com.lishi.baijiaxing.myOrders.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.myOrders.adapter.OrderFormAdpter;
import com.lishi.baijiaxing.base.BaseFragmentV4;
import com.lishi.baijiaxing.myOrders.model.MyOrderFormBean;
import com.lishi.baijiaxing.myOrders.presenter.OrdersPresenterImpl;
import com.lishi.baijiaxing.utils.ProgressBarUtil;
import com.lishi.baijiaxing.view.MyListView;

import java.util.ArrayList;

/**
 * 待收货
 * Created by Administrator on 2016/8/3.
 */
@SuppressLint("ValidFragment")
public class Fragment_StayTakeGoods extends BaseFragmentV4 implements OrdersView {
    private static final String TAG = "Fragment_StayTakeGoods";
    private static Fragment_StayTakeGoods mFragment_stayTakeGoods;
    private View view;
    private boolean isPrepared;
    private ArrayList<MyOrderFormBean> mMyOrderFormBeen;
    private MyListView mListView;
    private ProgressBarUtil progressBarUtil;
    private OrdersPresenterImpl mOrdersPresenter;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 55){
                mOrdersPresenter.LoadData(TAG);
            }
        }
    };
    public static Fragment_StayTakeGoods newInstantiation() {
        if (mFragment_stayTakeGoods == null) {
            mFragment_stayTakeGoods = new Fragment_StayTakeGoods();
        }
        return mFragment_stayTakeGoods;
    }

    private Fragment_StayTakeGoods() {

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
        mListView = (MyListView) view.findViewById(R.id.listview_myorderform_stayshopment);
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
        OrderFormAdpter adapter = new OrderFormAdpter(getActivity(), mMyOrderFormBeen);
        mListView.setAdapter(adapter);
        Log.i(TAG, "加载数据完成" + TAG + "==============");
        progressBarUtil.dismiss();
    }

    @Override
    public void onFailed() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFragment_stayTakeGoods = null;
    }
}
