package com.lishi.baijiaxing.free.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.free.model.ReplyBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/20.
 */
public class FreeReplyAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<ReplyBean> mReplyBeen;
    private LayoutInflater mLayoutInflater;

    public FreeReplyAdapter(Context context, ArrayList<ReplyBean> replyBeen) {
        this.mContext = context;
        this.mReplyBeen = replyBeen;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mReplyBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return mReplyBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mReplyBeen != null) {
            FreeReplyViewHolder viewHolder;
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.free_comment_reply, parent, false);
                viewHolder = new FreeReplyViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (FreeReplyViewHolder) convertView.getTag();
            }
            ReplyBean replyBean = mReplyBeen.get(position);
            viewHolder.tv_reply_name.setText(replyBean.getName());
            viewHolder.tv_reply_info.setText(": " + replyBean.getInfo());
        }
        return convertView;
    }

    class FreeReplyViewHolder {
        private TextView tv_reply_name;
        private TextView tv_reply_info;

        public FreeReplyViewHolder(View itemView) {
            tv_reply_name = (TextView) itemView.findViewById(R.id.comment_reply_name);
            tv_reply_info = (TextView) itemView.findViewById(R.id.comment_reply_info);
        }
    }
}
