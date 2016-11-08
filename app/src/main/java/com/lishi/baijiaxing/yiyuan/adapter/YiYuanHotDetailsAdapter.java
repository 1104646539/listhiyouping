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
public class YiYuanHotDetailsAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private YiYuanHotDetailsBean mYiYuanDetailsBean;
    private final int MAX_HEAD = 5;

    private static final int TYPE_PHOTO = 0X001;
    private static final int TYPE_NAME = 0X002;
    private static final int TYPE_NUM_HOT = 0X003;
    private static final int TYPE_ALLRECORD = 0X004;//所有参与记录
    private static final int TYPE_INFOTITLE = 0X005;
    private static final int TYPE_INFO = 0X006;

    public YiYuanHotDetailsAdapter(Context context, YiYuanHotDetailsBean detailsBean) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mYiYuanDetailsBean = detailsBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_PHOTO:
                return new YiYuanDetailsPhotoViewHolder(mLayoutInflater.inflate(R.layout.free_details1, parent, false));
            case TYPE_NAME:
                return new YiYuanDetailsNameViewHolder(mLayoutInflater.inflate(R.layout.free_details2, parent, false));
            case TYPE_NUM_HOT:
                return new YiYuanHotWinningViewHolder(mLayoutInflater.inflate(R.layout.yiyuan_details3_hot, parent, false));
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
            viewHolder.mLinearLayout.addView(new Advertisements(mContext, true, LayoutInflater.from(mContext), 20000).initView(mYiYuanDetailsBean.getJSONArray()));
        } else if (holder instanceof YiYuanDetailsNameViewHolder) {
            YiYuanDetailsNameViewHolder viewHolder = (YiYuanDetailsNameViewHolder) holder;
            viewHolder.mTextView.setText(mYiYuanDetailsBean.getName());
        } else if (holder instanceof YiYuanHotWinningViewHolder) {
            if (mYiYuanDetailsBean.getType() == YiYuanActivity.TYPE_HOT) {
                YiYuanHotWinningViewHolder viewHolder = (YiYuanHotWinningViewHolder) holder;

                viewHolder.now_num.setText("" + mYiYuanDetailsBean.getNum());
                viewHolder.needNum.setText("" + mYiYuanDetailsBean.getNeedNum());
                viewHolder.surplusNum.setText("" + mYiYuanDetailsBean.getSurplusNum());
                viewHolder.MyParticipationNum.setText("" + mYiYuanDetailsBean.getParticipationNum());
                viewHolder.winningNum.setText("" + mYiYuanDetailsBean.getWinningNum());
                viewHolder.mProgressBar.setProgress(countProgress(mYiYuanDetailsBean));
            }
        } else if (holder instanceof YiYuanDetailsRecordViewHolder) {
            YiYuanDetailsRecordViewHolder viewHolder = (YiYuanDetailsRecordViewHolder) holder;

            viewHolder.recordStartTime.setText("" + mYiYuanDetailsBean.getRecordTime());
        } else if (holder instanceof YiYuanDetailsInfoTitleViewHolder) {
            YiYuanDetailsInfoTitleViewHolder viewHolder = (YiYuanDetailsInfoTitleViewHolder) holder;
        } else if (holder instanceof FreeDetailsInfoViewHolder) {
            FreeDetailsInfoViewHolder viewHolder = (FreeDetailsInfoViewHolder) holder;
            viewHolder.info.setImageResource(mYiYuanDetailsBean.getSrcs().get(position - MAX_HEAD));
        }
    }

    private int countProgress(YiYuanHotDetailsBean yiYuanDetailsBean) {
        int needNum = yiYuanDetailsBean.getNeedNum();
        int surplusNum = yiYuanDetailsBean.getSurplusNum();
        int progress = (needNum - surplusNum) * 100 / needNum;
        return progress;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_PHOTO;
        } else if (position == 1) {
            return TYPE_NAME;
        } else if (position == 2) {
            return TYPE_NUM_HOT;
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
        return mYiYuanDetailsBean.getSrcs().size() + MAX_HEAD;
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

    class YiYuanHotWinningViewHolder extends RecyclerView.ViewHolder {
        /**
         * 当前期号         now_num
         * 需要份数         needNum
         * 剩余份数         surplusNum
         * 我参与的份数     MyParticipationNum
         * 夺宝号码         winningNum
         * 计算详情         countDetails
         */
        TextView now_num, MyParticipationNum, needNum, surplusNum, winningNum, countDetails;
        ProgressBar mProgressBar;

        public YiYuanHotWinningViewHolder(View itemView) {
            super(itemView);
            now_num = (TextView) itemView.findViewById(R.id.yiyuan_details3_num);
            MyParticipationNum = (TextView) itemView.findViewById(R.id.yiyuan_details3_participationNum);
            needNum = (TextView) itemView.findViewById(R.id.yiyuan_details3_needNum);
            surplusNum = (TextView) itemView.findViewById(R.id.yiyuan_details3_surplusNum);
            winningNum = (TextView) itemView.findViewById(R.id.yiyuan_details3_winningNum);
            countDetails = (TextView) itemView.findViewById(R.id.yiyuan_details3_countDetails);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.yiyuan_details3_progressBar);
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
