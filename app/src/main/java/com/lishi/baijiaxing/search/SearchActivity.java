package com.lishi.baijiaxing.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.details.CommodityDetailsActivity;
import com.lishi.baijiaxing.search.adapter.SearchAdapter;
import com.lishi.baijiaxing.search.model.HotSearch;
import com.lishi.baijiaxing.search.model.SearchList;
import com.lishi.baijiaxing.search.presenter.SearchPresenterImpl;
import com.lishi.baijiaxing.search.view.SearchView;
import com.lishi.baijiaxing.search.wedget.FloatLayout;
import com.lishi.baijiaxing.utils.DividerItemDecoration;
import com.lishi.baijiaxing.utils.NetUtils;
import com.lishi.baijiaxing.utils.ProgressBarUtil;
import com.lishi.baijiaxing.utils.SharedPreferencesHelp;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索页面
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener, YiYuanHotAdapter.OnItemClickListener, FloatLayout.OnItemClickListener, SearchView {
    private FloatLayout hotFloat;
    private List<String> historyDatas;
    private EditText mEdit;
    private TextView search;
    private RecyclerView recyclerView_search;
    private ImageView search_back;
    private TextView search_delete;
    private SearchAdapter adapter;
    private SearchPresenterImpl mSearchPresenter;
    private HotSearch mHotSearch;//热门搜索

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        findId();
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void init() {
        initData();
        initView();
    }

    private void initData() {
        if (!NetUtils.isConnected(this)) {
            Log.i("Fragment_Home", "无网络连接");
            Toast.makeText(this, "请检查网络连接", Toast.LENGTH_SHORT).show();
        } else {
            Log.i("Fragment_Home", "有网络连接");
            if (mSearchPresenter == null) {
                mSearchPresenter = new SearchPresenterImpl(this);
            }
            mSearchPresenter.getHotSearchCommodity();
        }

        historyDatas = new ArrayList<>();
        historyDatas.clear();

        historyDatas = SharedPreferencesHelp.readHistory(this);
    }

    private void initView() {

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView_search.setLayoutManager(manager);
        recyclerView_search.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter = new SearchAdapter(this, historyDatas);
        recyclerView_search.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    /**
     * 添加热门标签
     */
    private void drawHotFloat() {
        hotFloat.removeAllViews();
        for (int i = 0; i < mHotSearch.getData().size(); i++) {
            TextView tv = new TextView(this);
            tv.setText(String.copyValueOf(mHotSearch.getData().get(i).getName().toCharArray(), 0, 8));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                    , LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.leftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, this.getResources().getDisplayMetrics());
            lp.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, this.getResources().getDisplayMetrics());
            lp.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, this.getResources().getDisplayMetrics());
            lp.bottomMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, this.getResources().getDisplayMetrics());

            tv.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
            tv.setBackgroundResource(R.drawable.search_hot_bg);
            hotFloat.addView(tv, lp);
        }
    }

    private void findId() {
        hotFloat = (FloatLayout) findViewById(R.id.search_hot_floatLayout);
        mEdit = (EditText) findViewById(R.id.search_edit);
        search = (TextView) findViewById(R.id.search_search);
        recyclerView_search = (RecyclerView) findViewById(R.id.recyclerView_search);
        search_back = (ImageView) findViewById(R.id.search_back);
        search_delete = (TextView) findViewById(R.id.search_delete);

        search_delete.setOnClickListener(this);
        search.setOnClickListener(this);
        search_back.setOnClickListener(this);
        hotFloat.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_search:
                String text = mEdit.getText().toString().trim();
                if (!text.equals("")) {
                    Toast.makeText(this, "点击了搜索" + text, Toast.LENGTH_SHORT).show();
                    historyDatas.add(text);
                    adapter.notifyDataSetChanged();
                    SharedPreferencesHelp.saveHistory(this, historyDatas);
                    startSearchActivity(text);
                }
                break;
            case R.id.search_back:
                finish();
                break;
            case R.id.search_delete:
                historyDatas.clear();
                adapter.notifyDataSetChanged();
                SharedPreferencesHelp.saveHistory(this, historyDatas);
                break;
        }
    }

    /**
     * 调起搜索结果页面
     *
     * @param commodityName
     */
    private void startSearchActivity(String commodityName) {
        if (!NetUtils.isConnected(this)) {
            Log.i("Fragment_Home", "无网络连接");
            Toast.makeText(this, "请检查网络连接", Toast.LENGTH_SHORT).show();
        } else {
            Log.i("Fragment_Home", "有网络连接");
            Intent startSearchResultActivity = new Intent(this, SearchResultActivity.class);
            startSearchResultActivity.putExtra("commodityName", commodityName);
            startActivity(startSearchResultActivity);
        }
    }


    @Override
    public void onClickListener(View view, int position) {
        mEdit.setText(historyDatas.get(position));
    }

    @Override
    public void onItemClickListener(View v, int position) {
        mEdit.setText(mHotSearch.getData().get(position).getName());
        if (mHotSearch.getData() != null && mHotSearch.getData().get(position) != null) {
            String gid = mHotSearch.getData().get(position).getGid();
            if (gid != null || !gid.equals("")) {
                if (!NetUtils.isConnected(this)) {
                    Log.i("Fragment_Home", "无网络连接");
                    Toast.makeText(this, "请检查网络连接", Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("Fragment_Home", "有网络连接");
                    Intent startCommodityDetails = new Intent(this, CommodityDetailsActivity.class);
                    startCommodityDetails.putExtra("gid", gid);
                    startActivity(startCommodityDetails);
                }
               
            }
        }

    }

    @Override
    public void getHotSearchSuccess(HotSearch search) {
        mHotSearch = search;
        drawHotFloat();
    }

    @Override
    public void getHotSearchFailed(String error) {
//        drawHotFloat();
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
}
