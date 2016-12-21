package com.lishi.baijiaxing.search.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.search.model.SearchList;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

/**
 * Created by Administrator on 2016/12/20.
 */

public class SearchListAdapter extends RecyclerView.Adapter {
    private SearchList mSearchList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private YiYuanHotAdapter.OnItemClickListener mOnItemClickListener;

    public SearchListAdapter(Context context, SearchList searchList) {
        this.mContext = context;
        this.mSearchList = searchList;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchListViewHolder(mLayoutInflater.inflate(R.layout.item_search, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SearchListViewHolder viewHolder = (SearchListViewHolder) holder;
        SearchList.DataBean.CommodityListBean commodityListBean = mSearchList.getData().getCommodityList().get(position);
        if (commodityListBean != null) {
            viewHolder.tv_name.setText(commodityListBean.getName());
            viewHolder.tv_oldPrice.setText("原价:" + commodityListBean.getOldPrice());
            viewHolder.tv_nowPrice.setText("现价:" + commodityListBean.getNowPrice());
            Glide.with(mContext).load(commodityListBean.getPhotoUrl()).into(viewHolder.photo);
        }
    }

    @Override
    public int getItemCount() {
        return mSearchList.getData().getCommodityList().size();
    }

    class SearchListViewHolder extends RecyclerView.ViewHolder {
        TextView tv_oldPrice, tv_nowPrice, tv_name, tv_buy;
        ImageView photo;

        public SearchListViewHolder(View itemView) {
            super(itemView);
            tv_oldPrice = (TextView) itemView.findViewById(R.id.search_item_oldPrice);
            tv_nowPrice = (TextView) itemView.findViewById(R.id.search_item_nowPrice);
            tv_name = (TextView) itemView.findViewById(R.id.search_item_name);
            tv_buy = (TextView) itemView.findViewById(R.id.search_item_buy);
            photo = (ImageView) itemView.findViewById(R.id.search_item_photo);
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

    public void setOnItemClickListener(YiYuanHotAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
