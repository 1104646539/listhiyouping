package com.lishi.baijiaxing.free.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseFragmentV4;
import com.lishi.baijiaxing.free.adapter.FreeDetailsAdapter;
import com.lishi.baijiaxing.free.model.FreeCommodityBean;
import com.lishi.baijiaxing.free.model.FreeDetailsBean;
import com.lishi.baijiaxing.free.presenter.FreeDetailsPresenterImpl;
import com.lishi.baijiaxing.shoppingCart.ShoppingCartActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 免费领商品详情——详情
 * Created by Administrator on 2016/10/19.
 */
public class Fragment_FreeDetails_Details extends BaseFragmentV4 implements FreeDetailsView, View.OnClickListener {
    private View mView;
    private static Fragment_FreeDetails_Details mFragment_freeDetails_details;
    private boolean isPrepare;
    private RecyclerView mRecyclerView;
    private FreeDetailsBean freeDetailsBean;
    private ArrayList<Integer> srcIds;
    private TextView tv_bottom1_1, tv_bottom1_2, tv_bottom1_3, tv_bottom1_4;
    private TextView tv_bottom2_1, tv_bottom2_2, tv_bottom2_3, tv_bottom2_4;

    private TextView[] tv_bottom1;
    private TextView[] tv_bottom2;

    private FreeDetailsPresenterImpl mFreeDatailsPresenterImpl;
    private ImageView free_details_shoppingcart;


    public static Fragment_FreeDetails_Details newInstance() {
        if (mFragment_freeDetails_details == null) {
            mFragment_freeDetails_details = new Fragment_FreeDetails_Details();
        }
        return mFragment_freeDetails_details;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepare = true;
        lazyLoad();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_freedetails_details, container, false);
        return mView;
    }

    @Override
    public void onInvisible() {

    }

    @Override
    public void lazyLoad() {
        if (!isPrepare || !isVisible) {
            return;
        }
        if (mFreeDatailsPresenterImpl == null) {
            mFreeDatailsPresenterImpl = new FreeDetailsPresenterImpl(this);
        }
        int type = getActivity().getIntent().getIntExtra("type", -1);
        mFreeDatailsPresenterImpl.loadData(type);

    }

    private void initView() {
        findId();
        initData();
        initBottomView();

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);

        FreeDetailsAdapter adapter = new FreeDetailsAdapter(getActivity(), freeDetailsBean);
        mRecyclerView.setAdapter(adapter);
    }

    private void initBottomView() {
        int type = freeDetailsBean.getType();
        changeBottom(type);
        Log.e("initBottomView", "Type = Type = Type = Type = Type = " + type);
    }

    private void changeBottom(int position) {
        for (int i = 0; i < 4; i++) {
            if (position == i) {
                tv_bottom1[position].setVisibility(View.VISIBLE);
            } else {
                tv_bottom1[i].setVisibility(View.GONE);
            }
        }
        for (int i = 0; i < 4; i++) {
            if (position == i) {
                tv_bottom2[position].setVisibility(View.VISIBLE);
            } else {
                tv_bottom2[i].setVisibility(View.GONE);
            }
        }
    }

    private void initData() {
    }

    private void findId() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView_freeDetails_Details);

        tv_bottom1_1 = (TextView) mView.findViewById(R.id.free_details_bottom1_1);
        tv_bottom1_2 = (TextView) mView.findViewById(R.id.free_details_bottom1_2);
        tv_bottom1_3 = (TextView) mView.findViewById(R.id.free_details_bottom1_3);
        tv_bottom1_4 = (TextView) mView.findViewById(R.id.free_details_bottom1_4);
        tv_bottom2_1 = (TextView) mView.findViewById(R.id.free_details_bottom2_1);
        tv_bottom2_2 = (TextView) mView.findViewById(R.id.free_details_bottom2_2);
        tv_bottom2_3 = (TextView) mView.findViewById(R.id.free_details_bottom2_3);
        tv_bottom2_4 = (TextView) mView.findViewById(R.id.free_details_bottom2_4);
        tv_bottom1 = new TextView[]{tv_bottom1_1, tv_bottom1_2, tv_bottom1_3, tv_bottom1_4};
        tv_bottom2 = new TextView[]{tv_bottom2_1, tv_bottom2_2, tv_bottom2_3, tv_bottom2_4};
        free_details_shoppingcart = (ImageView) mView.findViewById(R.id.free_details_shoppingcart);
        free_details_shoppingcart.setOnClickListener(this);
        tv_bottom1_1.setOnClickListener(this);
        tv_bottom1_2.setOnClickListener(this);
        tv_bottom1_3.setOnClickListener(this);
        tv_bottom1_4.setOnClickListener(this);
        tv_bottom2_1.setOnClickListener(this);
        tv_bottom2_2.setOnClickListener(this);
        tv_bottom2_3.setOnClickListener(this);
        tv_bottom2_4.setOnClickListener(this);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void closeDialog() {

    }

    @Override
    public void loadDataSuccess(FreeDetailsBean data) {
        freeDetailsBean = data;
        initView();
    }

    @Override
    public void loadDataFailed(String error) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.free_details_bottom1_1:
                break;
            case R.id.free_details_bottom1_2:
                break;
            case R.id.free_details_bottom1_3:
                break;
            case R.id.free_details_bottom1_4:
                break;
            case R.id.free_details_bottom2_1:
                break;
            case R.id.free_details_bottom2_2:
                break;
            case R.id.free_details_bottom2_3:
                break;
            case R.id.free_details_bottom2_4:
                Intent startWinningActivity = new Intent(getActivity(), FreeWinningActivity.class);
                startActivity(startWinningActivity);
                break;
            case R.id.free_details_shoppingcart:
                Intent startShoppingCart = new Intent(getActivity(), ShoppingCartActivity.class);
                startActivity(startShoppingCart);
                break;
        }

    }
}
