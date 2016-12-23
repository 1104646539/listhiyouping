package com.lishi.baijiaxing.yiyuan.view;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.liaoinstan.springview.widget.SpringView;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseFragmentV4;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.utils.DividerItemDecoration;
import com.lishi.baijiaxing.view.MyDefaultFooter;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanListAdapter;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanNewestAdapter;
import com.lishi.baijiaxing.yiyuan.model.LotteryList;
import com.lishi.baijiaxing.yiyuan.model.YiYuanNewestBean;
import com.lishi.baijiaxing.yiyuan.presenter.YiYuanNewestPresenterImpl;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/21.
 */
public class Fragment_YiYuanNewest extends BaseFragmentV4 implements YiYuanNewestView, SpringView.OnFreshListener {
    private View mView;
    private boolean isPrepare;
    //    private RecyclerView mRecyclerView;
    private ListView mListView;
    private YiYuanNewestPresenterImpl mYiYuanNewestPresenter;
    private List<LotteryList.DataBean.CommodityListBean> mLotteryList = new ArrayList<>();
    private SpringView springView_Lottery;
    private boolean isLoading;
    //    private YiYuanNewestAdapter adapter;
    private YiYuanListAdapter mAdapter;
    private MyDefaultFooter myDefaultFooter;
    private LinearLayoutManager manager;

    public static Fragment_YiYuanNewest newInstance() {
        return new Fragment_YiYuanNewest();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.yiyuan_newest, container, false);
        findId();
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
        if (mYiYuanNewestPresenter == null) {
            mYiYuanNewestPresenter = new YiYuanNewestPresenterImpl(this);
        }
        mYiYuanNewestPresenter.loadData(2);
    }

    private void initView() {
        if (isLoading) {
            springView_Lottery.onFinishFreshAndLoad();
            Logger.d("更新数据" + Thread.currentThread().getName());
            mAdapter.notifyDataSetChanged();
            isLoading = false;
        } else {
            myDefaultFooter = new MyDefaultFooter(getActivity());
            springView_Lottery.setFooter(myDefaultFooter);
            springView_Lottery.setType(SpringView.Type.FOLLOW);
            springView_Lottery.setListener(this);

//            manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//            mRecyclerView.setLayoutManager(manager);
//            mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
//            adapter = new YiYuanNewestAdapter(getActivity(), mLotteryList);
//            mRecyclerView.setAdapter(adapter);
            mAdapter = new YiYuanListAdapter(getActivity(), mLotteryList);
            mListView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(new YiYuanHotAdapter.OnItemClickListener() {
                @Override
                public void onClickListener(View view, int position) {
                    Intent startYiYuanDetailsActivity = new Intent(getActivity(), YiYuanDetailsActivity.class);
                    startYiYuanDetailsActivity.putExtra("type", YiYuanActivity.TYPE_NEWEST);
                    startActivity(startYiYuanDetailsActivity);
                }
            });
        }
    }


    private void findId() {
//        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView_yiyuan_newest);
        springView_Lottery = (SpringView) mView.findViewById(R.id.springView_Lottery);
        mListView = (ListView) mView.findViewById(R.id.listView_yiyuan_newest);
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
    public void loadLotteryListSuccess(List<LotteryList.DataBean.CommodityListBean> lotteryList) {
        mLotteryList.clear();
        this.mLotteryList.addAll(lotteryList);
        if (mLotteryList.size() == 0) {
            return;
        }
        initView();
    }

    @Override
    public void loadLotteryListFailed(String error) {
        Logger.d("error:" + error);
    }

    @Override
    public void onLastPage(String status) {
        myDefaultFooter.setMoreLoad(false);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadmore() {
        isLoading = true;
        mYiYuanNewestPresenter.loadData(-1);
    }
}
