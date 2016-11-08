package com.lishi.baijiaxing.freeDesign.view;

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
import com.lishi.baijiaxing.freeDesign.adapter.ProgressQueryAdapter;
import com.lishi.baijiaxing.freeDesign.model.ProgressQueryBean;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotDetailsAdapter;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanNewestDetailsAdapter;
import com.lishi.baijiaxing.yiyuan.model.YiYuanHotDetailsBean;
import com.lishi.baijiaxing.yiyuan.model.YiYuanNewestDetailsBean;
import com.lishi.baijiaxing.yiyuan.presenter.YiYuanHotDetailsPresenterImpl;
import com.lishi.baijiaxing.yiyuan.presenter.YiYuanNewestDetailsPresenterImpl;
import com.lishi.baijiaxing.yiyuan.view.YiYuanActivity;
import com.lishi.baijiaxing.yiyuan.view.YiYuanHotDetailsView;
import com.lishi.baijiaxing.yiyuan.view.YiYuanNewestDetailsView;

/**
 * 免费设计——服务进度查询
 * Created by Administrator on 2016/10/21.
 */
public class Fragment_ProgressQuery extends BaseFragmentV4 {
    private RecyclerView mRecyclerView;
    private static Fragment_ProgressQuery mFragment_ProgressQuery;
    private View mView;
    private boolean isPrepare;

    public static Fragment_ProgressQuery newInstance() {
        if (mFragment_ProgressQuery == null) {
            mFragment_ProgressQuery = new Fragment_ProgressQuery();
        }
        return mFragment_ProgressQuery;
    }

    @Override
    public void onInvisible() {

    }

    @Override
    public void lazyLoad() {
        if (!isPrepare || !isVisible) {
            return;
        }
        initView();
    }

    private void initView() {
        findId();
        initData();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);

        ProgressQueryAdapter adapter = new ProgressQueryAdapter(getActivity(), new ProgressQueryBean(ProgressQueryBean.TYPE_SUBSCRIBESU_SUCCESS));
        mRecyclerView.setAdapter(adapter);
    }

    private void initData() {
    }

    private void findId() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView_progress_query);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_progress_query, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepare = true;
        lazyLoad();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFragment_ProgressQuery = null;
    }
}
