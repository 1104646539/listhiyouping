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
import com.lishi.baijiaxing.yiyuan.model.HotList;
import com.lishi.baijiaxing.yiyuan.model.YiYuanHotBean;
import com.lishi.baijiaxing.yiyuan.presenter.YiYuanHotPresenterImpl;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/21.
 */
public class Fragment_YiYuanHot extends BaseFragmentV4 implements YiYuanHotView {
    private View mView;
    private boolean isPrepare;
    private HotList mHotList;
    private RecyclerView mRecyclerView;
    private YiYuanHotPresenterImpl mYiYuanHotPresenter;

    public static Fragment_YiYuanHot newInstance() {
        return new Fragment_YiYuanHot();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.yiyuan_hot, container, false);
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
        if (mYiYuanHotPresenter == null) {
            mYiYuanHotPresenter = new YiYuanHotPresenterImpl(this);
        }
        mYiYuanHotPresenter.loadData();
    }

    private void initView() {
        findId();

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        YiYuanHotAdapter adapter = new YiYuanHotAdapter(getActivity(), mHotList.getData());
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new YiYuanHotAdapter.OnItemClickListener() {
            @Override
            public void onClickListener(View view, int position) {
                Intent startYiYuanDetailsActivity = new Intent(getActivity(), YiYuanDetailsActivity.class);
                startYiYuanDetailsActivity.putExtra("type", YiYuanActivity.TYPE_HOT);
                startActivity(startYiYuanDetailsActivity);
            }
        });
    }

    private void findId() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView_yiyuan_hot);
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
    public void loadDataSuccess(Object data) {

    }

    @Override
    public void loadDataFailed(String error) {

    }

    @Override
    public void loadHotListSuccess(HotList hotList) {
        mHotList = hotList;
        Logger.d("status:"+hotList.getStatus()+"msg:"+hotList.getMsg()+"hotlist:"+hotList.getData());
        if (mHotList.getData() == null||mHotList.getData().size()==0) {
            return;
        }
        initView();
    }

    @Override
    public void loadHotListFailed(String error) {

    }
}
