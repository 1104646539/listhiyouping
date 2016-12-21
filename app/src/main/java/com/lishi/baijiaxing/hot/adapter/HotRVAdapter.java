package com.lishi.baijiaxing.hot.adapter;

import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.hot.model.HotCommodity;
import com.lishi.baijiaxing.hot.view.HotCommodityActivity;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

/**
 * Created by Administrator on 2016/12/8.
 */

public class HotRVAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private HotCommodity mHotCommodity;
    /**
     * 广告图类型
     */
    private static final int TYPE_AD = 0X1110;
    /**
     * 普通商品类型
     */
    private static final int TYPE_COMMODITY = 0X1112;

    private YiYuanHotAdapter.OnItemClickListener mOnItemClickListener;

    public HotRVAdapter(Context context, HotCommodity hotCommodity) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mHotCommodity = hotCommodity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_AD) {
            return new HotRVAdViewHolder(mLayoutInflater.inflate(R.layout.item_hot_commodity_ad, parent, false));
        } else {
            return new HotRVViewHolder(mLayoutInflater.inflate(R.layout.item_hot_commodity, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        if (holder instanceof HotRVViewHolder) {
            HotRVViewHolder viewHolder = (HotRVViewHolder) holder;
            HotCommodity.DataBean data = mHotCommodity.getData().get(position-1);
            viewHolder.tv_flag.setText("" + position);
            viewHolder.tv_name.setText(data.getName());
            viewHolder.tv_nowPrice.setText("￥" + data.getOldPrice());
            viewHolder.tv_oldPrice.setText("原价:" + data.getNowPrice());
            viewHolder.tv_num.setText("热销" + data.getSalesVolume() + "件");
            Glide.with(mContext).load(data.getPhotoUrl()).into(viewHolder.photo);

            viewHolder.tv_oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        } else if (holder instanceof HotRVAdViewHolder) {
            HotRVAdViewHolder viewHolder = (HotRVAdViewHolder) holder;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_AD;
        } else {
            return TYPE_COMMODITY;
        }
    }

    @Override
    public int getItemCount() {
        return mHotCommodity.getData().size() + 1;
    }

    class HotRVViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_num, tv_nowPrice, tv_oldPrice, tv_name, tv_buy, tv_flag;
        private ImageView photo;

        public HotRVViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.hot_commodity_name);
            tv_num = (TextView) itemView.findViewById(R.id.hot_commodity_number);
            tv_nowPrice = (TextView) itemView.findViewById(R.id.hot_commodity_nowPrice);
            tv_oldPrice = (TextView) itemView.findViewById(R.id.hot_commodity_oldPrice);
            tv_buy = (TextView) itemView.findViewById(R.id.hot_commodity_buy);
            tv_flag = (TextView) itemView.findViewById(R.id.hot_commodity_flag);
            photo = (ImageView) itemView.findViewById(R.id.hot_commodity_photo);

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

    class HotRVAdViewHolder extends RecyclerView.ViewHolder {
        private ImageView photo;

        public HotRVAdViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.hot_commodity_ad);
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

    public void setOnItemClickListener(YiYuanHotAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
