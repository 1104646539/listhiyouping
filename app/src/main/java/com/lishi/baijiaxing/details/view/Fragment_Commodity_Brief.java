package com.lishi.baijiaxing.details.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseFragmentV4;
import com.lishi.baijiaxing.details.adapter.FragmentBriefAdapter;
import com.lishi.baijiaxing.details.presenter.CommodityBriefPresenterImpl;

import java.util.ArrayList;

/**
 * 普通商品详情——简介
 * Created by Administrator on 2016/10/19.
 */
public class Fragment_Commodity_Brief extends BaseFragmentV4 implements CommodityBriefView {
    private View mView;
    private static Fragment_Commodity_Brief mFragment_Commodity_Brief;
    private boolean isPrepare;
    private RecyclerView mRecyclerView;
    private ArrayList<Integer> srcIds;
    private CommodityBriefPresenterImpl mCommodityBriefPresenter;

    public static Fragment_Commodity_Brief newInstance() {
        if (mFragment_Commodity_Brief == null) {
            mFragment_Commodity_Brief = new Fragment_Commodity_Brief();
        }
        return mFragment_Commodity_Brief;
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
        mView = inflater.inflate(R.layout.fragment_commodity_brief, container, false);
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
        if (mCommodityBriefPresenter == null) {
            mCommodityBriefPresenter = new CommodityBriefPresenterImpl(this);
        }
        mCommodityBriefPresenter.loadData();

    }

    private void initView() {
        findId();
    }


    private void findId() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView_commodity_brief);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void closeDialog() {

    }

    @Override
    public void loadDataSuccess(ArrayList<Integer> data) {
        srcIds = data;
        initView();

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);

        FragmentBriefAdapter adapter = new FragmentBriefAdapter(getActivity(), srcIds);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void loadDataFailed(String error) {

    }
}
