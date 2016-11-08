package com.lishi.baijiaxing.freeDesign.adapter;

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
import com.lishi.baijiaxing.freeDesign.model.ProgressQueryBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */
public class ProgressQueryAdapter extends RecyclerView.Adapter {
    private ProgressQueryBean mProgressQueryBean;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ProgressQueryAdapter(Context context, ProgressQueryBean queryBean) {
        this.mContext = context;
        this.mProgressQueryBean = queryBean;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProgressQueryViewHolder(mLayoutInflater.inflate(R.layout.free_design_timeline, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ProgressQueryViewHolder) {
            ProgressQueryViewHolder viewHolder = (ProgressQueryViewHolder) holder;
            if (position == 0) {
                if (mProgressQueryBean.getType() == ProgressQueryBean.TYPE_SUBSCRIBESU_SUCCESS) {
                    viewHolder.iv.setImageResource(R.drawable.free_design_timeline_true);
                    viewHolder.title.setTextColor(Color.parseColor("#e10012"));
                    viewHolder.info.setTextColor(Color.parseColor("#e10012"));
                } else {
                    viewHolder.iv.setImageResource(R.drawable.free_design_timeline_false);
                    viewHolder.title.setTextColor(Color.parseColor("#818181"));
                    viewHolder.info.setTextColor(Color.parseColor("#818181"));
                }
                viewHolder.line.setVisibility(View.VISIBLE);
                viewHolder.title.setText("预约成功");
                viewHolder.info.setText("利世预约成功利世预约成功利世预约成功利世预约成功利世预约成功利世预约成功利世预约成功利世预约成功利世预约成功");

                RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) viewHolder.itemView.getLayoutParams();
                lp.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, mContext.getResources().getDisplayMetrics());
                LinearLayout.LayoutParams vlp = (LinearLayout.LayoutParams) viewHolder.iv.getLayoutParams();
                vlp.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, mContext.getResources().getDisplayMetrics());
            } else if (position == 1) {
                if (mProgressQueryBean.getType() == ProgressQueryBean.TYPE_DESIGN_ING) {
                    viewHolder.iv.setImageResource(R.drawable.free_design_timeline_true);
                    viewHolder.title.setTextColor(Color.parseColor("#e10012"));
                    viewHolder.info.setTextColor(Color.parseColor("#e10012"));
                } else {
                    viewHolder.iv.setImageResource(R.drawable.free_design_timeline_false);
                    viewHolder.title.setTextColor(Color.parseColor("#818181"));
                    viewHolder.info.setTextColor(Color.parseColor("#818181"));
                }
                viewHolder.line.setVisibility(View.VISIBLE);
                viewHolder.title.setText("免费设计中");
                viewHolder.info.setText("利世免费设计中利世免费设计中利世免费设计中利世免费设计中利世免费设计中利世免费设计中利世免费设计中利世免费设计中");
            } else {
                if (mProgressQueryBean.getType() == ProgressQueryBean.TYPE_DESIGN_SUCCESS) {
                    viewHolder.iv.setImageResource(R.drawable.free_design_timeline_true);
                    viewHolder.title.setTextColor(Color.parseColor("#e10012"));
                    viewHolder.info.setTextColor(Color.parseColor("#e10012"));
                } else {
                    viewHolder.iv.setImageResource(R.drawable.free_design_timeline_false);
                    viewHolder.title.setTextColor(Color.parseColor("#818181"));
                    viewHolder.info.setTextColor(Color.parseColor("#818181"));
                }
                viewHolder.line.setVisibility(View.INVISIBLE);
                viewHolder.title.setText("设计完成");
                viewHolder.info.setText("利世设计完成利世设计完成利世设计完成利世设计完成利世设计完成利世设计完成利世设计完成利世设计完成利世设计完成利世设计完成");
            }
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class ProgressQueryViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView title, info;
        View line;

        public ProgressQueryViewHolder(View itemView) {
            super(itemView);
            line = (View) itemView.findViewById(R.id.free_design_timeline_line);
            iv = (ImageView) itemView.findViewById(R.id.free_design_timeline_iv);
            title = (TextView) itemView.findViewById(R.id.free_design_timeline_title);
            info = (TextView) itemView.findViewById(R.id.free_design_timeline_info);
        }
    }
}
