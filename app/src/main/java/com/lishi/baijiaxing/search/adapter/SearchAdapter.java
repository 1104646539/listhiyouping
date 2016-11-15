package com.lishi.baijiaxing.search.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/11/15.
 */

public class SearchAdapter extends RecyclerView.Adapter {
    private List<String> mDatas;
    private LayoutInflater mLayoutInflater;

    public SearchAdapter(Context context, List<String> datas) {
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    
}
