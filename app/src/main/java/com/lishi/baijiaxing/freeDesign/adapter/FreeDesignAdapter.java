package com.lishi.baijiaxing.freeDesign.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.freeDesign.model.FreeDesignBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/7.
 */
public class FreeDesignAdapter extends RecyclerView.Adapter {
    private List<FreeDesignBean> mFreeDesignBeen;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private static final int TYPE_LEFT = 0X001;
    private static final int TYPE_RIGHT = 0X002;


    public FreeDesignAdapter(Context context, List<FreeDesignBean> freeDesignBeanList) {
        this.mContext = context;
        this.mFreeDesignBeen = freeDesignBeanList;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_LEFT) {
            return new FreeDesignLeftViewHolder(mLayoutInflater.inflate(R.layout.free_design_item_left, parent, false));
        }
        return new FreeDesignRightViewHolder(mLayoutInflater.inflate(R.layout.free_design_item_right, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FreeDesignBean freeDesignBean = mFreeDesignBeen.get(position);
        if (holder instanceof FreeDesignLeftViewHolder) {
            FreeDesignLeftViewHolder viewHolder = (FreeDesignLeftViewHolder) holder;
            viewHolder.left_photo.setImageResource(freeDesignBean.getPhotoUrl());
            viewHolder.left_name.setText(freeDesignBean.getName());
            viewHolder.left_brief.setText(freeDesignBean.getBrief());
            viewHolder.left_num.setText(freeDesignBean.getNum() + "个起订");
            viewHolder.left_oldPrice.setText("原价：" + freeDesignBean.getOldPrice());
            viewHolder.left_nowPrice.setText("现价：" + freeDesignBean.getNowPrice());
            viewHolder.left_info.setText(freeDesignBean.getInfo());
        } else {
            FreeDesignRightViewHolder viewHolder = (FreeDesignRightViewHolder) holder;
            viewHolder.right_photo.setImageResource(freeDesignBean.getPhotoUrl());
            viewHolder.right_name.setText(freeDesignBean.getName());
            viewHolder.right_brief.setText(freeDesignBean.getBrief());
            viewHolder.right_num.setText(freeDesignBean.getNum() + "个起订");
            viewHolder.right_oldPrice.setText("原价：" + freeDesignBean.getOldPrice());
            viewHolder.right_nowPrice.setText("现价：" + freeDesignBean.getNowPrice());
            viewHolder.right_info.setText(freeDesignBean.getInfo());
        }
        if (position == getItemCount() - 1) {
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
            lp.bottomMargin = lp.topMargin;
            
        }

    }

    @Override
    public int getItemCount() {
        return mFreeDesignBeen.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 1 ? TYPE_LEFT : TYPE_RIGHT;
    }

    class FreeDesignLeftViewHolder extends RecyclerView.ViewHolder {
        TextView left_name, left_brief, left_num, left_oldPrice, left_nowPrice, left_info;
        ImageView left_photo;

        public FreeDesignLeftViewHolder(View itemView) {
            super(itemView);
            left_name = (TextView) itemView.findViewById(R.id.free_design_left_name);
            left_brief = (TextView) itemView.findViewById(R.id.free_design_left_brief);
            left_num = (TextView) itemView.findViewById(R.id.free_design_left_num);
            left_oldPrice = (TextView) itemView.findViewById(R.id.free_design_left_nowPrice);
            left_nowPrice = (TextView) itemView.findViewById(R.id.free_design_left_nowPrice);
            left_info = (TextView) itemView.findViewById(R.id.free_design_left_info);
            left_photo = (ImageView) itemView.findViewById(R.id.free_design_left_photo);
        }
    }

    class FreeDesignRightViewHolder extends RecyclerView.ViewHolder {
        TextView right_name, right_brief, right_num, right_oldPrice, right_nowPrice, right_info;
        ImageView right_photo;

        public FreeDesignRightViewHolder(View itemView) {
            super(itemView);
            right_name = (TextView) itemView.findViewById(R.id.free_design_right_name);
            right_brief = (TextView) itemView.findViewById(R.id.free_design_right_brief);
            right_num = (TextView) itemView.findViewById(R.id.free_design_right_num);
            right_oldPrice = (TextView) itemView.findViewById(R.id.free_design_right_nowPrice);
            right_nowPrice = (TextView) itemView.findViewById(R.id.free_design_right_nowPrice);
            right_info = (TextView) itemView.findViewById(R.id.free_design_right_info);
            right_photo = (ImageView) itemView.findViewById(R.id.free_design_right_photo);
        }
    }
}
