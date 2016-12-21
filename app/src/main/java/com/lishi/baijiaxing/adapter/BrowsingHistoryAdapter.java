package com.lishi.baijiaxing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/4.
 */
public class BrowsingHistoryAdapter extends BaseAdapter {
    private Context mContext;
    private List<CommodityBean> mCommodityBeen;
    private LayoutInflater mLayoutInflater;

    public BrowsingHistoryAdapter(Context context, List<CommodityBean> commodityBeen) {
        mCommodityBeen = commodityBeen;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mCommodityBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return mCommodityBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BrowsingHistoryViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_browsinghistory, null, false);
            viewHolder = new BrowsingHistoryViewHolder();
            viewHolder.iv_brief = (ImageView) convertView.findViewById(R.id.iv_browsinghistory_photo);
            viewHolder.tv_info = (TextView) convertView.findViewById(R.id.tv_browsinghistory_title);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_browsinghistory_price);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (BrowsingHistoryViewHolder) convertView.getTag();
        }
        CommodityBean commodityBean = mCommodityBeen.get(position);
        viewHolder.iv_brief.setImageResource(R.drawable.classity_item);
//        viewHolder.tv_info.setText(commodityBean.getCommTitle());
//        viewHolder.tv_price.setText(commodityBean.getCommPrice() + "");
        return convertView;
    }

    class BrowsingHistoryViewHolder {
        ImageView iv_brief;
        TextView tv_info;
        TextView tv_price;
    }
}
