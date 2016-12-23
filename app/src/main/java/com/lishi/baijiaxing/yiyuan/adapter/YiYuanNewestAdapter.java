package com.lishi.baijiaxing.yiyuan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.yiyuan.model.LotteryList;
import com.lishi.baijiaxing.yiyuan.model.YiYuanHotBean;
import com.lishi.baijiaxing.yiyuan.model.YiYuanNewestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/21.
 */
public class YiYuanNewestAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<LotteryList.DataBean.CommodityListBean> mYiYuanNewestBeans;
    private LayoutInflater mLayoutInflater;
    private YiYuanHotAdapter.OnItemClickListener mOnItemClickListener;

    public YiYuanNewestAdapter(Context context, List<LotteryList.DataBean.CommodityListBean> yiYuanNewestBeen) {
        this.mContext = context;
        this.mYiYuanNewestBeans = yiYuanNewestBeen;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setOnItemClickListener(YiYuanHotAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new YiYuanNewestViewHolder(mLayoutInflater.inflate(R.layout.item_yiyuan_newest, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof YiYuanNewestViewHolder) {
            YiYuanNewestViewHolder viewHolder = (YiYuanNewestViewHolder) holder;
            LotteryList.DataBean.CommodityListBean yiYuanNewestBean = mYiYuanNewestBeans.get(position);

            viewHolder.iv_photo.setImageResource(R.drawable.yiyuan_hot_photo);
            viewHolder.tv_name.setText(yiYuanNewestBean.getName());
            viewHolder.tv_num.setText(yiYuanNewestBean.getOoid());
            viewHolder.tv_winningName.setText(yiYuanNewestBean.getWinningUser());
//            viewHolder.tv_winningNum.setText("" + yiYuanNewestBean.getWinningNum());
            Glide.with(mContext).load(yiYuanNewestBean.getPhotoUrl()).into(viewHolder.iv_photo);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onClickListener(v, position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mYiYuanNewestBeans.size();
    }

    class YiYuanNewestViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_num, tv_winningNum, tv_winningName;
        private ImageView iv_photo;


        public YiYuanNewestViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.yiYuan_newest_name);
            iv_photo = (ImageView) itemView.findViewById(R.id.yiYuan_newest_photo);
            tv_num = (TextView) itemView.findViewById(R.id.yiyuan_newest_num);
            tv_winningName = (TextView) itemView.findViewById(R.id.yiyuan_newest_winningName);
            tv_winningNum = (TextView) itemView.findViewById(R.id.yiyuan_newest_winningNum);

        }
    }
}
