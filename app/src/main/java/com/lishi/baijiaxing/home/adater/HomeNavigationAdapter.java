package com.lishi.baijiaxing.home.adater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.bean.GridNavigationBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */

public class HomeNavigationAdapter extends BaseAdapter {
    private List<GridNavigationBean> mGridViewIds;
    private Context mContext;

    public HomeNavigationAdapter(Context context, List<GridNavigationBean> srcIds) {
        this.mContext = context;
        this.mGridViewIds = srcIds;

    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public Object getItem(int position) {
        return mGridViewIds.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_gridview_item, parent, false);
        ImageView iv = (ImageView) view.findViewById(R.id.iv_home_gridview);
        GridNavigationBean gridNavigation1 = mGridViewIds.get(position);
        iv.setImageResource(gridNavigation1.getSrcId());
        TextView tv = (TextView) view.findViewById(R.id.tv_home_gridview);
        tv.setText(gridNavigation1.getText());
        return view;
    }

    
}
