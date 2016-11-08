package com.lishi.baijiaxing.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lishi.baijiaxing.inter.RecyclerViewItemonClick;

/**
 * Created by Administrator on 2016/6/12.
 */
public class MyRecyclerViewViewHoder extends RecyclerView.ViewHolder {
    private View mItemView;
    private RecyclerViewItemonClick mRecyclerViewItemonClick;

    public MyRecyclerViewViewHoder(View itemView, RecyclerViewItemonClick recyclerViewItemonClick) {
        super(itemView);
        mRecyclerViewItemonClick = recyclerViewItemonClick;
        mItemView = itemView;
        
        
        if (mRecyclerViewItemonClick != null) {
            mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRecyclerViewItemonClick.OnRecyclerViewItemClickListener(mItemView, getPosition());
                }
            });
        }
    }
    

}
