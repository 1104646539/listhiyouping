package com.lishi.baijiaxing.customize.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.customize.model.CustomizeCommodityBean;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

/**
 * Created by Administrator on 2016/10/26.
 */
public class CustomizeDetailsAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private CustomizeCommodityBean mCustomizeCommodityBean;
    private LayoutInflater mLayoutInflater;
    private YiYuanHotAdapter.OnItemClickListener mOnItemClickListener;

    private final static int TYPE_ITEM1 = 0X001;
    private final static int TYPE_ITEM2 = 0X002;
    private final static int TYPE_ITEM3 = 0X003;
    private final static int TYPE_ITEM4 = 0X004;
    private final static int TYPE_ITEM5 = 0X005;
    private final static int TYPE_ITEM6 = 0X006;


    public CustomizeDetailsAdapter(Context context, CustomizeCommodityBean customizeCommodityBean) {
        this.mContext = context;
        this.mCustomizeCommodityBean = customizeCommodityBean;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setOnItemClickListener(YiYuanHotAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ITEM1:
                return new CustomizeDetailsItem1ViewHolder(mLayoutInflater.inflate(R.layout.customize_details_item1, parent, false));
            case TYPE_ITEM2:
                return new CustomizeDetailsItem2ViewHolder(mLayoutInflater.inflate(R.layout.customize_details_item2, parent, false));
            case TYPE_ITEM3:
                return new CustomizeDetailsItem3ViewHolder(mLayoutInflater.inflate(R.layout.customize_details_item3, parent, false));
            case TYPE_ITEM4:
                return new CustomizeDetailsItem4ViewHolder(mLayoutInflater.inflate(R.layout.customize_details_item4, parent, false));
            case TYPE_ITEM5:
                return new CustomizeDetailsItem5ViewHolder(mLayoutInflater.inflate(R.layout.customize_details_item5, parent, false));
            case TYPE_ITEM6:
                return new CustomizeDetailsItem6ViewHolder(mLayoutInflater.inflate(R.layout.customize_details_item6, parent, false));
        }
        return new CustomizeDetailsItem6ViewHolder(mLayoutInflater.inflate(R.layout.customize_details_item6, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CustomizeDetailsItem1ViewHolder) {
            CustomizeDetailsItem1ViewHolder viewHolder = (CustomizeDetailsItem1ViewHolder) holder;
            viewHolder.photo.setImageResource(R.drawable.customize_details_photo);
            viewHolder.customize.setImageResource(R.drawable.customize_details_btn);
        } else if (holder instanceof CustomizeDetailsItem2ViewHolder) {
            CustomizeDetailsItem2ViewHolder viewHolder = (CustomizeDetailsItem2ViewHolder) holder;
            viewHolder.price.setText("￥" + mCustomizeCommodityBean.getPrice());
            viewHolder.price.getPaint().setFakeBoldText(true);
            viewHolder.commodityBrief.setText(mCustomizeCommodityBean.getCommodityBrief());
        } else if (holder instanceof CustomizeDetailsItem3ViewHolder) {
            CustomizeDetailsItem3ViewHolder viewHolder = (CustomizeDetailsItem3ViewHolder) holder;

            viewHolder.cycle.setText("+" + mCustomizeCommodityBean.getCycle());
            viewHolder.cycle.getPaint().setFakeBoldText(true);
            viewHolder.startNum.setText("" + mCustomizeCommodityBean.getStartNum());
            viewHolder.startNum.getPaint().setFakeBoldText(true);
            viewHolder.manualPrice.setText("" + mCustomizeCommodityBean.getManualPrice());
            viewHolder.manualPrice.getPaint().setFakeBoldText(true);
        } else if (holder instanceof CustomizeDetailsItem4ViewHolder) {
            CustomizeDetailsItem4ViewHolder viewHolder = (CustomizeDetailsItem4ViewHolder) holder;
          
        } else if (holder instanceof CustomizeDetailsItem5ViewHolder) {
            CustomizeDetailsItem5ViewHolder viewHolder = (CustomizeDetailsItem5ViewHolder) holder;
            viewHolder.norms.setText(mCustomizeCommodityBean.getNorms());
            viewHolder.num.setText(mCustomizeCommodityBean.getNum() + "件");
        } else if (holder instanceof CustomizeDetailsItem6ViewHolder) {
            CustomizeDetailsItem6ViewHolder viewHolder = (CustomizeDetailsItem6ViewHolder) holder;
            viewHolder.commodityInfo.setImageResource(mCustomizeCommodityBean.getSrcs().get(position - 5));
        }
    }

    @Override
    public int getItemCount() {
        return mCustomizeCommodityBean.getSrcs().size() + 5;
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
        }
        return TYPE_ITEM6;

    }

    class CustomizeDetailsItem1ViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        ImageView customize;

        public CustomizeDetailsItem1ViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.customize_details_photo);
            customize = (ImageView) itemView.findViewById(R.id.customize_details_iv);
            customize.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onClickListener(v, getPosition());
                    }
                }
            });
        }
    }

    class CustomizeDetailsItem2ViewHolder extends RecyclerView.ViewHolder {
        TextView price;
        TextView commodityBrief;
        ImageView collect;

        public CustomizeDetailsItem2ViewHolder(View itemView) {
            super(itemView);
            price = (TextView) itemView.findViewById(R.id.customize_details_price);

            commodityBrief = (TextView) itemView.findViewById(R.id.customize_details_commodityBrief);
            collect = (ImageView) itemView.findViewById(R.id.customize_details_collect);
        }
    }


    class CustomizeDetailsItem4ViewHolder extends RecyclerView.ViewHolder {
        public CustomizeDetailsItem4ViewHolder(View itemView) {
            super(itemView);
        }
    }

    class CustomizeDetailsItem5ViewHolder extends RecyclerView.ViewHolder {
        TextView norms;
        TextView num;

        public CustomizeDetailsItem5ViewHolder(View itemView) {
            super(itemView);
            norms = (TextView) itemView.findViewById(R.id.customize_details_norms);
            num = (TextView) itemView.findViewById(R.id.customize_details_num);
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

    class CustomizeDetailsItem3ViewHolder extends RecyclerView.ViewHolder {
        TextView cycle;
        TextView startNum;
        TextView manualPrice;

        public CustomizeDetailsItem3ViewHolder(View itemView) {
            super(itemView);
            cycle = (TextView) itemView.findViewById(R.id.customize_details_cycle);
            startNum = (TextView) itemView.findViewById(R.id.customize_details_startNum);
            manualPrice = (TextView) itemView.findViewById(R.id.customize_details_manualPrice);
        }
    }

    class CustomizeDetailsItem6ViewHolder extends RecyclerView.ViewHolder {
        ImageView commodityInfo;

        public CustomizeDetailsItem6ViewHolder(View itemView) {
            super(itemView);
            commodityInfo = (ImageView) itemView.findViewById(R.id.customize_details_commodityInfo);
        }
    }


}
