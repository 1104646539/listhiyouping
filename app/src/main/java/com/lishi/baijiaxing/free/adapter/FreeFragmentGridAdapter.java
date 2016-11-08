package com.lishi.baijiaxing.free.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.free.model.FreeCommodityBean;

import java.util.ArrayList;

/**
 * 免费领Grid
 * Created by Administrator on 2016/10/18.
 */
public class FreeFragmentGridAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<FreeCommodityBean> mFreeCommodityBeen;
    private LayoutInflater mLayoutInflater;
    private OnFreeGridItemClick monFreeGridClickLister;

    public void setMonFreeGridClickLister(OnFreeGridItemClick monFreeGridClickLister) {
        this.monFreeGridClickLister = monFreeGridClickLister;
    }

    public FreeFragmentGridAdapter(Context context, ArrayList<FreeCommodityBean> freeCommodityBeen) {
        this.mContext = context;
        this.mFreeCommodityBeen = freeCommodityBeen;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FreeGridViewHolder(mLayoutInflater.inflate(R.layout.free_item_grid, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int type = getItemViewType(position);
        FreeCommodityBean fcb = mFreeCommodityBeen.get(position);
        final FreeGridViewHolder viewHolder = (FreeGridViewHolder) holder;
        viewHolder.iv_photo.setImageResource(R.drawable.free_grid_photo);
        viewHolder.tv_title.setText(fcb.getName());
        viewHolder.tv_price.setText("￥" + fcb.getPrice() + "");
        viewHolder.tv_limitNum.setText("限量" + fcb.getLimitNum() + "件");
        viewHolder.tv_peopleNum.setText(fcb.getPeopleNum() + "人已申请");
        if (type == FreeCommodityBean.TYPE_BE_BEING_APPLY_NOT) {
            viewHolder.tv_state.setText("立即申请");
            viewHolder.tv_state.setBackgroundResource(R.drawable.tv_background_red);
        } else if (type == FreeCommodityBean.TYPE_BE_BEING_APPLY_OK) {
            viewHolder.tv_state.setText("已申请");
            viewHolder.tv_state.setBackgroundResource(R.drawable.tv_backgroud_black);
            viewHolder.tv_state.setTextColor(Color.parseColor("#FFFFFF"));
        } else if (type == FreeCommodityBean.TYPE_START_BEFORE) {
            viewHolder.tv_state.setText("即将开始");
            viewHolder.tv_state.setBackgroundResource(R.drawable.tv_backgroud_green);
            viewHolder.tv_state.setTextColor(Color.parseColor("#63dba0"));
        } else if (type == FreeCommodityBean.TYPE_FINISH) {
            viewHolder.tv_state.setText("已结束");
            viewHolder.tv_state.setBackgroundResource(R.drawable.tv_backgroud_black);
            viewHolder.tv_state.setTextColor(Color.parseColor("#FFFFFF"));
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (monFreeGridClickLister != null) {
                    monFreeGridClickLister.onFreeGridClickLister(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFreeCommodityBeen.size();
    }

    @Override
    public int getItemViewType(int position) {
        int type = mFreeCommodityBeen.get(position).getType();
        return type;
    }

    class FreeGridViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_price, tv_limitNum, tv_state, tv_peopleNum;
        ImageView iv_photo;


        public FreeGridViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.free_grid_title);
            tv_price = (TextView) itemView.findViewById(R.id.free_grid_price);
            tv_limitNum = (TextView) itemView.findViewById(R.id.free_grid_limitNum);
            tv_state = (TextView) itemView.findViewById(R.id.free_grid_state);
            tv_peopleNum = (TextView) itemView.findViewById(R.id.free_grid_peopleNum);
            iv_photo = (ImageView) itemView.findViewById(R.id.free_grid_photo);

        }
    }

    public interface OnFreeGridItemClick {
        void onFreeGridClickLister(View view, int position);
    }
}
