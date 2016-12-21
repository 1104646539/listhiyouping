package com.lishi.baijiaxing.details.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseFragmentV4;
import com.lishi.baijiaxing.details.adapter.FragmentBriefAdapter;
import com.lishi.baijiaxing.details.model.CommodityDetails;
import com.lishi.baijiaxing.details.presenter.CommodityBriefPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * 普通商品详情——简介
 * Created by Administrator on 2016/10/19.
 */
public class Fragment_Commodity_Brief extends BaseFragmentV4 implements CommodityBriefView {
    private View mView;
    private static Fragment_Commodity_Brief mFragment_Commodity_Brief;
    private boolean isPrepare;
    private RecyclerView mRecyclerView;
    private CommodityBriefPresenterImpl mCommodityBriefPresenter;
    private CommodityDetails.DataBean mDataBeen;
    private String gid;//cid

    public static Fragment_Commodity_Brief newInstance() {
//        if (mFragment_Commodity_Brief == null) {
//            mFragment_Commodity_Brief = new Fragment_Commodity_Brief();
//        }
        return new Fragment_Commodity_Brief();
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
        mDataBeen = getActivity().getIntent().getParcelableExtra("brief");
        Log.i("mDataBeen", "mDataBeen:" + mDataBeen.getBriefUrls().size());
        if (mDataBeen != null && mDataBeen.getBriefUrls() != null && mDataBeen.getBriefUrls().size() > 0) {
            initView();
            Log.i("mDataBeen", "mDataBeen:获取成功");
        } else {
            if (mCommodityBriefPresenter == null) {
                mCommodityBriefPresenter = new CommodityBriefPresenterImpl(this);
            }
            gid = getActivity().getIntent().getStringExtra("gid");
            Log.i("mDataBeen", "mDataBeen:获取失败，重新获取" + "gid:" + gid);
            if (gid != null && !gid.equals("")) {
                mCommodityBriefPresenter.loadData(gid);
            }
        }
    }

    private void initView() {
        findId();
        initRecyclerView();
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
    public void loadDataSuccess(CommodityDetails data) {
        mDataBeen = data.getData();
        initView();
    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);

        FragmentBriefAdapter adapter = new FragmentBriefAdapter(getActivity(), mDataBeen.getBriefUrls());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void loadDataFailed(String error) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFragment_Commodity_Brief = null;
        mRecyclerView = null;
        mCommodityBriefPresenter = null;
    }
}
