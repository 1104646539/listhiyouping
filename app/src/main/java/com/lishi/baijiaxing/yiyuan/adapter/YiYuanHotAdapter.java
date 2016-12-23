package com.lishi.baijiaxing.yiyuan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.yiyuan.model.HotList;
import com.lishi.baijiaxing.yiyuan.model.YiYuanHotBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/21.
 */
public class YiYuanHotAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<HotList.DataBean> mYiYuanHotBeens;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;

    public YiYuanHotAdapter(Context context, List<HotList.DataBean> yiYuanHotBeen) {
        this.mContext = context;
        this.mYiYuanHotBeens = yiYuanHotBeen;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new YiYuanHotViewHolder(mLayoutInflater.inflate(R.layout.item_yiyuan_hot, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof YiYuanHotViewHolder) {
            YiYuanHotViewHolder viewHolder = (YiYuanHotViewHolder) holder;
            HotList.DataBean yiYuanHotBean = mYiYuanHotBeens.get(position);

            viewHolder.iv_photo.setImageResource(R.drawable.yiyuan_hot_photo);
            viewHolder.tv_name.setText(yiYuanHotBean.getName());
            int progress = countProgress(yiYuanHotBean);
            viewHolder.tv_progress.setText(progress + "%");
            viewHolder.mProgressBar.setMax(100);
            viewHolder.mProgressBar.setProgress(progress);
            Glide.with(mContext).load(yiYuanHotBean.getPhotoUrl()).into(viewHolder.iv_photo);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onClickListener(v, position);
                    }
                }
            });

        }
    }

    private int countProgress(HotList.DataBean yiYuanHotBean) {
        int max = Integer.valueOf(yiYuanHotBean.getMaxNum());
        int now = Integer.valueOf(yiYuanHotBean.getNowNum());
        float progress = (now * 100 / max);
        return (int) progress;
    }

    @Override
    public int getItemCount() {
        return mYiYuanHotBeens.size();
    }

    class YiYuanHotViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private TextView tv_progress;
        private ProgressBar mProgressBar;
        private ImageView iv_photo;

        public YiYuanHotViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.yiYuan_hot_name);
            tv_progress = (TextView) itemView.findViewById(R.id.yiYuan_hot_progress);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.yiYuan_hot_progressBar);
            iv_photo = (ImageView) itemView.findViewById(R.id.yiYuan_hot_photo);

        }
    }

    public interface OnItemClickListener {
        void onClickListener(View view, int position);
    }
}
