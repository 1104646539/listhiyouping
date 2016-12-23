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
import android.widget.Toast;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseFragmentV4;
import com.lishi.baijiaxing.free.adapter.FreeFragmentGridAdapter;
import com.lishi.baijiaxing.free.adapter.FreeFragmentLinearAdapter;
import com.lishi.baijiaxing.free.model.FreeList;
import com.lishi.baijiaxing.free.presenter.FreeFragmentPresenterImpl;
import com.orhanobut.logger.Logger;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;

/**
 * Created by Administrator on 2016/10/17.
 */
public class Fragment_Free extends BaseFragmentV4 implements FreeFragmentView, FreeFragmentGridAdapter.OnFreeGridItemClick {
    /**
     * 数据类型
     */
    private int type = -1;
    private View mView;
    private FreeList mFreeList;
    private boolean isPrepared;
    private FreeFragmentPresenterImpl mFreeFragmentPresenterImpl;
    private RecyclerView mRecyclerView;
    /**
     * 是否是网格布局
     */
    private boolean isGrid = true;

    public static Fragment_Free newInstance() {
        return new Fragment_Free();
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

    @Override
    public void onResume() {
        super.onResume();
        lazyLoad();
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
    public void loadDataSuccess(Object data) {

    }

    private void initLayoutManager() {
        VerticalDividerItemDecoration verticalDividerItemDecoration = new VerticalDividerItemDecoration.Builder(getActivity()).build();
        if (isGrid) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(gridLayoutManager);

            FreeFragmentGridAdapter adapter = new FreeFragmentGridAdapter(getActivity(), mFreeList.getData());
            mRecyclerView.setAdapter(adapter);
//            mRecyclerView.removeItemDecoration(verticalDividerItemDecoration);
            adapter.setMonFreeGridClickLister(this);
        } else {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(layoutManager);
//            mRecyclerView.removeItemDecoration(verticalDividerItemDecoration);
            FreeFragmentLinearAdapter adapter = new FreeFragmentLinearAdapter(getActivity(), mFreeList.getData());
            mRecyclerView.setAdapter(adapter);
//            mRecyclerView.addItemDecoration(verticalDividerItemDecoration);
            adapter.setOnFreeGridItemClick(this);
        }
    }

    @Override
    public void loadDataFailed(String error) {

    }

    public void setType(int type) {
        this.type = type;
        initView();
    }

    public boolean isGrid() {
        return isGrid;
    }

    public void setGrid(boolean grid) {
        if (mFreeList.getData() == null || mFreeList.getData().size() == 0) {
            return;
        }
        isGrid = grid;
        initLayoutManager();
    }

    @Override
    public void onFreeGridClickLister(View view, int position) {
        Intent startFreeCommodityDetailsActivity = new Intent(getActivity(), FreeDetailsActivity.class);
        startFreeCommodityDetailsActivity.putExtra("zid", mFreeList.getData().get(position).getZid());
        startFreeCommodityDetailsActivity.putExtra("gid", mFreeList.getData().get(position).getGid());
        Logger.d("zid" + mFreeList.getData().get(position).getZid() + "gid" + mFreeList.getData().get(position).getGid());
        startActivity(startFreeCommodityDetailsActivity);
    }

    @Override
    public void loadFreeListSuccess(FreeList freeList) {
        Log.e("loadDataSuccess", "Fragment" + freeList.getData().size());
        mFreeList = freeList;
        if (mFreeList.getData() == null || mFreeList.getData().size() == 0) {
            Toast.makeText(getActivity(), "type:" + type + "无商品", Toast.LENGTH_SHORT).show();
            return;
        }
        initLayoutManager();
    }

    @Override
    public void loadFreeListFailed(String error) {
        Log.e("loadDataFailed", "Fragment error= " + error);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFreeList = null;
        mRecyclerView = null;
        mView = null;
        isPrepared = false;
    }
}
