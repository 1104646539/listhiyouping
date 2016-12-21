package com.lishi.baijiaxing.pay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.pay.model.PayType;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrder;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/12/9.
 */

public class PayAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<PayType> mPayTypes;
    private SubmitOrder.DataBean mOrder;
    private YiYuanHotAdapter.OnItemClickListener mOnItemClickListener;

    private static final int TYPE_ORDER = 0x111;
    private static final int TYPE_PAY = 0x1111;

    public SubmitOrder.DataBean getOrder() {
        return mOrder;
    }

    public void setOrder(SubmitOrder.DataBean order) {
        mOrder = order;
    }

    public PayAdapter(Context context, List<PayType> payTypes) {
        this.mContext = context;
        this.mPayTypes = payTypes;
    }

    public void setOnItemClickListener(YiYuanHotAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ORDER) {
            return new PayOrderViewHolder(LayoutInflater.from(mContext).inflate(R.layout.pay_item_0, parent, false));
        }
        return new PayViewHolder(LayoutInflater.from(mContext).inflate(R.layout.pay_item_1, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ORDER;
        }
        return TYPE_PAY;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PayOrderViewHolder) {
            PayOrderViewHolder viewHolder = (PayOrderViewHolder) holder;
            viewHolder.totalPrice.setText(Double.valueOf(mOrder.getPrice()) + "元");
        } else if (holder instanceof PayViewHolder) {
            PayType payType = mPayTypes.get(position - 1);
            PayViewHolder viewHolder = (PayViewHolder) holder;
            viewHolder.brief.setText(payType.getBrief());
            viewHolder.name.setText(payType.getName());
            viewHolder.icon.setImageResource(payType.getSrcId());
        }
    }

    @Override
    public int getItemCount() {
        return mPayTypes.size() + 1;
    }

    class PayOrderViewHolder extends RecyclerView.ViewHolder {
        TextView totalPrice;

        public PayOrderViewHolder(View itemView) {
            super(itemView);
            totalPrice = (TextView) itemView.findViewById(R.id.pay_item0_totalPrice);
        }
    }

    class PayViewHolder extends RecyclerView.ViewHolder {
        TextView name, brief;
        ImageView icon;

        public PayViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.pay_item1_name);
            brief = (TextView) itemView.findViewById(R.id.pay_item1_brief);
            icon = (ImageView) itemView.findViewById(R.id.pay_item1_icon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onClickListener(v, getPosition());
                    }
                }
            });
        }
    }

}
