package com.lishi.baijiaxing.myyiyuan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.myyiyuan.model.MyYiYuanBean;
import com.lishi.baijiaxing.view.HintProgressBar;

import java.util.List;

/**
 * 我的一元拼
 * Created by Administrator on 2016/10/27.
 */
public class MyYiYuanAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<MyYiYuanBean> mMyYiYuanBean;
    private LayoutInflater mLayoutInflater;

    public MyYiYuanAdapter(Context context, List<MyYiYuanBean> yiYuanBeens) {
        this.mContext = context;
        this.mMyYiYuanBean = yiYuanBeens;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyYiYuanViewHolder(mLayoutInflater.inflate(R.layout.myyiyuan_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyYiYuanViewHolder) {
            MyYiYuanViewHolder viewHolder = (MyYiYuanViewHolder) holder;
            MyYiYuanBean myYiYuanBean = mMyYiYuanBean.get(position);
            viewHolder.iv_photo.setImageResource(R.drawable.yiyuan_hot_photo);

            viewHolder.tv_name.setText(myYiYuanBean.getName());
            viewHolder.num.setText(myYiYuanBean.getNum() + "");
            viewHolder.participationNum.setText(myYiYuanBean.getParticipationNum() + "");
            viewHolder.mHintProgressBar.setProgress(countProgress(myYiYuanBean));
            viewHolder.maxNum.setText(myYiYuanBean.getMax_num() + "份");
            viewHolder.nowNum.setText(myYiYuanBean.getMax_num() - myYiYuanBean.getNow_num() + "份");
        }
    }

    private int countProgress(MyYiYuanBean myYiYuanBean) {
        int max = myYiYuanBean.getMax_num();
        int now = myYiYuanBean.getNow_num();
        int progress = (int) ((now * 1.0F) / (max * 1.0F) * 100);
        Log.e("countProgress", "progress=progressprogressprogressprogressprogressprogressprogress" + progress);
        return progress;
    }

    @Override
    public int getItemCount() {
        return mMyYiYuanBean.size();
    }

    class MyYiYuanViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, num, participationNum, maxNum, nowNum, buy, check;
        ImageView iv_photo;
        HintProgressBar mHintProgressBar;

        public MyYiYuanViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.myyiYuan_name);
            num = (TextView) itemView.findViewById(R.id.myyiyuan_num);
            participationNum = (TextView) itemView.findViewById(R.id.myyiyuan_participationNum);
            maxNum = (TextView) itemView.findViewById(R.id.myyiyuan_maxNum);
            nowNum = (TextView) itemView.findViewById(R.id.myyiyuan_nowNum);
            buy = (TextView) itemView.findViewById(R.id.myyiyuan_buy);
            check = (TextView) itemView.findViewById(R.id.myyiyuan_check);
            iv_photo = (ImageView) itemView.findViewById(R.id.myyiYuan_photo);
            mHintProgressBar = (HintProgressBar) itemView.findViewById(R.id.myyiyuan_progressBar);
        }
    }
}
