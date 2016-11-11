package com.lishi.baijiaxing.latest.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.latest.model.LatestBean;
import com.lishi.baijiaxing.latest.model.LatestCommodityBean;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/4.
 */
public class LatestAdapter extends RecyclerView.Adapter {
    private LatestBean mLatestBean;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private YiYuanHotAdapter.OnItemClickListener mOnItemClickListener;

    private final static int TYPE_TOP = 0X001;
    private final static int TYPE_BOTTOM = 0X002;
    private final static int TYPE_HEAD = 0X003;
    private final static int TYPE_ITEM1 = 0X004;
    private final static int TYPE_ITEM2 = 0X005;


    public LatestAdapter(Context context, LatestBean latestBean) {
        this.mContext = context;
        this.mLatestBean = latestBean;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setOnItemClickListener(YiYuanHotAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TOP:
            case TYPE_BOTTOM:
                return new LatestTopBottomViewHolder(mLayoutInflater.inflate(R.layout.latest_item_top, parent, false));
            case TYPE_ITEM1:
                return new LatestItem1ViewHolder(mLayoutInflater.inflate(R.layout.latest_item1_photo, parent, false));
            case TYPE_ITEM2:
                return new LatestItem2ViewHolder(mLayoutInflater.inflate(R.layout.latest_item2_photo, parent, false));
            case TYPE_HEAD:
                return new LatestHeadViewHolder(mLayoutInflater.inflate(R.layout.latest_item_head, parent, false));
        }
        return new LatestItem2ViewHolder(mLayoutInflater.inflate(R.layout.latest_item2_photo, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Log.e("onBindViewHolder", "onBindViewHolder: position=" + position);
        if (holder instanceof LatestTopBottomViewHolder) {
            LatestTopBottomViewHolder viewHolder = (LatestTopBottomViewHolder) holder;
            if (position == 0) {
                viewHolder.mImageView.setImageResource(R.drawable.latest_top);
            } else {
                viewHolder.mImageView.setImageResource(R.drawable.latest_bottom);
            }
        } else if (holder instanceof LatestHeadViewHolder) {
            LatestHeadViewHolder viewHolder = (LatestHeadViewHolder) holder;
            viewHolder.head.setText(mLatestBean.getClassifyName().get(countHeadPosition(position)));
        } else if (holder instanceof LatestItem1ViewHolder) {
            LatestItem1ViewHolder viewHolder = (LatestItem1ViewHolder) holder;
            viewHolder.photo.setImageResource(R.drawable.latest_item1_photo);
            viewHolder.photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onClickListener(v, position);
                    }
                }
            });
        } else {
            LatestItem2ViewHolder viewHolder = (LatestItem2ViewHolder) holder;
            List<LatestCommodityBean> lcsb = mLatestBean.getLatestCommodityBeen();
            viewHolder.name.setText(lcsb.get(countItem2Position(position)).getName());
            viewHolder.brief.setText(lcsb.get(countItem2Position(position)).getBrief());
            viewHolder.price.setText("￥"+lcsb.get(countItem2Position(position)).getPrice());
            viewHolder.photo.setImageResource(R.drawable.latest_item2_photo);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onClickListener(v, position);
                    }
                }
            });
            if (position % 2 == 0) {
                RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) viewHolder.itemView.getLayoutParams();
                lp.rightMargin = lp.leftMargin;
            }
        }
    }

    /**
     * 计算item2 Position
     *
     * @param position
     * @return
     */
    private int countItem2Position(int position) {
        if (position <= 8) {
            return position - 3;
        } else if (position <= 16) {
            return position - 5;
        } else if (position <= 24) {
            return position - 7;
        }
        return 0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            GridLayoutManager gridManager = (GridLayoutManager) manager;
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        case TYPE_TOP:
                        case TYPE_BOTTOM:
                        case TYPE_ITEM1:
                        case TYPE_HEAD:
                            return 2;
                        case TYPE_ITEM2:
                            return 1;
                    }
                    return 2;
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TOP;
        } else if (position == getItemCount() - 1) {
            return TYPE_BOTTOM;
        } else if ((position - 1) % 8 == 0) {
            return TYPE_HEAD;
        } else if ((position - 2) % 8 == 0) {
            return TYPE_ITEM1;
        } else {
            return TYPE_ITEM2;
        }
    }

    /**
     * 计算head position
     *
     * @param position
     * @return
     */
    private int countHeadPosition(int position) {
        return (position - 1) / 8;
    }

    @Override
    public int getItemCount() {
        return mLatestBean.getClassifyName().size()
                + mLatestBean.getLatestCommodityBeen().size() + 2;
    }

    class LatestTopBottomViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        public LatestTopBottomViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.latest_item_top_photo);
        }
    }

    class LatestHeadViewHolder extends RecyclerView.ViewHolder {
        TextView head;

        public LatestHeadViewHolder(View itemView) {
            super(itemView);
            head = (TextView) itemView.findViewById(R.id.latest_head);
        }
    }

    class LatestItem1ViewHolder extends RecyclerView.ViewHolder {
        private ImageView photo;

        public LatestItem1ViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.latest_item1_photo);
        }
    }

    class LatestItem2ViewHolder extends RecyclerView.ViewHolder {
        private ImageView photo;
        private TextView name, price, buy, brief;

        public LatestItem2ViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.latest_item1_photo);
            name = (TextView) itemView.findViewById(R.id.latest_item2_name);
            price = (TextView) itemView.findViewById(R.id.latest_item2_price);
            buy = (TextView) itemView.findViewById(R.id.latest_item2_buy);
            brief = (TextView) itemView.findViewById(R.id.latest_item2_brief);

        }
    }

}
