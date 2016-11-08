package com.lishi.baijiaxing.free.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.free.model.FreeWinningBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/21.
 */
public class FreeWinningAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<FreeWinningBean> mFreeWinningBeens;
    private LayoutInflater mLayoutInflater;

    public FreeWinningAdapter(Context context, ArrayList<FreeWinningBean> freeWinningBeans) {
        this.mContext = context;
        this.mFreeWinningBeens = freeWinningBeans;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FreeWinningViewHolder(mLayoutInflater.inflate(R.layout.item_free_winning, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder != null) {
            if (holder instanceof FreeWinningViewHolder) {
                FreeWinningViewHolder viewHolder = (FreeWinningViewHolder) holder;
                FreeWinningBean freeWinningBean = mFreeWinningBeens.get(position);

                viewHolder.iv_head.setImageResource(R.drawable.commend_head);
                viewHolder.tv_name.setText(freeWinningBean.getName());
                viewHolder.tv_address.setText(freeWinningBean.getAddress());
            }
        }
    }

    @Override
    public int getItemCount() {
        return mFreeWinningBeens.size();
    }

    class FreeWinningViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_head;
        private TextView tv_name, tv_address;

        public FreeWinningViewHolder(View itemView) {
            super(itemView);
            iv_head = (ImageView) itemView.findViewById(R.id.free_winning_head);
            tv_name = (TextView) itemView.findViewById(R.id.free_winning_name);
            tv_address = (TextView) itemView.findViewById(R.id.free_winning_address);

        }
    }
}
