package com.lishi.baijiaxing.details.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lishi.baijiaxing.R;

import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 */
public class FragmentBriefAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Integer> mSrcs;
    private LayoutInflater mLayoutInflater;

    public FragmentBriefAdapter(Context context, List<Integer> srcs) {
        this.mContext = context;
        this.mSrcs = srcs;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FragmentBriefViewHolder(mLayoutInflater.inflate(R.layout.commodity_details4, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FragmentBriefViewHolder viewHolder = (FragmentBriefViewHolder) holder;
        viewHolder.brief.setImageResource(mSrcs.get(position));
    }

    @Override
    public int getItemCount() {
        return mSrcs.size();
    }

    class FragmentBriefViewHolder extends RecyclerView.ViewHolder {
        ImageView brief;

        public FragmentBriefViewHolder(View itemView) {
            super(itemView);
            brief = (ImageView) itemView.findViewById(R.id.commodity_details_brief);
        }
    }
}
