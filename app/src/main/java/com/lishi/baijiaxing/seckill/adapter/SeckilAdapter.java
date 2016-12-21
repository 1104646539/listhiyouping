package com.lishi.baijiaxing.seckill.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.seckill.model.SSeckilBean;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

import java.util.List;

/**
 * 利世秒杀
 * Created by Administrator on 2016/11/22.
 */

public class SeckilAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<SSeckilBean> mSeckilBeen;
    private LayoutInflater mLayoutInflater;
    private YiYuanHotAdapter.OnItemClickListener mOnItemClickListener;

    public SeckilAdapter(Context context, List<SSeckilBean> seckilBeen) {
        this.mContext = context;
        this.mSeckilBeen = seckilBeen;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setOnItemClickListener(YiYuanHotAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SeckilViewHolder(mLayoutInflater.inflate(R.layout.item_seckil, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SeckilViewHolder viewHolder = (SeckilViewHolder) holder;
        SSeckilBean seckilBean = mSeckilBeen.get(position);
        viewHolder.name.setText(seckilBean.getName());
        viewHolder.nowPrice.setText("￥" + seckilBean.getNowPrice());
        viewHolder.oldPrice.setText("￥" + seckilBean.getOldPrice());
        viewHolder.oldPrice.getPaint().setFlags(TextPaint.STRIKE_THRU_TEXT_FLAG);
        int progress = getProgress(seckilBean);
        viewHolder.soldProgress.setText("已售" + progress + "%");
        viewHolder.photo.setImageResource(R.drawable.seckil_item_photo);
        viewHolder.progress.setProgress(progress);
    }

    /**
     * 计算进度
     *
     * @param seckilBean
     * @return
     */
    private int getProgress(SSeckilBean seckilBean) {
        int max = Integer.valueOf(seckilBean.getTotalNum());
        int now = Integer.valueOf(seckilBean.getSoldNum());

        int progress = (int) ((now * 1.0F) / (max * 1.0F) * 100);
        return progress;
    }

    @Override
    public int getItemCount() {
        return mSeckilBeen.size();
    }

    class SeckilViewHolder extends RecyclerView.ViewHolder {
        TextView name, nowPrice, oldPrice, goBuy, soldProgress;
        ImageView photo;
        ProgressBar progress;

        public SeckilViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.seckil_item_name);
            nowPrice = (TextView) itemView.findViewById(R.id.seckil_item_nowPrice);
            oldPrice = (TextView) itemView.findViewById(R.id.seckil_item_oldPrice);
            goBuy = (TextView) itemView.findViewById(R.id.seckil_item_goBuy);
            soldProgress = (TextView) itemView.findViewById(R.id.seckil_item_soldProgress);
            photo = (ImageView) itemView.findViewById(R.id.seckil_item_photo);
            progress = (ProgressBar) itemView.findViewById(R.id.seckil_item_progress);

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
