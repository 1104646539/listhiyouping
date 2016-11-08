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
import com.lishi.baijiaxing.bean.LogisticsBean;
import com.lishi.baijiaxing.bean.LogisticsStateBean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class LogisticsQueryAdapter extends BaseAdapter {

    private List<LogisticsBean> mLogisticsBeen;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public LogisticsQueryAdapter(Context context, List<LogisticsBean> logisticsBeens) {
        mContext = context;
        mLogisticsBeen = logisticsBeens;
        mLayoutInflater = LayoutInflater.from(mContext);

    }

    @Override
    public int getCount() {
        return mLogisticsBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return mLogisticsBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LogisticsQueryViewHolder holder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_logisticsquery, null, false);
            holder = new LogisticsQueryViewHolder();
            holder.iv_photo = (ImageView) convertView.findViewById(R.id.iv_logisticsquery_photo);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_logisticsquery_title);
            holder.tv_info = (TextView) convertView.findViewById(R.id.tv_logisticsquery_info);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_logisticsquery_price);
            holder.tv_query = (TextView) convertView.findViewById(R.id.tv_logisticsquery_query);
            holder.tv_confirm = (TextView) convertView.findViewById(R.id.tv_logisticsquery_confirm);
            holder.tv_wait = (TextView) convertView.findViewById(R.id.tv_logisticsquery_wait);
            convertView.setTag(holder);
        } else {
            holder = (LogisticsQueryViewHolder) convertView.getTag();
        }
        holder.iv_photo.setImageResource(R.drawable.classity_item);
        LogisticsBean logisticsBean = mLogisticsBeen.get(position);
        CommodityBean commodityBean = logisticsBean.getCommodityBean();
        holder.tv_title.setText(commodityBean.getCommTitle());
        holder.tv_info.setText(commodityBean.getCommInfo());
        holder.tv_price.setText(commodityBean.getCommPrice()+"");

        if (logisticsBean.getState() == LogisticsStateBean.Query) {//物流查询
            holder.tv_query.setVisibility(View.VISIBLE);
            holder.tv_confirm.setVisibility(View.GONE);
            holder.tv_wait.setVisibility(View.GONE);
        } else if (logisticsBean.getState() == LogisticsStateBean.CONFIRM) {//确认收货
            holder.tv_query.setVisibility(View.GONE);
            holder.tv_confirm.setVisibility(View.VISIBLE);
            holder.tv_wait.setVisibility(View.GONE);
        } else if (logisticsBean.getState() == LogisticsStateBean.WAIT) {//等待发货
            holder.tv_query.setVisibility(View.GONE);
            holder.tv_confirm.setVisibility(View.GONE);
            holder.tv_wait.setVisibility(View.VISIBLE);
        }


        return convertView;
    }

    class LogisticsQueryViewHolder {
        ImageView iv_photo;
        TextView tv_title;
        TextView tv_info;
        TextView tv_price;
        TextView tv_query, tv_confirm, tv_wait;
    }
}
