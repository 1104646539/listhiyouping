package com.lishi.baijiaxing.submitOrder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrderCommodityBean;

import java.util.List;

/**
 * 提交订单
 * Created by Administrator on 2016/7/18.
 */
public class SubmitOrderAdapter extends BaseAdapter {
    private Context mContext;
    private List<SubmitOrderCommodityBean> mSocbs;
    private LayoutInflater mLayoutInflater;

    public SubmitOrderAdapter(Context context, List<SubmitOrderCommodityBean> socbs) {
        this.mContext = context;
        this.mSocbs = socbs;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mSocbs.size();
    }

    @Override
    public Object getItem(int position) {
        return mSocbs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SubmitOrderViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new SubmitOrderViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.item_shoppinglist, parent, false);
            viewHolder.tv_number = (TextView) convertView.findViewById(R.id.submit_order_num);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.submit_order_price);
            viewHolder.iv_photo = (ImageView) convertView.findViewById(R.id.submit_order_photo);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.submit_order_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SubmitOrderViewHolder) convertView.getTag();
        }
        SubmitOrderCommodityBean submitOrderCommodityBean = mSocbs.get(position);

        viewHolder.tv_name.setText(submitOrderCommodityBean.getCommName());
        viewHolder.tv_number.setText("数量:x  " + submitOrderCommodityBean.getBuyNum());
        viewHolder.iv_photo.setImageResource(R.drawable.classity_item);
        viewHolder.tv_price.setText("￥" + submitOrderCommodityBean.getCommPrice());

        return convertView;
    }

    class SubmitOrderViewHolder {
        TextView tv_number, tv_price, tv_name;
        ImageView iv_photo;
    }
}
