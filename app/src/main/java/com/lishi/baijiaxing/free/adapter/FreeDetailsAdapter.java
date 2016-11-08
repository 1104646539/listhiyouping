package com.lishi.baijiaxing.free.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.free.model.FreeCommodityBean;
import com.lishi.baijiaxing.free.model.FreeDetailsBean;
import com.lishi.baijiaxing.home.widget.Advertisements;

/**
 * Created by Administrator on 2016/10/19.
 */
public class FreeDetailsAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private FreeDetailsBean mFreeDetailsBean;
    private final int MAX_HEAD = 5;

    private static final int TYPE_PHOTO = 0X001;
    private static final int TYPE_NAME = 0X002;
    private static final int TYPE_TIME = 0X003;
    private static final int TYPE_FREE = 0X004;
    private static final int TYPE_INFOTITLE = 0X005;
    private static final int TYPE_INFO = 0X006;

    public FreeDetailsAdapter(Context context, FreeDetailsBean detailsBean) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mFreeDetailsBean = detailsBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_PHOTO:
                return new FreeDetailsPhotoViewHolder(mLayoutInflater.inflate(R.layout.free_details1, parent, false));
            case TYPE_NAME:
                return new FreeDetailsNameViewHolder(mLayoutInflater.inflate(R.layout.free_details2, parent, false));
            case TYPE_TIME:
                return new FreeDetailsTime1ViewHolder(mLayoutInflater.inflate(R.layout.free_details3, parent, false));
            case TYPE_FREE:
                return new FreeDetailsFreeViewHolder(mLayoutInflater.inflate(R.layout.free_details4, parent, false));
            case TYPE_INFOTITLE:
                return new FreeDetailsInfoTitleViewHolder(mLayoutInflater.inflate(R.layout.free_details5, parent, false));
            case TYPE_INFO:
                return new FreeDetailsInfoViewHolder(mLayoutInflater.inflate(R.layout.free_details6, parent, false));
        }
        return new FreeDetailsInfoViewHolder(mLayoutInflater.inflate(R.layout.free_details6, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FreeDetailsPhotoViewHolder) {
            FreeDetailsPhotoViewHolder viewHolder = (FreeDetailsPhotoViewHolder) holder;
            viewHolder.mLinearLayout.setEnabled(false);
            viewHolder.mLinearLayout.addView(new Advertisements(mContext, true, LayoutInflater.from(mContext), 20000).initView(mFreeDetailsBean.getJSONArray()));
        } else if (holder instanceof FreeDetailsNameViewHolder) {
            FreeDetailsNameViewHolder viewHolder = (FreeDetailsNameViewHolder) holder;
            viewHolder.mTextView.setText(mFreeDetailsBean.getName());
        } else if (holder instanceof FreeDetailsTime1ViewHolder) {
            FreeDetailsTime1ViewHolder viewHolder = (FreeDetailsTime1ViewHolder) holder;
            if (mFreeDetailsBean.getType() == FreeCommodityBean.TYPE_BE_BEING_APPLY_NOT ||
                    mFreeDetailsBean.getType() == FreeCommodityBean.TYPE_BE_BEING_APPLY_OK) {
                viewHolder.ll2.setVisibility(View.VISIBLE);
                viewHolder.ll1.setVisibility(View.GONE);
                viewHolder.ll3.setVisibility(View.GONE);
            } else if (mFreeDetailsBean.getType() == FreeCommodityBean.TYPE_START_BEFORE) {
                viewHolder.ll2.setVisibility(View.GONE);
                viewHolder.ll1.setVisibility(View.VISIBLE);
                viewHolder.ll3.setVisibility(View.GONE);
            } else if (mFreeDetailsBean.getType() == FreeCommodityBean.TYPE_FINISH) {
                viewHolder.ll2.setVisibility(View.GONE);
                viewHolder.ll1.setVisibility(View.GONE);
                viewHolder.ll3.setVisibility(View.VISIBLE);
            }

            viewHolder.tv_limitNum.setText(mFreeDetailsBean.getLimitNum() + "");
            viewHolder.tv_peopleNum.setText(mFreeDetailsBean.getPeopleNum() + "");
            viewHolder.tv_price.setText(mFreeDetailsBean.getPrice() + "");
            viewHolder.tv_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        } else if (holder instanceof FreeDetailsFreeViewHolder) {
            FreeDetailsFreeViewHolder viewHolder = (FreeDetailsFreeViewHolder) holder;
        } else if (holder instanceof FreeDetailsInfoTitleViewHolder) {
            FreeDetailsInfoTitleViewHolder viewHolder = (FreeDetailsInfoTitleViewHolder) holder;
        } else if (holder instanceof FreeDetailsInfoViewHolder) {
            FreeDetailsInfoViewHolder viewHolder = (FreeDetailsInfoViewHolder) holder;
            viewHolder.info.setImageResource(mFreeDetailsBean.getSrcs().get(position - MAX_HEAD));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_PHOTO;
        } else if (position == 1) {
            return TYPE_NAME;
        } else if (position == 2) {
            return TYPE_TIME;
        } else if (position == 3) {
            return TYPE_FREE;
        } else if (position == 4) {
            return TYPE_INFOTITLE;
        } else {
            return TYPE_INFO;
        }
    }

    @Override
    public int getItemCount() {
        return mFreeDetailsBean.getSrcs().size() + MAX_HEAD;
    }

    class FreeDetailsPhotoViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mLinearLayout;

        public FreeDetailsPhotoViewHolder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.free_details_linear);
        }
    }

    class FreeDetailsNameViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public FreeDetailsNameViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.free_details_name);
        }
    }

    class FreeDetailsTime1ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_limitNum;
        TextView tv_peopleNum;
        TextView tv_price;

        TextView mTextView1;
        TextView mTextView2;
        TextView mTextView3;
        TextView mTextView4;
        TextView mTextView5;

        LinearLayout ll1;
        LinearLayout ll2;
        LinearLayout ll3;

        public FreeDetailsTime1ViewHolder(View itemView) {
            super(itemView);

            tv_limitNum = (TextView) itemView.findViewById(R.id.free_details_limitNum);
            tv_peopleNum = (TextView) itemView.findViewById(R.id.free_details_peopleNum);
            tv_price = (TextView) itemView.findViewById(R.id.free_details_price);

            mTextView1 = (TextView) itemView.findViewById(R.id.free_details_time1);
            mTextView2 = (TextView) itemView.findViewById(R.id.free_details_time2);
            mTextView3 = (TextView) itemView.findViewById(R.id.free_details_time3);
            mTextView4 = (TextView) itemView.findViewById(R.id.free_details_time4);
            mTextView5 = (TextView) itemView.findViewById(R.id.free_details_time5);
            ll1 = (LinearLayout) itemView.findViewById(R.id.free_details_ll1);
            ll2 = (LinearLayout) itemView.findViewById(R.id.free_details_ll2);
            ll3 = (LinearLayout) itemView.findViewById(R.id.free_details_ll3);
        }
    }

    class FreeDetailsInfoTitleViewHolder extends RecyclerView.ViewHolder {

        public FreeDetailsInfoTitleViewHolder(View itemView) {
            super(itemView);
        }
    }

    class FreeDetailsFreeViewHolder extends RecyclerView.ViewHolder {
        public FreeDetailsFreeViewHolder(View itemView) {
            super(itemView);
        }
    }

    class FreeDetailsInfoViewHolder extends RecyclerView.ViewHolder {
        ImageView info;

        public FreeDetailsInfoViewHolder(View itemView) {
            super(itemView);
            info = (ImageView) itemView.findViewById(R.id.free_details_info);
        }
    }

}
