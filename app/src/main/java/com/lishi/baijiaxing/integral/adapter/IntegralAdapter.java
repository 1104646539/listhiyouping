package com.lishi.baijiaxing.integral.adapter;

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
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

import java.util.List;

import retrofit2.http.Header;

/**
 * Created by Administrator on 2016/11/2.
 */
public class IntegralAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<IntegralBean> mIntegralBeen;
    private LayoutInflater mLayoutInflater;
    private YiYuanHotAdapter.OnItemClickListener mOnItemClickListener;

    private static final int TYPE_HEAD = 0X001;
    private static final int TYPE_Photo = 0X002;

    public IntegralAdapter(Context context, List<IntegralBean> integralBeen) {
        this.mContext = context;
        this.mIntegralBeen = integralBeen;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setOnItemClickListener(YiYuanHotAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_Photo) {
            return new IntegralViewHolder(mLayoutInflater.inflate(R.layout.integral_item, parent, false));
        }
        return new IntegralHeadViewHolder(mLayoutInflater.inflate(R.layout.integral_item_head, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof IntegralViewHolder) {
            IntegralViewHolder viewHolder = (IntegralViewHolder) holder;
            IntegralBean integral = mIntegralBeen.get(position - 1);
            viewHolder.needIntegral.setText(integral.getNeedIntegral() + "积分");
            viewHolder.name.setText(integral.getName());
            viewHolder.photo.setImageResource(R.drawable.integral_exchange_photo);

            if (position % 2 == 0 && position != 0) {
                GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) viewHolder.itemView.getLayoutParams();
                lp.rightMargin = lp.leftMargin;
            } else {
                GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) viewHolder.itemView.getLayoutParams();
                lp.rightMargin = 0;
            }
        } else {
            IntegralHeadViewHolder viewHolder = (IntegralHeadViewHolder) holder;
            viewHolder.headPhoto.setImageResource(R.drawable.integral_exchange_brief);
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
        return mIntegralBeen.size() + 1;
    }

    class IntegralViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView needIntegral, name;

        public IntegralViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.integral_exchange_photo);
            needIntegral = (TextView) itemView.findViewById(R.id.integral_exchange_needIntegral);
            name = (TextView) itemView.findViewById(R.id.integral_exchange_name);
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

    class IntegralHeadViewHolder extends RecyclerView.ViewHolder {
        ImageView headPhoto;

        public IntegralHeadViewHolder(View itemView) {
            super(itemView);
            headPhoto = (ImageView) itemView.findViewById(R.id.integral_exchange_headPhoto);
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
