package com.lishi.baijiaxing.myOrders.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.activity.EvaluateActivity;
import com.lishi.baijiaxing.myOrders.model.MyOrderFormBean;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.shoppingCart.model.StoreBean;
import com.lishi.baijiaxing.view.MyListView;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

import java.util.ArrayList;

/**
 * 我的订单适配器
 * Created by Administrator on 2016/8/3.
 */
public class OrderFormAdpter extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    private ArrayList<MyOrderFormBean> mMyOrderFormBeens;
    private LayoutInflater mLayoutInflater;
    private int mPosition;

    public OrderFormAdpter(Context context, ArrayList<MyOrderFormBean> myOrderFormBeans) {
        this.mContext = context;
        this.mMyOrderFormBeens = myOrderFormBeans;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mMyOrderFormBeens.size();
    }

    @Override
    public Object getItem(int position) {
        return mMyOrderFormBeens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public int getPosition() {
        return mPosition;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyOrderFormViewHolder holder = null;
        mPosition = position;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_myorderform, parent, false);
            holder = new MyOrderFormViewHolder();
            holder.iv_store_icon = (ImageView) convertView.findViewById(R.id.iv_myorderform_storeicon);
            holder.tv_store_name = (TextView) convertView.findViewById(R.id.tv_myorderform_storename);
            holder.tv_count_number = (TextView) convertView.findViewById(R.id.tv_myorderform_countnumber);
            holder.tv_total_price = (TextView) convertView.findViewById(R.id.tv_myorderform_totalprice);
            holder.btn_cancel = (Button) convertView.findViewById(R.id.btn_myorderform_cancel);
            holder.btn_payment = (Button) convertView.findViewById(R.id.btn_myorderform_payment);
            holder.mMyListView = (MyListView) convertView.findViewById(R.id.listview_myorderform_item);

            holder.tv_payment = (TextView) convertView.findViewById(R.id.tv_myorderform_payment);
            holder.tv_stayshipments = (TextView) convertView.findViewById(R.id.tv_myorderform_stayshipments);
            holder.tv_yetshipments = (TextView) convertView.findViewById(R.id.tv_myorderform_yetshipments);
            holder.tv_stayevaluate = (TextView) convertView.findViewById(R.id.tv_myorderform_stayevaluate);
            holder.tv_refundfinish = (TextView) convertView.findViewById(R.id.tv_myorderform_refundfinish);
            holder.tv_dealfinish = (TextView) convertView.findViewById(R.id.tv_myorderform_dealfinish);
            holder.tv_refundin = (TextView) convertView.findViewById(R.id.tv_myorderform_refundin);

            holder.ll_payment = (LinearLayout) convertView.findViewById(R.id.ll_staypayment);
            holder.ll_stayshipments = (LinearLayout) convertView.findViewById(R.id.ll_stayshipments);
            holder.ll_yetshipments = (LinearLayout) convertView.findViewById(R.id.ll_yetshipments);
            holder.ll_stayevaluate = (LinearLayout) convertView.findViewById(R.id.ll_stayevaluate);
            holder.ll_refundfinish = (LinearLayout) convertView.findViewById(R.id.ll_refundfinish);
            holder.ll_dealfinish = (LinearLayout) convertView.findViewById(R.id.ll_dealfinish);
            holder.ll_refundin = (LinearLayout) convertView.findViewById(R.id.ll_refundin);

            holder.btn_cancel = (Button) convertView.findViewById(R.id.btn_myorderform_cancel);
            holder.btn_payment = (Button) convertView.findViewById(R.id.btn_myorderform_payment);

            holder.btn_myorderform_applyaftersale = (Button) convertView.findViewById(R.id.btn_myorderform_applyaftersale);
            holder.btn_myorderform_checklogistics = (Button) convertView.findViewById(R.id.btn_myorderform_checklogistics);
            holder.btn_myorderform_affirmtakegoods = (Button) convertView.findViewById(R.id.btn_myorderform_affirmtakegoods);

            holder.btn_myorderform_applyaftersale1 = (Button) convertView.findViewById(R.id.btn_myorderform_applyaftersale1);
            holder.btn_myorderform_remindshipments = (Button) convertView.findViewById(R.id.btn_myorderform_remindshipments);

            holder.btn_myorderform_deleteorderform = (Button) convertView.findViewById(R.id.btn_myorderform_deleteorderform);
            holder.btn_myorderform_checklogistics1 = (Button) convertView.findViewById(R.id.btn_myorderform_checklogistics1);
            holder.btn_myorderform_evaluate = (Button) convertView.findViewById(R.id.btn_myorderform_evaluate);

            holder.btn_myorderform_recommendgoods1 = (Button) convertView.findViewById(R.id.btn_myorderform_recommendgoods1);
            holder.btn_myorderform_againbuy = (Button) convertView.findViewById(R.id.btn_myorderform_againbuy);

            holder.btn_myorderform_recommendgoods2 = (Button) convertView.findViewById(R.id.btn_myorderform_recommendgoods2);
            holder.btn_myorderform_checkprogress = (Button) convertView.findViewById(R.id.btn_myorderform_checkprogress);

            holder.btn_myorderform_recommendgoods3 = (Button) convertView.findViewById(R.id.btn_myorderform_recommendgoods3);
            holder.btn_myorderform_refundtowhere = (Button) convertView.findViewById(R.id.btn_myorderform_refundtowhere);

            convertView.setTag(holder);
        } else {
            holder = (MyOrderFormViewHolder) convertView.getTag();
        }
        MyOrderFormBean myOrderFormBean = mMyOrderFormBeens.get(position);

        holder.iv_store_icon.setImageResource(R.drawable.icon_store);
        holder.tv_store_name.setText(myOrderFormBean.getStoreBean().getCommStore());

        switchState(myOrderFormBean.getState(), holder);
        OrderFormItemAdapter formItemAdapter = new OrderFormItemAdapter(myOrderFormBean.getStoreBean());
        holder.mMyListView.setAdapter(formItemAdapter);

        holder.tv_count_number.setText("共计" + myOrderFormBean.getNumber() + "件商品，实付款：");
        holder.tv_total_price.setText("￥:" + myOrderFormBean.getTotalPrice());
        sTateListener(myOrderFormBean.getStoreBean(), holder);
        return convertView;
    }


    /**
     * 监听各种状态
     *
     * @param storeBean
     */
    private void sTateListener(StoreBean storeBean, MyOrderFormViewHolder holder) {
        holder.btn_myorderform_evaluate.setOnClickListener(this);
    }

    /**
     * 判断状态
     *
     * @param state
     */
    private void switchState(int state, MyOrderFormViewHolder holder) {
        switch (state) {
            case MyOrderFormBean.STAYPAYMENT://待付款
                holder.tv_payment.setVisibility(View.VISIBLE);
                holder.ll_payment.setVisibility(View.VISIBLE);
                break;
            case MyOrderFormBean.STAYSHIPMENTS://待发货
                holder.tv_stayshipments.setVisibility(View.VISIBLE);
                holder.ll_stayshipments.setVisibility(View.VISIBLE);

                holder.tv_yetshipments.setVisibility(View.GONE);
                holder.ll_yetshipments.setVisibility(View.GONE);
                break;
            case MyOrderFormBean.YETSHIPMENTS://已发货
                holder.tv_yetshipments.setVisibility(View.VISIBLE);
                holder.ll_yetshipments.setVisibility(View.VISIBLE);

                holder.tv_stayshipments.setVisibility(View.GONE);
                holder.ll_stayshipments.setVisibility(View.GONE);
                break;
            case MyOrderFormBean.STAYEVALUATE://待评价
                holder.tv_stayevaluate.setVisibility(View.VISIBLE);
                holder.ll_stayevaluate.setVisibility(View.VISIBLE);
                break;
            case MyOrderFormBean.REFUNDFINISH://退款完成
                holder.tv_refundfinish.setVisibility(View.VISIBLE);
                holder.ll_refundfinish.setVisibility(View.VISIBLE);

                holder.tv_refundin.setVisibility(View.GONE);
                holder.ll_refundin.setVisibility(View.GONE);
                holder.tv_dealfinish.setVisibility(View.GONE);
                holder.ll_dealfinish.setVisibility(View.GONE);
                break;
            case MyOrderFormBean.REFUNDIN://退款中
                holder.tv_refundin.setVisibility(View.VISIBLE);
                holder.ll_refundin.setVisibility(View.VISIBLE);

                holder.tv_refundfinish.setVisibility(View.GONE);
                holder.ll_refundfinish.setVisibility(View.GONE);
                holder.tv_dealfinish.setVisibility(View.GONE);
                holder.ll_dealfinish.setVisibility(View.GONE);
                break;
            case MyOrderFormBean.DEALFINISH://完成交易
                holder.tv_dealfinish.setVisibility(View.VISIBLE);
                holder.ll_dealfinish.setVisibility(View.VISIBLE);

                holder.tv_refundfinish.setVisibility(View.GONE);
                holder.ll_refundfinish.setVisibility(View.GONE);
                holder.tv_refundin.setVisibility(View.GONE);
                holder.ll_refundin.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_myorderform_evaluate://评论
                Intent startEvaluateActivity = new Intent(mContext, EvaluateActivity.class);
                startEvaluateActivity.putExtra("data", mMyOrderFormBeens.get(mPosition).getStoreBean());
                mContext.startActivity(startEvaluateActivity);
                break;
        }
    }


    private class MyOrderFormViewHolder {
        ImageView iv_store_icon;//店铺图标
        TextView tv_store_name;//店铺名字
        TextView tv_count_number;//总数量
        TextView tv_total_price;//总价
        MyListView mMyListView;

        TextView tv_payment;//去付款
        TextView tv_stayshipments;//待发货
        TextView tv_yetshipments;//已发货
        TextView tv_stayevaluate;//待评价
        TextView tv_refundfinish;//退款完成
        TextView tv_dealfinish;//完成交易
        TextView tv_refundin;//退款中

        LinearLayout ll_payment;//待付款
        LinearLayout ll_stayshipments;//待发货
        LinearLayout ll_yetshipments;//已发货
        LinearLayout ll_stayevaluate;//待评价
        LinearLayout ll_refundfinish;//退款完成
        LinearLayout ll_dealfinish;//完成交易
        LinearLayout ll_refundin;//退款中
        //代付款
        Button btn_cancel;//取消订单
        Button btn_payment;//付款
        //已发货
        Button btn_myorderform_applyaftersale;//申请售后
        Button btn_myorderform_checklogistics;//查看物流
        Button btn_myorderform_affirmtakegoods;//确定收货
        //待发货
        Button btn_myorderform_applyaftersale1;//申请售后
        Button btn_myorderform_remindshipments;//提醒发货
        //待评价
        Button btn_myorderform_deleteorderform;//删除订单
        Button btn_myorderform_checklogistics1;//查看物流
        Button btn_myorderform_evaluate;//评价
        //完成交易
        Button btn_myorderform_recommendgoods1;//宝贝推荐
        Button btn_myorderform_againbuy;//再次购买
        //退款中
        Button btn_myorderform_recommendgoods2;//宝贝推荐
        Button btn_myorderform_checkprogress;//进度查询
        //退款完成
        Button btn_myorderform_recommendgoods3;//宝贝推荐
        Button btn_myorderform_refundtowhere;//查询退款去向
    }


    class OrderFormItemAdapter extends BaseAdapter {
        StoreBean mStoreBean;

        public OrderFormItemAdapter(StoreBean storeBean) {
            this.mStoreBean = storeBean;
        }

        @Override
        public int getCount() {
            return mStoreBean.getCommodityBeanList().size();
        }

        @Override
        public Object getItem(int position) {
            return mStoreBean.getCommodityBeanList().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            OrderFormItemViewHolder holder = null;
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.item_myorderform_item, parent, false);
                holder = new OrderFormItemViewHolder();
                holder.iv_photo = (ImageView) convertView.findViewById(R.id.iv_item_myoderform_photo);
                holder.tv_title = (TextView) convertView.findViewById(R.id.tv_item_myoderform_title);
                convertView.setTag(holder);
            } else {
                holder = (OrderFormItemViewHolder) convertView.getTag();
            }
            holder.iv_photo.setImageResource(R.drawable.item_myorderform_photo);
            holder.tv_title.setText(mStoreBean.getCommodityBeanList().get(position).getCommTitle());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onListItemClickListener(v, mStoreBean);
                    }
                }
            });

            return convertView;
        }

        class OrderFormItemViewHolder {
            ImageView iv_photo;
            TextView tv_title;
        }
    }

    private OnListItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnListItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnListItemClickListener {
        void onListItemClickListener(View v, StoreBean storeBean);
    }

}
