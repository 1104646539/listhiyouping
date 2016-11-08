package com.lishi.baijiaxing.freeDesign.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseFragmentV4;
import com.lishi.baijiaxing.freeDesign.FreeDesignActivity;
import com.lishi.baijiaxing.freeDesign.adapter.FreeDesignAdapter;
import com.lishi.baijiaxing.freeDesign.adapter.ProgressQueryAdapter;
import com.lishi.baijiaxing.freeDesign.model.ProgressQueryBean;

/**
 * 免费设计——对话服务助手
 * Created by Administrator on 2016/10/21.
 */
public class Fragment_DialogueHelper extends BaseFragmentV4 {
    private RecyclerView mRecyclerView;
    private static Fragment_DialogueHelper mFragment_DialogueHelper;
    private View mView;
    private boolean isPrepare;

    public static Fragment_DialogueHelper newInstance() {
        if (mFragment_DialogueHelper == null) {
            mFragment_DialogueHelper = new Fragment_DialogueHelper();
        }
        return mFragment_DialogueHelper;
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

    
    }

    private void initData() {
    }

    private void findId() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView_dialogue_helper);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_dialogue_helper, container, false);
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
        mFragment_DialogueHelper = null;
    }
}
