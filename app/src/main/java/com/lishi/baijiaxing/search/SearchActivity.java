package com.lishi.baijiaxing.search;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.search.wedget.FloatLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索页面
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {
    private FloatLayout hotFloat, historyFloat;
    private List<String> hotDatas;
    private List<String> historyDatas;
    private EditText mEdit;
    private TextView search;
    private RecyclerView recyclerView_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    private void init() {
        findId();
        initData();
        initView();
    }

    private void initData() {
        hotDatas = new ArrayList<>();
        historyDatas = new ArrayList<>();
        hotDatas.clear();
        historyDatas.clear();

        hotDatas.add("尚雅保温壶");
        hotDatas.add("开门红保温");
    }

    private void initView() {
        drawHotFloat();

    }

    /**
     * 添加热门标签
     */
    private void drawHotFloat() {
        hotFloat.removeAllViews();
        int hCount = hotDatas.size();
        for (int i = 0; i < hCount; i++) {
            TextView tv = new TextView(this);
            tv.setText(hotDatas.get(i));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                    , LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.leftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, this.getResources().getDisplayMetrics());
            lp.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, this.getResources().getDisplayMetrics());
            lp.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, this.getResources().getDisplayMetrics());
            lp.bottomMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, this.getResources().getDisplayMetrics());

            tv.setBackgroundResource(R.drawable.search_hot_bg);
            hotFloat.addView(tv, lp);
        }
    }

    private void findId() {
        hotFloat = (FloatLayout) findViewById(R.id.search_hot_floatLayout);
        historyFloat = (FloatLayout) findViewById(R.id.search_history_floatLayout);
        mEdit = (EditText) findViewById(R.id.search_edit);
        search = (TextView) findViewById(R.id.search_search);
        recyclerView_search = (RecyclerView) findViewById(R.id.recyclerView_search);

        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_search:
                String text = mEdit.getText().toString().trim();
                if (text.equals("")) {
                    return;
                }
                Toast.makeText(this, "点击了搜索" + text, Toast.LENGTH_SHORT);
                historyDatas.add(text);
                break;
        }
    }
}
