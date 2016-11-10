package com.lishi.baijiaxing.myOrders.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrderCommodityBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */
public class OrderDetailsAdapter extends BaseAdapter {
    private Context mContext;
    private List<CommodityBean> mCommodityBeen;
    private LayoutInflater mLayoutInflater;

    public OrderDetailsAdapter(Context context, List<CommodityBean> commodityBeen) {
        this.mContext = context;
        this.mCommodityBeen = commodityBeen;
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
        OrderDetailsViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new OrderDetailsViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.item_shoppinglist, parent, false);
            viewHolder.tv_number = (TextView) convertView.findViewById(R.id.submit_order_num);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.submit_order_price);
            viewHolder.iv_photo = (ImageView) convertView.findViewById(R.id.submit_order_photo);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.submit_order_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (OrderDetailsViewHolder) convertView.getTag();
        }
        CommodityBean commodityBean = mCommodityBeen.get(position);

        viewHolder.tv_name.setText(commodityBean.getCommTitle());
        viewHolder.tv_number.setText("数量:x  " + commodityBean.getCommNum());
        viewHolder.iv_photo.setImageResource(R.drawable.classity_item);
        viewHolder.tv_price.setText("￥" + commodityBean.getCommPrice());

        return convertView;
    }

    class OrderDetailsViewHolder {
        TextView tv_number, tv_price, tv_name;
        ImageView iv_photo;
    }
}