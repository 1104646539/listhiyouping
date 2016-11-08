package com.lishi.baijiaxing.details.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.details.model.CommodityDetailsBean;
import com.lishi.baijiaxing.shoppingCart.adapter.ShoppingCartAdapter;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 */
public class FragmentDetailsAdapter extends RecyclerView.Adapter {
    private CommodityDetailsBean mCommodityDetailsBeen;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private YiYuanHotAdapter.OnItemClickListener mOnItemClick;

    private final static int TYPE_ITEM1 = 0X001;
    private final static int TYPE_ITEM2 = 0X002;
    private final static int TYPE_ITEM3 = 0X003;
    private final static int TYPE_ITEM4 = 0X004;

    public FragmentDetailsAdapter(Context context, CommodityDetailsBean commodityDetailsBeen) {
        this.mCommodityDetailsBeen = commodityDetailsBeen;
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setOnItemClick(YiYuanHotAdapter.OnItemClickListener onItemClick) {
        mOnItemClick = onItemClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ITEM1:
                return new FragmentDetails1ViewHolder(mLayoutInflater.inflate(R.layout.commodity_details1, parent, false));
            case TYPE_ITEM2:
                return new FragmentDetails2ViewHolder(mLayoutInflater.inflate(R.layout.commodity_details2, parent, false));
            case TYPE_ITEM3:
                return new FragmentDetails3ViewHolder(mLayoutInflater.inflate(R.layout.commodity_details3, parent, false));
            case TYPE_ITEM4:
                return new FragmentDetails4ViewHolder(mLayoutInflater.inflate(R.layout.commodity_details4, parent, false));
        }
        return new FragmentDetails4ViewHolder(mLayoutInflater.inflate(R.layout.commodity_details4, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FragmentDetails1ViewHolder) {
            FragmentDetails1ViewHolder viewHolder = (FragmentDetails1ViewHolder) holder;
            viewHolder.photo.setImageResource(R.drawable.commodity_details_photo);
        } else if (holder instanceof FragmentDetails2ViewHolder) {
            FragmentDetails2ViewHolder viewHolder = (FragmentDetails2ViewHolder) holder;
            viewHolder.name.setText(mCommodityDetailsBeen.getName());
            viewHolder.price.setText("￥" + mCommodityDetailsBeen.getPrice());
        } else if (holder instanceof FragmentDetails3ViewHolder) {
            FragmentDetails3ViewHolder viewHolder = (FragmentDetails3ViewHolder) holder;
            if (mCommodityDetailsBeen.getNorms().equals("")) {
                viewHolder.norms.setText("未选");
            } else {
                viewHolder.norms.setText(mCommodityDetailsBeen.getNorms());
            }
        } else if (holder instanceof FragmentDetails4ViewHolder) {
            FragmentDetails4ViewHolder viewHolder = (FragmentDetails4ViewHolder) holder;
            viewHolder.brief.setImageResource(mCommodityDetailsBeen.getBrief().get(position - 3));
        }
    }

    @Override
    public int getItemCount() {
        return mCommodityDetailsBeen.getBrief().size() + 3;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ITEM1;
        } else if (position == 1) {
            return TYPE_ITEM2;
        } else if (position == 2) {
            return TYPE_ITEM3;
        } else if (position >= 3) {
            return TYPE_ITEM4;
        }
        return TYPE_ITEM4;
    }

    class FragmentDetails1ViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;

        public FragmentDetails1ViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.commodity_details_photo);
        }
    }

    class FragmentDetails2ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;

        public FragmentDetails2ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.commodity_details_name);
            price = (TextView) itemView.findViewById(R.id.commodity_details_price);
        }
    }

    class FragmentDetails3ViewHolder extends RecyclerView.ViewHolder {
        TextView norms;

        public FragmentDetails3ViewHolder(View itemView) {
            super(itemView);
            norms = (TextView) itemView.findViewById(R.id.commodity_details_norms);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClick != null) {
                        mOnItemClick.onClickListener(v, getPosition());
                    }
                }
            });
        }
    }

    class FragmentDetails4ViewHolder extends RecyclerView.ViewHolder {
        ImageView brief;

        public FragmentDetails4ViewHolder(View itemView) {
            super(itemView);
            brief = (ImageView) itemView.findViewById(R.id.commodity_details_brief);
        }
    }

}
