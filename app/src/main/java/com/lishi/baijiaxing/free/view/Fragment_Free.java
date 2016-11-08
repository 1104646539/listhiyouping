package com.lishi.baijiaxing.free.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseFragmentV4;
import com.lishi.baijiaxing.free.adapter.FreeFragmentGridAdapter;
import com.lishi.baijiaxing.free.adapter.FreeFragmentLinearAdapter;
import com.lishi.baijiaxing.free.model.FreeCommodityBean;
import com.lishi.baijiaxing.free.presenter.FreeFragmentPresenterImpl;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/17.
 */
public class Fragment_Free extends BaseFragmentV4 implements FreeFragmentView, FreeFragmentGridAdapter.OnFreeGridItemClick {
    static Fragment_Free mFragment_free;
    /**
     * 数据类型
     */
    private int type = -1;
    private View mView;
    private ArrayList<FreeCommodityBean> mFreeCommodityBeen;
    private boolean isPrepared;
    private FreeFragmentPresenterImpl mFreeFragmentPresenterImpl;
    private RecyclerView mRecyclerView;
    /**
     * 是否是网格布局
     */
    private boolean isGrid = true;


    public static synchronized Fragment_Free newInstance() {
        mFragment_free = new Fragment_Free();
        return mFragment_free;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_free, container, false);
        return mView;
    }

    @Override
    public void onInvisible() {

    }

    @Override
    public void lazyLoad() {
        if (!isVisible || !isPrepared) {
            return;
        }
        //正式开始加载视图
        findId();
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        if (mFreeFragmentPresenterImpl == null) {
            mFreeFragmentPresenterImpl = new FreeFragmentPresenterImpl(this);
        }
        Log.e("initView", "type=type=type=type=type=type=type=" + type);
        if (type != -1) {
            mFreeFragmentPresenterImpl.loadData(type);
        }
    }

    private void findId() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView_free);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void closeDialog() {

    }

    @Override
    public void loadDataSuccess(ArrayList<FreeCommodityBean> data) {
        Log.e("loadDataSuccess", "Fragment" + data.size());
        mFreeCommodityBeen = data;
        initLayoutManager();
    }

    private void initLayoutManager() {
        if (isGrid) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(gridLayoutManager);

            FreeFragmentGridAdapter adapter = new FreeFragmentGridAdapter(getActivity(), mFreeCommodityBeen);
            mRecyclerView.setAdapter(adapter);
            adapter.setMonFreeGridClickLister(this);
        } else {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(layoutManager);

            FreeFragmentLinearAdapter adapter = new FreeFragmentLinearAdapter(getActivity(), mFreeCommodityBeen);
            mRecyclerView.setAdapter(adapter);

        }
    }

    @Override
    public void loadDataFailed(String error) {
        Log.e("loadDataFailed", "Fragment error= " + error);
    }


    public void setType(int type) {
        this.type = type;
        initView();
    }

    public boolean isGrid() {
        return isGrid;
    }

    public void setGrid(boolean grid) {
        isGrid = grid;
        initLayoutManager();
    }

    @Override
    public void onFreeGridClickLister(View view, int position) {
        Intent startFreeCommodityDetailsActivity = new Intent(getActivity(), FreeDetailsActivity.class);
        startFreeCommodityDetailsActivity.putExtra("type", mFreeCommodityBeen.get(position).getType());
        startActivity(startFreeCommodityDetailsActivity);
    }
}
