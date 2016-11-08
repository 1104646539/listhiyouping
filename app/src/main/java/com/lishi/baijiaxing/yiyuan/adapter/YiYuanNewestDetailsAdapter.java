package com.lishi.baijiaxing.yiyuan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.home.widget.Advertisements;
import com.lishi.baijiaxing.yiyuan.model.YiYuanHotDetailsBean;
import com.lishi.baijiaxing.yiyuan.model.YiYuanNewestDetailsBean;
import com.lishi.baijiaxing.yiyuan.view.YiYuanActivity;

/**
 * Created by Administrator on 2016/10/19.
 */
public class YiYuanNewestDetailsAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private YiYuanNewestDetailsBean mYiYuanNewestDetailsBean;
    private final int MAX_HEAD = 5;

    private static final int TYPE_PHOTO = 0X001;
    private static final int TYPE_NAME = 0X002;
    private static final int TYPE_NUM_NEWEST = 0X003;
    private static final int TYPE_ALLRECORD = 0X004;//所有参与记录
    private static final int TYPE_INFOTITLE = 0X005;
    private static final int TYPE_INFO = 0X006;

    public YiYuanNewestDetailsAdapter(Context context, YiYuanNewestDetailsBean detailsBean) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mYiYuanNewestDetailsBean = detailsBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_PHOTO:
                return new YiYuanDetailsPhotoViewHolder(mLayoutInflater.inflate(R.layout.free_details1, parent, false));
            case TYPE_NAME:
                return new YiYuanDetailsNameViewHolder(mLayoutInflater.inflate(R.layout.free_details2, parent, false));
            case TYPE_NUM_NEWEST:
                return new YiYuanNewestWinningViewHolder(mLayoutInflater.inflate(R.layout.yiyuan_details3_newest, parent, false));
            case TYPE_ALLRECORD:
                return new YiYuanDetailsRecordViewHolder(mLayoutInflater.inflate(R.layout.yiyuan_details4, parent, false));
            case TYPE_INFOTITLE:
                return new YiYuanDetailsInfoTitleViewHolder(mLayoutInflater.inflate(R.layout.yiyuan_details5, parent, false));
            case TYPE_INFO:
                return new FreeDetailsInfoViewHolder(mLayoutInflater.inflate(R.layout.free_details6, parent, false));
        }
        return new FreeDetailsInfoViewHolder(mLayoutInflater.inflate(R.layout.free_details6, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof YiYuanDetailsPhotoViewHolder) {
            YiYuanDetailsPhotoViewHolder viewHolder = (YiYuanDetailsPhotoViewHolder) holder;
            viewHolder.mLinearLayout.setEnabled(false);
            viewHolder.mLinearLayout.addView(new Advertisements(mContext, true, LayoutInflater.from(mContext), 20000).initView(mYiYuanNewestDetailsBean.getJSONArray()));
        } else if (holder instanceof YiYuanDetailsNameViewHolder) {
            YiYuanDetailsNameViewHolder viewHolder = (YiYuanDetailsNameViewHolder) holder;
            viewHolder.mTextView.setText(mYiYuanNewestDetailsBean.getName());
        } else if (holder instanceof YiYuanNewestWinningViewHolder) {
            YiYuanNewestWinningViewHolder viewHolder = (YiYuanNewestWinningViewHolder) holder;

            viewHolder.head_portrait.setImageResource(R.drawable.commend_head);
            viewHolder.winningName.setText(mYiYuanNewestDetailsBean.getWinningName());
            viewHolder.num.setText(mYiYuanNewestDetailsBean.getNum()+"期");
            viewHolder.participationNum.setText(mYiYuanNewestDetailsBean.getParticipationNum()+"");
            viewHolder.winningNum.setText(mYiYuanNewestDetailsBean.getWinningNum());
            viewHolder.time.setText(mYiYuanNewestDetailsBean.getTime());
            if (mYiYuanNewestDetailsBean.isParticipation()) {
                viewHolder.mLinearLayout.setVisibility(View.INVISIBLE);
            } else {
                viewHolder.mLinearLayout.setVisibility(View.VISIBLE);
            }

        } else if (holder instanceof YiYuanDetailsRecordViewHolder) {
            YiYuanDetailsRecordViewHolder viewHolder = (YiYuanDetailsRecordViewHolder) holder;

            viewHolder.recordStartTime.setText("" + mYiYuanNewestDetailsBean.getRecordTime());
        } else if (holder instanceof YiYuanDetailsInfoTitleViewHolder) {
            YiYuanDetailsInfoTitleViewHolder viewHolder = (YiYuanDetailsInfoTitleViewHolder) holder;
        } else if (holder instanceof FreeDetailsInfoViewHolder) {
            FreeDetailsInfoViewHolder viewHolder = (FreeDetailsInfoViewHolder) holder;
            viewHolder.info.setImageResource(mYiYuanNewestDetailsBean.getSrcs().get(position - MAX_HEAD));
        } else if (holder instanceof YiYuanNewestWinningViewHolder) {

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_PHOTO;
        } else if (position == 1) {
            return TYPE_NAME;
        } else if (position == 2) {
            return TYPE_NUM_NEWEST;
        } else if (position == 3) {
            return TYPE_ALLRECORD;
        } else if (position == 4) {
            return TYPE_INFOTITLE;
        } else {
            return TYPE_INFO;
        }
    }

    @Override
    public int getItemCount() {
        return mYiYuanNewestDetailsBean.getSrcs().size() + MAX_HEAD;
    }

    class YiYuanDetailsPhotoViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mLinearLayout;

        public YiYuanDetailsPhotoViewHolder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.free_details_linear);
        }
    }

    class YiYuanDetailsNameViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public YiYuanDetailsNameViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.free_details_name);
        }
    }


    class YiYuanDetailsInfoTitleViewHolder extends RecyclerView.ViewHolder {

        public YiYuanDetailsInfoTitleViewHolder(View itemView) {
            super(itemView);
        }
    }


    class YiYuanNewestWinningViewHolder extends RecyclerView.ViewHolder {
        ImageView head_portrait;
        TextView winningName, time, winningNum, num, participationNum;
        LinearLayout mLinearLayout;
        TextView isParticipation;

        public YiYuanNewestWinningViewHolder(View itemView) {
            super(itemView);
            head_portrait = (ImageView) itemView.findViewById(R.id.yiyuan_details3_newest_headPortrait);
            winningName = (TextView) itemView.findViewById(R.id.yiyuan_details3_newest_winningName);
            winningNum = (TextView) itemView.findViewById(R.id.yiyuan_details3_newest_winningNum);
            time = (TextView) itemView.findViewById(R.id.yiyuan_details3_newest_time);
            num = (TextView) itemView.findViewById(R.id.yiyuan_details3_newest_num);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.yiyuan_details3_newest_isParticipation_ll);
            isParticipation = (TextView) itemView.findViewById(R.id.yiyuan_details3_newest_isParticipation_tv);
            participationNum = (TextView) itemView.findViewById(R.id.yiyuan_details3_newest_participationNum);
        }
    }

    class FreeDetailsInfoViewHolder extends RecyclerView.ViewHolder {
        ImageView info;

        public FreeDetailsInfoViewHolder(View itemView) {
            super(itemView);
            info = (ImageView) itemView.findViewById(R.id.free_details_info);
        }
    }

    private class YiYuanDetailsRecordViewHolder extends RecyclerView.ViewHolder {
        private TextView recordStartTime;

        public YiYuanDetailsRecordViewHolder(View itemView) {
            super(itemView);
            recordStartTime = (TextView) itemView.findViewById(R.id.yiyuan_details4_time);
        }
    }
}
