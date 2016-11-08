package com.lishi.baijiaxing.classify.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.classify.view.Fragment_Classify;

/**
 * Created by Administrator on 2016/6/12.
 */
public class OneClassifyAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private String[] mDatas;
    private Context mContext;
    
    public OneClassifyAdapter(Context context, String[] datas) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mDatas.length;
    }

    @Override
    public Object getItem(int position) {
        return mDatas[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.classity_onetitle, null, false);
        TextView tv = (TextView) view.findViewById(R.id.tv_oneclass_title);
        tv.setText(mDatas[position]);
        if (position == Fragment_Classify.mPosition){
            view.setBackgroundResource(R.color.item_padding);
        }else{
            view.setBackgroundResource(R.color.base_bg_color);
        }
        return view;
    }


    class OneClassViewHolder {
        private TextView mTextView_title;
    }
}
