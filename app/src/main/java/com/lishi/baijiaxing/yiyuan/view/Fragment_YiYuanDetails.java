package com.lishi.baijiaxing.yiyuan.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseFragmentV4;
import com.lishi.baijiaxing.shoppingCart.ShoppingCartActivity;
import com.lishi.baijiaxing.yiyuan.YiYuanHotDetailsCallback;
import com.lishi.baijiaxing.yiyuan.YiYuanNewestDetailsCallback;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotDetailsAdapter;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanNewestDetailsAdapter;
import com.lishi.baijiaxing.yiyuan.model.YiYuanHotDetailsBean;
import com.lishi.baijiaxing.yiyuan.model.YiYuanNewestDetailsBean;
import com.lishi.baijiaxing.yiyuan.presenter.YiYuanHotDetailsPresenterImpl;
import com.lishi.baijiaxing.yiyuan.presenter.YiYuanNewestDetailsPresenterImpl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 一元拼——详情
 * Created by Administrator on 2016/10/21.
 */
public class Fragment_YiYuanDetails extends BaseFragmentV4 implements View.OnClickListener, YiYuanHotDetailsView, YiYuanNewestDetailsView {
    private RecyclerView mRecyclerView;
    private static Fragment_YiYuanDetails mFragment_yiYuanDetails;
    private View mView;
    private boolean isPrepare;
    private YiYuanHotDetailsAdapter hotAdapter;
    private YiYuanNewestDetailsAdapter newestAdapter;
    private YiYuanHotDetailsBean mYiYuanHotDetailsBean;
    private YiYuanNewestDetailsBean mYiYuanNewestDetailsBean;
    private int type;
    private TextView hotShopping;
    private TextView newestShopping;
    private YiYuanHotDetailsPresenterImpl mYiYuanHotDetailsPresenter;
    private YiYuanNewestDetailsPresenterImpl mYiYuanNewestDetailsPresenter;

    public static Fragment_YiYuanDetails newInstance() {
        if (mFragment_yiYuanDetails == null) {
            mFragment_yiYuanDetails = new Fragment_YiYuanDetails();
        }
        return mFragment_yiYuanDetails;
    }

    @Override
    public void onInvisible() {

    }

    @Override
    public void lazyLoad() {
        if (!isPrepare || !isVisible) {
            return;
        }

        type = getActivity().getIntent().getIntExtra("type", 0);
        if (type == YiYuanActivity.TYPE_HOT) {
            if (mYiYuanHotDetailsPresenter == null) {
                mYiYuanHotDetailsPresenter = new YiYuanHotDetailsPresenterImpl(this);
            }
            mYiYuanHotDetailsPresenter.loadData(type);

        } else if (type == YiYuanActivity.TYPE_NEWEST) {
            if (mYiYuanNewestDetailsPresenter == null) {
                mYiYuanNewestDetailsPresenter = new YiYuanNewestDetailsPresenterImpl(this);
            }
            mYiYuanNewestDetailsPresenter.loadData(type);
        }
    }

    private void initView() {
        findId();
        initData();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
    }

    private void initData() {
    }

    private void findId() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView_yiyuan_details);
        hotShopping = (TextView) mView.findViewById(R.id.yiyuan_details_hot);
        newestShopping = (TextView) mView.findViewById(R.id.yiyuan_details_newest);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_yiyuan_details, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepare = true;
        lazyLoad();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yiyuan_details_hot:
                Toast.makeText(getActivity(), "点击了Hot购买", Toast.LENGTH_SHORT).show();
                break;
            case R.id.yiyuan_details_newest:
                Toast.makeText(getActivity(), "点击了Newest购买", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void loadHotDataSuccess(YiYuanHotDetailsBean bean) {
        mYiYuanHotDetailsBean = bean;
        initView();
        hotAdapter = new YiYuanHotDetailsAdapter(getActivity(), mYiYuanHotDetailsBean);
        mRecyclerView.setAdapter(hotAdapter);

        hotShopping.setVisibility(View.VISIBLE);
        newestShopping.setVisibility(View.GONE);
        hotShopping.setOnClickListener(this);
    }

    @Override
    public void loadDataNewestSuccess(YiYuanNewestDetailsBean bean) {
        mYiYuanNewestDetailsBean = bean;
        initView();
        newestAdapter = new YiYuanNewestDetailsAdapter(getActivity(), mYiYuanNewestDetailsBean);
        mRecyclerView.setAdapter(newestAdapter);

        hotShopping.setVisibility(View.GONE);
        newestShopping.setVisibility(View.VISIBLE);
        newestShopping.setOnClickListener(this);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void closeDialog() {

    }

    @Override
    public void loadDataSuccess(Object data) {

    }

    @Override
    public void loadDataFailed(String error) {

    }

}
