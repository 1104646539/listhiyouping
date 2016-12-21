package com.lishi.baijiaxing.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.bean.MeaningfulBean;
import com.lishi.baijiaxing.view.MyGridView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/7/4.
 */
public class MeaningfulAdapter extends BaseAdapter {
    private Context mContext;
    private List<MeaningfulBean> mBeanList;
    private LayoutInflater mLayoutInflater;

    public MeaningfulAdapter(Context context, List<MeaningfulBean> meaningfulBeens) {
        mContext = context;
        mBeanList = meaningfulBeens;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return mBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MeaningfulViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_meaningful_recyclerview, parent, false);
            viewHolder = new MeaningfulViewHolder();
            viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_meaningful_icon);
            viewHolder.iv_brief = (ImageView) convertView.findViewById(R.id.iv_meaningful_brief);
            viewHolder.mGridView = (MyGridView) convertView.findViewById(R.id.gridview_meaningful_listview);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (MeaningfulViewHolder) convertView.getTag();
        }
        final MeaningfulBean bean = mBeanList.get(position);
        viewHolder.iv_icon.setImageResource(bean.getIcon());
        viewHolder.iv_brief.setImageResource(bean.getBriefImg());
        viewHolder.mGridView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return bean.getGoods().size();
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                MeaningfulGridViewViewHolder holder = null;
                if (convertView == null) {
                    convertView = mLayoutInflater.inflate(R.layout.item_meaningful_gridview, null, false);
                    holder = new MeaningfulGridViewViewHolder();
                    holder.iv = (ImageView) convertView.findViewById(R.id.iv_meaningful_gridview);
                    holder.iv_buy = (ImageView) convertView.findViewById(R.id.iv_meaningful_gridview_buy);
                    convertView.setTag(holder);
                } else {
                    holder = (MeaningfulGridViewViewHolder) convertView.getTag();
                }
                holder.iv.setImageResource(R.drawable.iv_meaningfu_grid2_2);
                holder.iv_buy.setImageResource(bean.getBuy());
                return convertView;
            }

            class MeaningfulGridViewViewHolder {
                ImageView iv;
                ImageView iv_buy;
            }
        });
        return convertView;
    }

    class MeaningfulViewHolder {
        ImageView iv_icon;
        ImageView iv_brief;
        MyGridView mGridView;
    }
}
