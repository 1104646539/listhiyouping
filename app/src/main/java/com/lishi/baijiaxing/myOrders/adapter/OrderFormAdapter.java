package com.lishi.baijiaxing.myOrders.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.activity.EvaluateActivity;
import com.lishi.baijiaxing.myOrders.model.MyOrderFormBean;
import com.lishi.baijiaxing.myOrders.model.MyOrderList;
import com.lishi.baijiaxing.refund.NotShipmentsRefundActivity;
import com.lishi.baijiaxing.refund.RefundProgressActivity;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.shoppingCart.model.StoreBean;
import com.lishi.baijiaxing.view.MyListView;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的订单适配器
 * Created by Administrator on 2016/8/3.
 */
public class OrderFormAdapter extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
//    private MyOrderList mMyOrderList;
    private List<MyOrderList.DataBean.OrderListBean> mDataBeens;
    private LayoutInflater mLayoutInflater;
    private int mPosition;


    private OnListItemClickListener mOnItemClickListener;
    /**
     * 待评价
     */
    private OnStayEvaluateItemClick mOnStayEvaluateItemClick;
    private OnStayPaymentItemClick mOnStayPaymentItemClick;
    private OnStayTakeGoodsItemClick mOnStayTakeGoodsItemClick;
    private OnReturnedGoodsItemClick mOnReturnedGoodsItemClick;

//    public OrderFormAdapter(Context context, MyOrderList myOrderList) {
//        this.mContext = context;
//        this.mMyOrderList = myOrderList;
//        this.mLayoutInflater = LayoutInflater.from(mContext);
//    }
    public OrderFormAdapter(Context context, List<MyOrderList.DataBean.OrderListBean> dataBeens) {
        this.mContext = context;
        this.mDataBeens = dataBeens;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setOnStayPaymentItemClick(OnStayPaymentItemClick onStayPaymentItemClick) {
        mOnStayPaymentItemClick = onStayPaymentItemClick;
    }

    public void setOnStayTakeGoodsItemClick(OnStayTakeGoodsItemClick onStayTakeGoodsItemClick) {
        mOnStayTakeGoodsItemClick = onStayTakeGoodsItemClick;
    }

    public void setOnReturnedGoodsItemClick(OnReturnedGoodsItemClick onReturnedGoodsItemClick) {
        mOnReturnedGoodsItemClick = onReturnedGoodsItemClick;
    }

    public void setOnStayEvaluateItemClick(OnStayEvaluateItemClick onStayEvaluateItemClick) {
        mOnStayEvaluateItemClick = onStayEvaluateItemClick;
    }

    @Override
    public int getCount() {
        return mDataBeens.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataBeens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
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
//            holder.btn_myorderform_checklogistics1 = (Button) convertView.findViewById(R.id.btn_myorderform_checklogistics1);
            holder.btn_myorderform_evaluate = (Button) convertView.findViewById(R.id.btn_myorderform_evaluate);

            holder.btn_myorderform_recommendgoods1 = (Button) convertView.findViewById(R.id.btn_myorderform_recommendgoods1);
            holder.btn_myorderform_againbuy = (Button) convertView.findViewById(R.id.btn_myorderform_againbuy);

            holder.btn_myorderform_recommendgoods2 = (Button) convertView.findViewById(R.id.btn_myorderform_recommendgoods2);
            holder.btn_myorderform_checkprogress = (Button) convertView.findViewById(R.id.btn_myorderform_checkprogress);

            holder.btn_myorderform_recommendgoods3 = (Button) convertView.findViewById(R.id.btn_myorderform_recommendgoods3);
            holder.btn_myorderform_refundtowhere = (Button) convertView.findViewById(R.id.btn_myorderform_refundtowhere);

            holder.btn_myorderform_recommendgoods1.setVisibility(View.GONE);
            holder.btn_myorderform_recommendgoods2.setVisibility(View.GONE);
            holder.btn_myorderform_recommendgoods3.setVisibility(View.GONE);
            holder.btn_myorderform_checklogistics.setVisibility(View.GONE);

            convertView.setTag(holder);
        } else {
            holder = (MyOrderFormViewHolder) convertView.getTag();
        }
        MyOrderList.DataBean.OrderListBean data = mDataBeens.get(position);

        if (data != null && data.getCommodityList() != null) {

            holder.iv_store_icon.setImageResource(R.drawable.icon_store);
            holder.tv_store_name.setText("订单编号:" + data.getOrderNumber());

            switchState(Integer.valueOf(data.getOrderStatus()), holder);
            OrderFormItemAdapter formItemAdapter = new OrderFormItemAdapter(data, position);
            holder.mMyListView.setAdapter(formItemAdapter);

            holder.tv_count_number.setText("共计" + countCommodityNum(data.getCommodityList()) + "件商品，实付款：");
            holder.tv_total_price.setText("￥:" + data.getTotalPrice() + "元");
            sTateListener(holder);
            setListener(holder, position);
        }
        return convertView;
    }

    
    private int countCommodityNum(List<MyOrderList.DataBean.OrderListBean.CommodityListBean> data) {
        int leng = data.size();
        int num = 0;
        for (int i = 0; i < leng; i++) {
            num += Integer.valueOf(data.get(i).getBuyNum());
        }
        return num > 0 ? num : 1;
    }

    /**
     * 设置监听
     *
     * @param holder
     */
    private void setListener(MyOrderFormViewHolder holder, final int position) {
//        holder.btn_myorderform_evaluate.setOnClickListener(this);
        //代付款
//        holder.btn_cancel.setOnClickListener(this);//取消订单
//        holder.btn_payment.setOnClickListener(this);//付款

        holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnStayPaymentItemClick != null) {
                    mOnStayPaymentItemClick.onBottom1(v, position);
                }
            }
        });
        holder.btn_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnStayPaymentItemClick != null) {
                    mOnStayPaymentItemClick.onBottom2(v, position);
                }
            }
        });

        //已发货
//        holder.btn_myorderform_applyaftersale.setOnClickListener(this);//申请售后
//        holder.btn_myorderform_checklogistics.setOnClickListener(this);//查看物流
//        holder.btn_myorderform_affirmtakegoods.setOnClickListener(this);//确定收货

        holder.btn_myorderform_applyaftersale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnStayTakeGoodsItemClick != null) {
                    mOnStayTakeGoodsItemClick.onBottom3(v, position);
                }
            }
        });
        holder.btn_myorderform_checklogistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnStayTakeGoodsItemClick != null) {
                    mOnStayTakeGoodsItemClick.onBottom4(v, position);
                }
            }
        });
        holder.btn_myorderform_affirmtakegoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnStayTakeGoodsItemClick != null) {
                    mOnStayTakeGoodsItemClick.onBottom5(v, position);
                }
            }
        });


        //待发货
//        holder.btn_myorderform_applyaftersale1.setOnClickListener(this);//申请售后
//        holder.btn_myorderform_remindshipments.setOnClickListener(this);//提醒发货
        holder.btn_myorderform_applyaftersale1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnStayTakeGoodsItemClick != null) {
                    mOnStayTakeGoodsItemClick.onBottom1(v, position);
                }
            }
        });
        holder.btn_myorderform_remindshipments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnStayTakeGoodsItemClick != null) {
                    mOnStayTakeGoodsItemClick.onBottom2(v, position);
                }
            }
        });

        //待评价
//        holder.btn_myorderform_deleteorderform.setOnClickListener(this);//删除订单
//        holder.btn_myorderform_evaluate.setOnClickListener(this);//评价

        holder.btn_myorderform_deleteorderform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnStayEvaluateItemClick != null) {
                    mOnStayEvaluateItemClick.onBottom1(v, position);
                }
            }
        });
        holder.btn_myorderform_evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnStayEvaluateItemClick != null) {
                    mOnStayEvaluateItemClick.onBottom2(v, position);
                }
            }
        });


        //完成交易
        holder.btn_myorderform_recommendgoods1.setOnClickListener(this);//宝贝推荐
        holder.btn_myorderform_againbuy.setOnClickListener(this);//再次购买


        //退款中
        holder.btn_myorderform_recommendgoods2.setOnClickListener(this);//宝贝推荐
        holder.btn_myorderform_checkprogress.setOnClickListener(this);//进度查询
        holder.btn_myorderform_recommendgoods2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnReturnedGoodsItemClick != null) {
                    mOnReturnedGoodsItemClick.onBottom1(v, position);
                }
            }
        });
        holder.btn_myorderform_checkprogress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnReturnedGoodsItemClick != null) {
                    mOnReturnedGoodsItemClick.onBottom2(v, position);
                }
            }
        });


        //退款完成
        holder.btn_myorderform_recommendgoods3.setOnClickListener(this);//宝贝推荐
        holder.btn_myorderform_refundtowhere.setOnClickListener(this);//查询退款去向
    }


    /**
     * 监听各种状态
     *
     * @param
     */
    private void sTateListener(MyOrderFormViewHolder holder) {
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

                holder.tv_stayshipments.setVisibility(View.GONE);
                holder.ll_stayshipments.setVisibility(View.GONE);
                holder.ll_yetshipments.setVisibility(View.GONE);
                holder.tv_refundin.setVisibility(View.GONE);
                holder.ll_refundin.setVisibility(View.GONE);
                holder.tv_dealfinish.setVisibility(View.GONE);
                holder.ll_dealfinish.setVisibility(View.GONE);
                holder.tv_yetshipments.setVisibility(View.GONE);
                break;
            case MyOrderFormBean.STAYSHIPMENTS://待发货
                holder.tv_stayshipments.setVisibility(View.VISIBLE);
                holder.ll_stayshipments.setVisibility(View.VISIBLE);
                holder.btn_myorderform_applyaftersale.setVisibility(View.VISIBLE);

                holder.ll_yetshipments.setVisibility(View.GONE);
                holder.tv_refundin.setVisibility(View.GONE);
                holder.ll_refundin.setVisibility(View.GONE);
                holder.tv_dealfinish.setVisibility(View.GONE);
                holder.ll_dealfinish.setVisibility(View.GONE);
                holder.tv_yetshipments.setVisibility(View.GONE);
                holder.tv_payment.setVisibility(View.GONE);
                holder.ll_payment.setVisibility(View.GONE);
                holder.tv_yetshipments.setVisibility(View.GONE);
                holder.tv_stayevaluate.setVisibility(View.GONE);
                holder.ll_stayevaluate.setVisibility(View.GONE);
                break;
            case MyOrderFormBean.YETSHIPMENTS://已发货
                holder.tv_yetshipments.setVisibility(View.VISIBLE);
                holder.ll_yetshipments.setVisibility(View.VISIBLE);
                holder.btn_myorderform_applyaftersale1.setVisibility(View.VISIBLE);

                holder.tv_stayshipments.setVisibility(View.GONE);
                holder.ll_stayshipments.setVisibility(View.GONE);
                holder.tv_refundin.setVisibility(View.GONE);
                holder.ll_refundin.setVisibility(View.GONE);
                holder.tv_dealfinish.setVisibility(View.GONE);
                holder.ll_dealfinish.setVisibility(View.GONE);
                holder.tv_stayevaluate.setVisibility(View.GONE);
                holder.tv_payment.setVisibility(View.GONE);
                break;
            case MyOrderFormBean.STAYEVALUATE://待评价
            case MyOrderFormBean.DEALFINISH://完成交易
                holder.tv_stayevaluate.setVisibility(View.VISIBLE);
                holder.ll_stayevaluate.setVisibility(View.VISIBLE);

                holder.tv_refundin.setVisibility(View.GONE);
                holder.ll_refundin.setVisibility(View.GONE);
                holder.tv_dealfinish.setVisibility(View.GONE);
                holder.ll_dealfinish.setVisibility(View.GONE);
                holder.tv_yetshipments.setVisibility(View.GONE);
                holder.tv_stayshipments.setVisibility(View.GONE);
                holder.tv_payment.setVisibility(View.GONE);
                break;
            case MyOrderFormBean.REFUNDFINISH://退款完成
                holder.tv_refundfinish.setVisibility(View.VISIBLE);
                holder.ll_refundfinish.setVisibility(View.VISIBLE);

                holder.tv_refundin.setVisibility(View.GONE);
                holder.ll_refundin.setVisibility(View.GONE);
                holder.tv_dealfinish.setVisibility(View.GONE);
                holder.ll_dealfinish.setVisibility(View.GONE);
                holder.tv_stayevaluate.setVisibility(View.GONE);
                holder.tv_yetshipments.setVisibility(View.GONE);
                holder.tv_stayshipments.setVisibility(View.GONE);
                holder.tv_payment.setVisibility(View.GONE);
                break;
            case MyOrderFormBean.REFUNDIN://退款中
                holder.tv_refundin.setVisibility(View.VISIBLE);
                holder.ll_refundin.setVisibility(View.VISIBLE);

                holder.tv_refundfinish.setVisibility(View.GONE);
                holder.ll_refundfinish.setVisibility(View.GONE);
                holder.tv_dealfinish.setVisibility(View.GONE);
                holder.tv_stayevaluate.setVisibility(View.GONE);
                holder.tv_yetshipments.setVisibility(View.GONE);
                holder.tv_stayshipments.setVisibility(View.GONE);
                holder.tv_payment.setVisibility(View.GONE);
                break;
//            case MyOrderFormBean.DEALFINISH://完成交易
//                holder.tv_dealfinish.setVisibility(View.VISIBLE);
//                holder.ll_dealfinish.setVisibility(View.VISIBLE);
//
//                holder.tv_refundfinish.setVisibility(View.GONE);
//                holder.ll_refundfinish.setVisibility(View.GONE);
//                holder.tv_refundin.setVisibility(View.GONE);
//                holder.ll_refundin.setVisibility(View.GONE);
//                holder.tv_stayevaluate.setVisibility(View.GONE);
//                holder.tv_yetshipments.setVisibility(View.GONE);
//                holder.tv_stayshipments.setVisibility(View.GONE);
//                holder.tv_payment.setVisibility(View.GONE);
//                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btn_myorderform_evaluate://评论
//                Intent startEvaluateActivity = new Intent(mContext, EvaluateActivity.class);
//                startEvaluateActivity.putExtra("data", mMyOrderList.getData().get(mPosition).getOid());
//                mContext.startActivity(startEvaluateActivity);
//                break;
//            case R.id.btn_myorderform_deleteorderform://待评价->删除订单
//                if (mOnStayEvaluateItemClick != null) {
//                    mOnStayEvaluateItemClick.onBottom1(v, mPosition);
//                }
//                break;
//            case R.id.btn_myorderform_applyaftersale://已发货->申请售后
////                Intent startNotShipmentsRefund1Activity = new Intent(mContext, NotShipmentsRefundActivity.class);
////                mContext.startActivity(startNotShipmentsRefund1Activity);
//                break;
//            case R.id.btn_myorderform_applyaftersale1://未发货->申请售后
//                Intent startNotShipmentsRefund2Activity = new Intent(mContext, NotShipmentsRefundActivity.class);
//                mContext.startActivity(startNotShipmentsRefund2Activity);
//                break;
//            case R.id.btn_myorderform_checkprogress://进度查询
//                Intent startRefundProgressActivity = new Intent(mContext, RefundProgressActivity.class);
//                mContext.startActivity(startRefundProgressActivity);
//                break;
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
        //        Button btn_myorderform_checklogistics1;//查看物流
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
        MyOrderList.DataBean.OrderListBean mCommodityListBeen;
        int mPosition;

        public OrderFormItemAdapter(MyOrderList.DataBean.OrderListBean commodityListBeen, int position) {
            this.mCommodityListBeen = commodityListBeen;
            this.mPosition = position;
        }

        @Override
        public int getCount() {
            if (mCommodityListBeen == null || mCommodityListBeen.getCommodityList() == null) {
                return 0;
            }
            return mCommodityListBeen.getCommodityList().size();
        }

        @Override
        public Object getItem(int position) {
            return mCommodityListBeen.getCommodityList().get(position);
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
            Glide.with(mContext).load(mCommodityListBeen.getCommodityList().get(position).getPhotoUrl()).into(holder.iv_photo);
            holder.tv_title.setText(mCommodityListBeen.getCommodityList().get(position).getName());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onListItemClickListener(v, mCommodityListBeen.getOid(), Integer.valueOf(
                                mCommodityListBeen.getOrderStatus()), mPosition);
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


    public void setOnItemClickListener(OnListItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnListItemClickListener {
        void onListItemClickListener(View v, String oid, int state, int position);
    }


    public interface OnStayEvaluateItemClick {
        void onListItemClickListener(View v, StoreBean storeBean);

        void onBottom1(View v, int position);

        void onBottom2(View v, int position);
    }

    public interface OnStayPaymentItemClick {
        void onListItemClickListener(View v, StoreBean storeBean);

        void onBottom1(View v, int position);//取消订单

        void onBottom2(View v, int position);//付款
    }

    public interface OnStayTakeGoodsItemClick {
        void onListItemClickListener(View v, StoreBean storeBean);

        void onBottom1(View v, int position);//申请售后

        void onBottom2(View v, int position);//提醒发货

        void onBottom3(View v, int position);//申请售后

        void onBottom4(View v, int position);//查看物流

        void onBottom5(View v, int position);// 确定收货
    }

    public interface OnReturnedGoodsItemClick {
        void onListItemClickListener(View v, StoreBean storeBean);

        void onBottom1(View v, int position);

        void onBottom2(View v, int position);
    }


}
