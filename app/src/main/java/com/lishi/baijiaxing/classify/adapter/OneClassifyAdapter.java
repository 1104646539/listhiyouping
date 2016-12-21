package com.lishi.baijiaxing.classify.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.classify.model.ClassList;
import com.lishi.baijiaxing.classify.view.Fragment_Classify;

import java.util.List;

/**
 * Created by Administrator on 2016/6/12.
 */
public class OneClassifyAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ClassList.DataBean> mDatas;

    public OneClassifyAdapter(Context context, List<ClassList.DataBean> datas) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.classity_onetitle, parent, false);
        TextView tv = (TextView) view.findViewById(R.id.tv_oneclass_title);
        tv.setText(mDatas.get(position).getClassifyname());
        if (position == Fragment_Classify.mPosition) {
            view.setBackgroundResource(R.color.item_padding);
        } else {
            view.setBackgroundResource(R.color.base_bg_color);
        }
        return view;
    }
}
