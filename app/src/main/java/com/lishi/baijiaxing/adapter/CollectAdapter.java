package com.lishi.baijiaxing.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.GetChars;
import android.text.style.TtsSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lishi.baijiaxing.R;

import java.util.Set;

import dalvik.system.DexClassLoader;

/**
 * Created by Administrator on 2016/7/1.
 */
public class CollectAdapter extends BaseAdapter {

    private Context mContext;
    private int[] datas;

    public CollectAdapter(Context context, int[] srcid) {
        mContext = context;
        datas = srcid;
    }

    @Override
    public int getCount() {
        return 4;
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
        Collect1ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_collect1, null, false);
            holder = new Collect1ViewHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.iv_collect_gridview);
            convertView.setTag(holder);
        } else {
            holder = (Collect1ViewHolder) convertView.getTag();
        }
        holder.iv.setImageResource(datas[position]);
        return convertView;
    }

    class Collect1ViewHolder {
        ImageView iv;
    }
}
