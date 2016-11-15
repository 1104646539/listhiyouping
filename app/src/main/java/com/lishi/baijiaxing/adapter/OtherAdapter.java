package com.lishi.baijiaxing.adapter;

import android.content.Context;
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
public class OtherAdapter extends BaseAdapter {
    private String[] mTitles;
    private int[] mSrcids;
    private LayoutInflater mLayoutInflater;

    public OtherAdapter(Context context, String[] titles, int[] srcids) {
        mTitles = titles;
        mSrcids = srcids;
        mLayoutInflater = LayoutInflater.from(context);
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
            convertView = mLayoutInflater.inflate(R.layout.item_orderform, parent, false);
            holder = new OrderformViewHolder();
            holder.iv_src = (ImageView) convertView.findViewById(R.id.iv_orderform);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_orderform);
            convertView.setTag(holder);
        } else {
            holder = (OrderformViewHolder) convertView.getTag();
        }
        holder.iv_src.setImageResource(mSrcids[position]);
        holder.tv_title.setText(mTitles[position]);

        return convertView;
    }

    class OrderformViewHolder {
        ImageView iv_src;
        TextView tv_title;
    }
}
