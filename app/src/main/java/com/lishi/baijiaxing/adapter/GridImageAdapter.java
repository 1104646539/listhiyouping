package com.lishi.baijiaxing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.lishi.baijiaxing.R;

/**
 * Created by Administrator on 2016/7/4.
 */
public class GridImageAdapter extends BaseAdapter {
    private Context mContext;
    private int[] mSrcIds;

    public GridImageAdapter(Context context, int[] srcIds) {
        mContext = context;
        mSrcIds = srcIds;

    }

    @Override
    public int getCount() {
        return mSrcIds.length;
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
        GridImageViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_image, null, false);
            holder = new GridImageViewHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.iv_image);
            convertView.setTag(holder);
        } else {
            holder = (GridImageViewHolder) convertView.getTag();
        }
        holder.iv.setImageResource(mSrcIds[position]);

        return convertView;
    }

    class GridImageViewHolder {
        ImageView iv;
    }
}
