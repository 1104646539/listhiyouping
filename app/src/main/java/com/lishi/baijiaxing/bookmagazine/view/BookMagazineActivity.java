package com.lishi.baijiaxing.bookmagazine.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liaoinstan.springview.widget.SpringView;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.bookmagazine.presenter.BookMagazinePresenterImpl;
import com.lishi.baijiaxing.details.CommodityDetailsActivity;
import com.lishi.baijiaxing.hot.adapter.HotRVAdapter;
import com.lishi.baijiaxing.hot.model.HotCommodity;
import com.lishi.baijiaxing.hot.presenter.HotCommodityPresenterImpl;
import com.lishi.baijiaxing.utils.DividerItemDecoration;
import com.lishi.baijiaxing.utils.ProgressBarUtil;
import com.lishi.baijiaxing.view.TopNavigationBar;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;
import com.orhanobut.logger.Logger;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;

/**
 * 书籍杂志
 */
public class BookMagazineActivity extends BaseActivity implements BookMagazineView, YiYuanHotAdapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private SpringView mSpringView;
    private HotRVAdapter adapter;
    private TopNavigationBar mTopNavigationBar;
    private HotCommodity mBookCommodity;
    private BookMagazinePresenterImpl mBookMagazinePresenter;
    private ProgressBarUtil mProgressBarUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_magazine);
        init();
    }

    private void init() {
        findId();
        initView();
    }

    private void initView() {
        mTopNavigationBar.setOnTopClick(new TopNavigationBar.OnTopClick() {
            @Override
            public void onTopLeftClick(View view) {
                finish();
            }

            @Override
            public void onTopRightClick(View view) {
            }
        });
        if (mBookMagazinePresenter == null) {
            mBookMagazinePresenter = new BookMagazinePresenterImpl(this);
        }
        mBookMagazinePresenter.loadData();
    }

    private void initRecyclerView() {
        adapter = new HotRVAdapter(this, mBookCommodity);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    private void findId() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_book_magazine);
        mSpringView = (SpringView) findViewById(R.id.springView_book_magazine);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.topbar_book_magazine);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void loadDataSuccess(HotCommodity commodity) {
        this.mBookCommodity = commodity;
        initRecyclerView();
    }

    @Override
    public void showDialog() {
        if (mProgressBarUtil == null) {
            mProgressBarUtil = new ProgressBarUtil(this);
        }
        mProgressBarUtil.show();
    }

    @Override
    public void closeDialog() {
        if (mProgressBarUtil != null) {
            mProgressBarUtil.dismiss();
        }
    }

    @Override
    public void loadDataSuccess(Object data) {

    }

    @Override
    public void loadDataFailed(String error) {
        Logger.d(error);
    }

    @Override
    public void onClickListener(View view, int position) {
        if (position == 0) {

        } else {
            if (mBookCommodity.getData().get(position - 1) != null && !mBookCommodity.getData().get((position - 1)).getGid().equals("")) {
                Intent startDetailsActivity = new Intent(this, CommodityDetailsActivity.class);
                startDetailsActivity.putExtra("gid", mBookCommodity.getData().get((position - 1)).getGid());
                startActivity(startDetailsActivity);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRecyclerView = null;
        adapter = null;
        mProgressBarUtil = null;
    }
}
