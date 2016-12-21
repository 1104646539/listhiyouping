package com.lishi.baijiaxing.search.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/15.
 */

public class SearchAdapter extends RecyclerView.Adapter {
    private List<String> mDatas;
    private LayoutInflater mLayoutInflater;
    private YiYuanHotAdapter.OnItemClickListener mOnItemClickListener;

    public SearchAdapter(Context context, List<String> datas) {
        this.mDatas = datas;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(YiYuanHotAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchViewHolder(mLayoutInflater.inflate(R.layout.search_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SearchViewHolder viewHolder = (SearchViewHolder) holder;
        viewHolder.mTextView.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public SearchViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.search_data);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onClickListener(v, getPosition());
                    }
                }
            });
        }
    }
}
