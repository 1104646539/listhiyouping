package com.lishi.baijiaxing.weeklyChoiceness.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.integral.model.IntegralBean;
import com.lishi.baijiaxing.weeklyChoiceness.model.WeeklyBean;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/2.
 */
public class WeeklyChoicenessAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<WeeklyBean> mWeeklyBeen;
    private LayoutInflater mLayoutInflater;
    private YiYuanHotAdapter.OnItemClickListener mOnItemClickListener;

    private static final int TYPE_HEAD = 0X001;
    private static final int TYPE_Photo = 0X002;

    public WeeklyChoicenessAdapter(Context context, List<WeeklyBean> weeklyBeen) {
        this.mContext = context;
        this.mWeeklyBeen = weeklyBeen;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setOnItemClickListener(YiYuanHotAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_Photo) {
            return new WeeklylViewHolder(mLayoutInflater.inflate(R.layout.weekly_item, parent, false));
        }
        return new WeeklyHeadViewHolder(mLayoutInflater.inflate(R.layout.weekly_item_head, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof WeeklylViewHolder) {
            WeeklylViewHolder viewHolder = (WeeklylViewHolder) holder;
            WeeklyBean integral = mWeeklyBeen.get(position - 1);
            viewHolder.price.setText(integral.getPrice() + "");
            viewHolder.name.setText(integral.getName());
            viewHolder.photo.setImageResource(R.drawable.weekly_choiceness_photo);

            if (position % 2 == 0 && position != 0) {
                GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) viewHolder.itemView.getLayoutParams();
                lp.rightMargin = lp.leftMargin;
            } else {
                GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) viewHolder.itemView.getLayoutParams();
                lp.rightMargin = 0;
            }
        } else {
            WeeklyHeadViewHolder viewHolder = (WeeklyHeadViewHolder) holder;
            viewHolder.headPhoto.setImageResource(R.drawable.weekly_choiceness_brief);
        }

    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEAD;
            default:
                return TYPE_Photo;
        }
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
                        case TYPE_HEAD:
                            return 2;
                        case TYPE_Photo:
                            return 1;
                    }
                    return 2;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mWeeklyBeen.size() + 1;
    }

    class WeeklylViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView price, name;

        public WeeklylViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.weekly_choiceness_photo);
            price = (TextView) itemView.findViewById(R.id.weekly_choiceness_price);
            name = (TextView) itemView.findViewById(R.id.weekly_choiceness_name);
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

    class WeeklyHeadViewHolder extends RecyclerView.ViewHolder {
        ImageView headPhoto;

        public WeeklyHeadViewHolder(View itemView) {
            super(itemView);
            headPhoto = (ImageView) itemView.findViewById(R.id.weekly_choiceness_headPhoto);
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
}
