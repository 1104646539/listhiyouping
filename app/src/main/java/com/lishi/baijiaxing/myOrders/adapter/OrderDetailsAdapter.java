package com.lishi.baijiaxing.myOrders.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.myOrders.model.OrderDetails;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrderCommodityBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */
public class OrderDetailsAdapter extends BaseAdapter {
    private Context mContext;
    private List<OrderDetails.DataBean.CommodityListBean> mCommodityBeen;
    private LayoutInflater mLayoutInflater;

    public OrderDetailsAdapter(Context context, List<OrderDetails.DataBean.CommodityListBean> commodityBeen) {
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
            convertView = mLayoutInflater.inflate(R.layout.item_myorder, parent, false);
            viewHolder.tv_number = (TextView) convertView.findViewById(R.id.order_details_commodity_num);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.order_details_commodity_price);
            viewHolder.iv_photo = (ImageView) convertView.findViewById(R.id.order_details_commodity_photo);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.order_details_commodity_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (OrderDetailsViewHolder) convertView.getTag();
        }
        OrderDetails.DataBean.CommodityListBean commodityBean = mCommodityBeen.get(position);

        viewHolder.tv_name.setText(commodityBean.getName());
        viewHolder.tv_number.setText("x  " + commodityBean.getBuyNum());
        viewHolder.tv_price.setText("￥" + commodityBean.getPrice());
        Glide.with(mContext).load(commodityBean.getPhotoUrl()).into(viewHolder.iv_photo);

        return convertView;
    }

    class OrderDetailsViewHolder {
        TextView tv_number, tv_price, tv_name;
        ImageView iv_photo;
    }
}