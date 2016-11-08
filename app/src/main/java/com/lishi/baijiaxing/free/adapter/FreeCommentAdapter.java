package com.lishi.baijiaxing.free.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.free.model.FreeCommentBean;
import com.lishi.baijiaxing.view.MyListView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/20.
 */
public class FreeCommentAdapter extends RecyclerView.Adapter {
    private ArrayList<FreeCommentBean> mCommentBeans;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnReplyClickLister mOnReplyClickLister;

    public void setOnReplyClickLister(OnReplyClickLister onReplyClickLister) {
        mOnReplyClickLister = onReplyClickLister;
    }

    public FreeCommentAdapter(Context context, ArrayList<FreeCommentBean> commentBeans) {
        this.mCommentBeans = commentBeans;
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FreeCommentViewHolder(mLayoutInflater.inflate(R.layout.free_comment, parent, false));
    }
    
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof FreeCommentViewHolder) {
            FreeCommentViewHolder viewHolder = (FreeCommentViewHolder) holder;
            FreeCommentBean commendBean = mCommentBeans.get(position);
            viewHolder.tv_name.setText(commendBean.getName());
            viewHolder.tv_time.setText(commendBean.getTime());
            viewHolder.tv_zan.setText(commendBean.getZans() + "");
            if (commendBean.getReplyBeans() == null || commendBean.getReplyBeans().size() == 0) {
                viewHolder.tv_reply.setText("0");
            } else {
                viewHolder.tv_reply.setText(commendBean.getReplyBeans().size() + "");
            }
            FreeReplyAdapter freeReplyAdapter = new FreeReplyAdapter(mContext, commendBean.getReplyBeans());
            viewHolder.mMyListView.setAdapter(freeReplyAdapter);
            
            viewHolder.tv_info.setText(commendBean.getInfo());

            viewHolder.tv_reply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnReplyClickLister != null) {
                        mOnReplyClickLister.onReplyClickLister(v, position);
                    }
                }
            });
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnReplyClickLister != null) {
                        mOnReplyClickLister.onOntherReplyClickLister(v, position);
                    }
                }
            });
            viewHolder.iv_reply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnReplyClickLister != null) {
                        mOnReplyClickLister.onReplyClickLister(v, position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mCommentBeans.size();
    }

    class FreeCommentViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_time, tv_info, tv_reply, tv_zan;
        private ImageView iv_reply, iv_zan;
        private MyListView mMyListView;

        public FreeCommentViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.comment_name);
            tv_time = (TextView) itemView.findViewById(R.id.comment_time);
            tv_info = (TextView) itemView.findViewById(R.id.comment_info);
            tv_reply = (TextView) itemView.findViewById(R.id.comment_reply_tv);
            tv_zan = (TextView) itemView.findViewById(R.id.comment_zan_tv);

            iv_reply = (ImageView) itemView.findViewById(R.id.comment_reply_iv);
            iv_zan = (ImageView) itemView.findViewById(R.id.comment_zan_iv);
            mMyListView = (MyListView) itemView.findViewById(R.id.listView_comment_reply);
        }
    }

    public interface OnReplyClickLister {
        void onReplyClickLister(View view, int position);

        void onOntherReplyClickLister(View view, int postion);
    }
}
