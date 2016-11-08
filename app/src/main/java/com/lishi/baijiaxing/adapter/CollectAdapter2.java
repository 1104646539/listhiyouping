package com.lishi.baijiaxing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lishi.baijiaxing.R;

/**
 * Created by Administrator on 2016/7/1.
 */
public class CollectAdapter2 extends BaseAdapter{
    private Context mContext;
    private int[] datas;

    public CollectAdapter2(Context context,int[] srcid) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_collect2, null, false);
            holder = new Collect1ViewHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.iv_collect_gridview2);
            convertView.setTag(holder);
        } else {
            holder = (Collect1ViewHolder) convertView.getTag();
        }
        holder.iv.setImageResource(datas[position]);
        if (position == 0 || position ==1){
            holder.iv.setPadding(3,0,3,3);
        }else{
            holder.iv.setPadding(3,3,3,0);
        }
        return convertView;
    }

    class Collect1ViewHolder {
        ImageView iv;
    }
}
