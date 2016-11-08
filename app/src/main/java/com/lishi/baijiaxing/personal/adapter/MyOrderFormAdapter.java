package com.lishi.baijiaxing.personal.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;

/**
 * Created by Administrator on 2016/6/20.
 */
public class MyOrderFormAdapter extends BaseAdapter {
    private Context mContext;
    private String[] mTitles;
    private int[] mSrcids;
    private LayoutInflater mLayoutInflater;

    public MyOrderFormAdapter(Context context, String[] titles, int[] srcids) {
        mTitles = titles;
        mSrcids = srcids;
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public Object getItem(int position) {
        return mTitles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderformViewHolder holder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_orderform, null, false);
            holder = new OrderformViewHolder();
            holder.iv_src = (ImageView) convertView.findViewById(R.id.iv_orderform);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_orderform);
            holder.iv_orderfrom_margin = (ImageView) convertView.findViewById(R.id.iv_orderform_margin);
            convertView.setTag(holder);
        } else {
            holder = (OrderformViewHolder) convertView.getTag();
        }
        holder.iv_src.setImageResource(mSrcids[position]);
        holder.tv_title.setText(mTitles[position]);
        holder.tv_title.setTextColor(Color.rgb(65, 60, 58));

        if (position == 3) {
            holder.iv_orderfrom_margin.setVisibility(View.VISIBLE);
        } else {
            holder.iv_orderfrom_margin.setVisibility(View.GONE);
        }

        return convertView;
    }

    class OrderformViewHolder {
        ImageView iv_src;
        TextView tv_title;
        ImageView iv_orderfrom_margin;
    }
}
