package com.lishi.baijiaxing.customize.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.customize.model.MagazineBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */

public class MagazineAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private Context mContext;
    private List<MagazineBean> mMagazineBeen;
    private LayoutInflater mLayoutInflater;
    private OnMagazineItemClickListener mOnMagazineItemClickListener;

    private final static int TYPE_ITEM1 = 0x001;
    private final static int TYPE_ITEM2 = 0x002;
    private final static int TYPE_ITEM3 = 0x003;
    private final static int TYPE_ITEM4 = 0x004;
    private final static int TYPE_ITEM5 = 0x005;
    private final static int TYPE_ITEM6 = 0x006;
    private final static int TYPE_ITEM_GRID = 0x007;
    private final static int TYPE_ITEM_BOTTOM = 0x008;

    public MagazineAdapter(Context context, List<MagazineBean> magazineBeen) {
        this.mContext = context;
        this.mMagazineBeen = magazineBeen;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setOnMagazineItemClickListener(OnMagazineItemClickListener onMagazineItemClickListener) {
        mOnMagazineItemClickListener = onMagazineItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ITEM1:
                return new MagazineItem1ViewHolder(mLayoutInflater.inflate(R.layout.magazine_item1, parent, false));
            case TYPE_ITEM2:
                return new MagazineItem2ViewHolder(mLayoutInflater.inflate(R.layout.magazine_item2, parent, false));
            case TYPE_ITEM3:
                return new MagazineItem3ViewHolder(mLayoutInflater.inflate(R.layout.magazine_item3, parent, false));
            case TYPE_ITEM4:
                return new MagazineItem4ViewHolder(mLayoutInflater.inflate(R.layout.magazine_item4, parent, false));
            case TYPE_ITEM5:
                return new MagazineItem5ViewHolder(mLayoutInflater.inflate(R.layout.magazine_item5, parent, false));
            case TYPE_ITEM6:
                return new MagazineItem6ViewHolder(mLayoutInflater.inflate(R.layout.magazine_item6, parent, false));
            case TYPE_ITEM_GRID:
                return new MagazineItemGridViewHolder(mLayoutInflater.inflate(R.layout.magazine_item_grid, parent, false));
            case TYPE_ITEM_BOTTOM:
                return new MagazineItemBottomViewHolder(mLayoutInflater.inflate(R.layout.magazine_item_bottom, parent, false));
        }
        return new MagazineItem1ViewHolder(mLayoutInflater.inflate(R.layout.magazine_item_bottom, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MagazineItem1ViewHolder) {
            MagazineItem1ViewHolder viewHolder = (MagazineItem1ViewHolder) holder;
        } else if (holder instanceof MagazineItem2ViewHolder) {
            MagazineItem2ViewHolder viewHolder = (MagazineItem2ViewHolder) holder;
            viewHolder.submit.setOnClickListener(this);
        } else if (holder instanceof MagazineItem3ViewHolder) {
            MagazineItem3ViewHolder viewHolder = (MagazineItem3ViewHolder) holder;
            viewHolder.more.setOnClickListener(this);
        } else if (holder instanceof MagazineItem4ViewHolder) {
            MagazineItem4ViewHolder viewHolder = (MagazineItem4ViewHolder) holder;
        } else if (holder instanceof MagazineItem5ViewHolder) {
            MagazineItem5ViewHolder viewHolder = (MagazineItem5ViewHolder) holder;
            viewHolder.startCustomize.setOnClickListener(this);
        } else if (holder instanceof MagazineItem6ViewHolder) {
            MagazineItem6ViewHolder viewHolder = (MagazineItem6ViewHolder) holder;
        } else if (holder instanceof MagazineItemGridViewHolder) {
            MagazineItemGridViewHolder viewHolder = (MagazineItemGridViewHolder) holder;
        } else if (holder instanceof MagazineItemBottomViewHolder) {
            MagazineItemBottomViewHolder viewHolder = (MagazineItemBottomViewHolder) holder;
            viewHolder.startCustomize.setOnClickListener(this);
            viewHolder.service.setOnClickListener(this);
        }
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        case TYPE_ITEM1:
                        case TYPE_ITEM2:
                        case TYPE_ITEM3:
                        case TYPE_ITEM4:
                        case TYPE_ITEM5:
                        case TYPE_ITEM_BOTTOM:
                            return 2;
                        case TYPE_ITEM_GRID:
                            return 1;
                        case TYPE_ITEM6:
                            return 2;
                    }
                    return 1;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mMagazineBeen.size() + 7;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ITEM1;
        } else if (position == 1) {
            return TYPE_ITEM2;
        } else if (position == 2) {
            return TYPE_ITEM3;
        } else if (position == 3) {
            return TYPE_ITEM4;
        } else if (position == 4) {
            return TYPE_ITEM5;
        } else if (position == 5) {
            return TYPE_ITEM6;
        } else if (position == getItemCount() - 1) {
            return TYPE_ITEM_BOTTOM;
        }
        return TYPE_ITEM_GRID;
    }

    @Override
    public void onClick(View v) {
        if (mOnMagazineItemClickListener != null) {
            switch (v.getId()) {
                case R.id.magazine_item5_startCustomize:
                case R.id.magazine_item_bottom_startCustomize:
                    mOnMagazineItemClickListener.onStartCustomize();
                    break;
                case R.id.magazine_item3_more:
                    mOnMagazineItemClickListener.onMore();
                    break;
                case R.id.magazine_item2_submit:
                    mOnMagazineItemClickListener.onSubmitInfo();
                    break;
                case R.id.magazine_item_bottom_service:
                    mOnMagazineItemClickListener.onService();
                    break;

            }
        }
    }

    class MagazineItem1ViewHolder extends RecyclerView.ViewHolder {

        public MagazineItem1ViewHolder(View itemView) {
            super(itemView);
        }
    }

    class MagazineItem2ViewHolder extends RecyclerView.ViewHolder {
        TextView submit;

        public MagazineItem2ViewHolder(View itemView) {
            super(itemView);
            submit = (TextView) itemView.findViewById(R.id.magazine_item2_submit);
        }
    }

    class MagazineItem3ViewHolder extends RecyclerView.ViewHolder {
        TextView more;

        public MagazineItem3ViewHolder(View itemView) {
            super(itemView);
            more = (TextView) itemView.findViewById(R.id.magazine_item3_more);
        }
    }

    class MagazineItem4ViewHolder extends RecyclerView.ViewHolder {

        public MagazineItem4ViewHolder(View itemView) {
            super(itemView);
        }
    }

    class MagazineItem5ViewHolder extends RecyclerView.ViewHolder {
        ImageView startCustomize;

        public MagazineItem5ViewHolder(View itemView) {
            super(itemView);
            startCustomize = (ImageView) itemView.findViewById(R.id.magazine_item5_startCustomize);
        }
    }

    class MagazineItem6ViewHolder extends RecyclerView.ViewHolder {

        public MagazineItem6ViewHolder(View itemView) {
            super(itemView);
        }
    }

    class MagazineItemGridViewHolder extends RecyclerView.ViewHolder {

        public MagazineItemGridViewHolder(View itemView) {
            super(itemView);
        }
    }

    class MagazineItemBottomViewHolder extends RecyclerView.ViewHolder {
        TextView startCustomize, service;

        public MagazineItemBottomViewHolder(View itemView) {
            super(itemView);
            startCustomize = (TextView) itemView.findViewById(R.id.magazine_item_bottom_startCustomize);
            service = (TextView) itemView.findViewById(R.id.magazine_item_bottom_service);
        }
    }

    public interface OnMagazineItemClickListener {
        /**
         * 点击开始定制
         */
        void onStartCustomize();

        /**
         * 点击提交资料
         */
        void onSubmitInfo();

        /**
         * 了解更多
         */
        void onMore();

        /**
         * 资讯客服
         */
        void onService();
    }
}
