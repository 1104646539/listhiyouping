package com.lishi.baijiaxing.refund.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.refund.model.RefundProgressBean;

/**
 * Created by Administrator on 2016/11/18.
 */

public class RefundProgressAdapter extends RecyclerView.Adapter {
    private RefundProgressBean mRefundProgressBean;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public RefundProgressAdapter(Context context, RefundProgressBean queryBean) {
        this.mContext = context;
        this.mRefundProgressBean = queryBean;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RefundProgressViewHolder(mLayoutInflater.inflate(R.layout.free_design_timeline, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RefundProgressViewHolder) {
            RefundProgressViewHolder viewHolder = (RefundProgressViewHolder) holder;
            if (position == 0) {
                if (mRefundProgressBean.getType() == RefundProgressBean.TYPE_DISPOSE_ING) {
                    viewHolder.iv.setImageResource(R.drawable.free_design_timeline_true);
                    viewHolder.title.setTextColor(Color.parseColor("#e10012"));
                    viewHolder.info.setTextColor(Color.parseColor("#e10012"));
                } else {
                    viewHolder.iv.setImageResource(R.drawable.free_design_timeline_false);
                    viewHolder.title.setTextColor(Color.parseColor("#818181"));
                    viewHolder.info.setTextColor(Color.parseColor("#818181"));
                }
                viewHolder.line.setVisibility(View.VISIBLE);
                viewHolder.title.setText("处理中");
                viewHolder.info.setText("您的订单正在生产，请耐心等待");

                RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) viewHolder.itemView.getLayoutParams();
                lp.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, mContext.getResources().getDisplayMetrics());
                LinearLayout.LayoutParams vlp = (LinearLayout.LayoutParams) viewHolder.iv.getLayoutParams();
                vlp.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, mContext.getResources().getDisplayMetrics());
            } else if (position == 1) {
                if (mRefundProgressBean.getType() == RefundProgressBean.TYPE_DISPOSE_SUCCESS) {
                    viewHolder.iv.setImageResource(R.drawable.free_design_timeline_true);
                    viewHolder.title.setTextColor(Color.parseColor("#e10012"));
                    viewHolder.info.setTextColor(Color.parseColor("#e10012"));
                } else {
                    viewHolder.iv.setImageResource(R.drawable.free_design_timeline_false);
                    viewHolder.title.setTextColor(Color.parseColor("#818181"));
                    viewHolder.info.setTextColor(Color.parseColor("#818181"));
                }
                viewHolder.line.setVisibility(View.VISIBLE);
                viewHolder.title.setText("您的申请已提交");
                viewHolder.info.setText("");
            }
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class RefundProgressViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView title, info;
        View line;

        public RefundProgressViewHolder(View itemView) {
            super(itemView);
            line = (View) itemView.findViewById(R.id.free_design_timeline_line);
            iv = (ImageView) itemView.findViewById(R.id.free_design_timeline_iv);
            title = (TextView) itemView.findViewById(R.id.free_design_timeline_title);
            info = (TextView) itemView.findViewById(R.id.free_design_timeline_info);
        }
    }
}
