package com.lishi.baijiaxing.yiyuan.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseFragmentV4;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.utils.DividerItemDecoration;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanNewestAdapter;
import com.lishi.baijiaxing.yiyuan.model.YiYuanNewestBean;
import com.lishi.baijiaxing.yiyuan.presenter.YiYuanNewestPresenterImpl;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/21.
 */
public class Fragment_YiYuanNewest extends BaseFragmentV4 implements BaseView<ArrayList<YiYuanNewestBean>> {
    private View mView;
    private boolean isPrepare;
    private static Fragment_YiYuanNewest mFragment_YiYuanNewest;
    private RecyclerView mRecyclerView;
    private ArrayList<YiYuanNewestBean> mYiYuanNewestBeans;
    private YiYuanNewestPresenterImpl mYiYuanNewestPresenter;

    public static Fragment_YiYuanNewest newInstance() {
        if (mFragment_YiYuanNewest == null) {
            mFragment_YiYuanNewest = new Fragment_YiYuanNewest();
        }
        return mFragment_YiYuanNewest;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.yiyuan_newest, container, false);
        return mView;
    }

    @Override
    public void onInvisible() {

    }

    @Override
    public void lazyLoad() {
        if (!isPrepare || !isPrepare) {
            return;
        }

        if (mYiYuanNewestPresenter == null) {
            mYiYuanNewestPresenter = new YiYuanNewestPresenterImpl(this);
        }
        mYiYuanNewestPresenter.loadData();

    }

    private void initView() {
        findId();

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        YiYuanNewestAdapter adapter = new YiYuanNewestAdapter(getActivity(), mYiYuanNewestBeans);
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new YiYuanHotAdapter.OnItemClickListener() {
            @Override
            public void onClickListener(View view, int position) {
                Intent startYiYuanDetailsActivity = new Intent(getActivity(), YiYuanDetailsActivity.class);
                startYiYuanDetailsActivity.putExtra("type", YiYuanActivity.TYPE_NEWEST);
                startActivity(startYiYuanDetailsActivity);
            }
        });
    }


    private void findId() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView_yiyuan_newest);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepare = true;
        lazyLoad();
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void closeDialog() {

    }

    @Override
    public void loadDataSuccess(ArrayList<YiYuanNewestBean> data) {
        mYiYuanNewestBeans = data;
        initView();
    }

    @Override
    public void loadDataFailed(String error) {

    }
}
