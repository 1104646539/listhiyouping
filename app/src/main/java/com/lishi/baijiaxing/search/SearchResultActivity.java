package com.lishi.baijiaxing.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.liaoinstan.springview.widget.SpringView;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.details.CommodityDetailsActivity;
import com.lishi.baijiaxing.search.adapter.SearchListAdapter;
import com.lishi.baijiaxing.search.model.SearchList;
import com.lishi.baijiaxing.search.presenter.SearchResultPresenter;
import com.lishi.baijiaxing.search.presenter.SearchResultPresenterImpl;
import com.lishi.baijiaxing.search.view.SearchResultView;
import com.lishi.baijiaxing.utils.NetUtils;
import com.lishi.baijiaxing.utils.ProgressBarUtil;
import com.lishi.baijiaxing.view.MyDefaultFooter;
import com.lishi.baijiaxing.view.TopNavigationBar;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

/**
 * 展示搜索结果
 */
public class SearchResultActivity extends BaseActivity implements SearchResultView, SpringView.OnFreshListener, YiYuanHotAdapter.OnItemClickListener {
    private SearchResultPresenterImpl mSearchResultPresenter;
    private String mCommodityName;
    private SearchList mSearchList;
    private SearchListAdapter adapter;
    private RecyclerView mRecyclerView;
    private TopNavigationBar mTopNavigationBar;
    private SpringView springView;
    private ProgressBarUtil mProgressBarUtil;
    private MyDefaultFooter mMyDefaultFooter;
    private boolean isLoding = false;
    private LinearLayout search_result_null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        init();

    }

    private void init() {
        mCommodityName = getIntent().getStringExtra("commodityName");
        finId();
        initView(2);
    }

    private void finId() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_searchResult);
        springView = (SpringView) findViewById(R.id.springView_searchResult);
        search_result_null = (LinearLayout) findViewById(R.id.search_result_null);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.navigation_searchResult);
        mTopNavigationBar.setOnTopClick(new TopNavigationBar.OnTopClick() {
            @Override
            public void onTopLeftClick(View view) {
                finish();
            }

            @Override
            public void onTopRightClick(View view) {

            }
        });
        mTopNavigationBar.setText_title(mCommodityName);
    }

    private void initView(int page) {
        if (mSearchResultPresenter == null) {
            mSearchResultPresenter = new SearchResultPresenterImpl(this);
        }
        mSearchResultPresenter.searchCommodity(mCommodityName, page);
    }

    @Override
    public void searchCommoditySuccess(SearchList searchList) {
        this.mSearchList = searchList;
        if (mSearchList.getData().getCommodityList() == null || mSearchList.getData().getCommodityList().size() == 0) {
            search_result_null.setVisibility(View.VISIBLE);
            springView.setVisibility(View.GONE);
            return;
        } else {
            springView.setVisibility(View.VISIBLE);
            search_result_null.setVisibility(View.GONE);

            if (isLoding) {
                isLoding = false;
                springView.onFinishFreshAndLoad();
                adapter.notifyDataSetChanged();
                return;
            }

            mMyDefaultFooter = new MyDefaultFooter(this);
            springView.setFooter(mMyDefaultFooter);
            springView.setType(SpringView.Type.FOLLOW);
            springView.setListener(this);

            LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(manager);
            adapter = new SearchListAdapter(this, mSearchList);
            mRecyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(this);
        }
    }

    @Override
    public void searchCommodityFailed(String error) {
        if (isLoding) {
            isLoding = false;
            springView.onFinishFreshAndLoad();
        }
        Toast.makeText(this, "搜索失败:" + error, Toast.LENGTH_SHORT).show();
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

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadmore() {
        isLoding = true;
        initView(-1);
    }

    @Override
    public void onClickListener(View view, int position) {
        if (mSearchList.getData().getCommodityList() != null && mSearchList.getData().getCommodityList().size() != 0) {
            String gid = mSearchList.getData().getCommodityList().get(position).getGid();
            if (gid != null || !gid.equals("")) {
                Intent startCommodityDetails = new Intent(this, CommodityDetailsActivity.class);
                startCommodityDetails.putExtra("gid", gid);
                startActivity(startCommodityDetails);
            }
        }
    }
}
