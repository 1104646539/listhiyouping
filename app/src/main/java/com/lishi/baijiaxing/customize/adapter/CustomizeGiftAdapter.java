package com.lishi.baijiaxing.customize.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.customize.model.CustomizeGiftBean;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

/**
 * Created by Administrator on 2016/10/26.
 */
public class CustomizeGiftAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private CustomizeGiftBean mCustomizeGiftBean;
    private LayoutInflater mLayoutInflater;
    private YiYuanHotAdapter.OnItemClickListener mOnItemClickListener;

    public CustomizeGiftAdapter(Context context, CustomizeGiftBean customizeGiftBean) {
        this.mContext = context;
        this.mCustomizeGiftBean = customizeGiftBean;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setOnItemClickListener(YiYuanHotAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CustomizeGiftViewHolder(mLayoutInflater.inflate(R.layout.customize_gift_item1, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CustomizeGiftViewHolder viewHolder = (CustomizeGiftViewHolder) holder;
        if (position == 0) {
            viewHolder.mImageView.setImageResource(R.drawable.customize_gift_iv1);
        } else if (position == 1) {
            viewHolder.mImageView.setImageResource(R.drawable.customize_gift_iv2);
        } else {
            viewHolder.mImageView.setImageResource(mCustomizeGiftBean.getAdvertisementUrls().get(position - 2));
        }
    }

    @Override
    public int getItemCount() {
        return 2 + mCustomizeGiftBean.getAdvertisementUrls().size();
    }

    class CustomizeGiftViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        public CustomizeGiftViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.customize_gift_iv);

            mImageView.setOnClickListener(new View.OnClickListener() {
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
