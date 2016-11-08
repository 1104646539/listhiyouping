package com.lishi.baijiaxing.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.bean.CommentBean;
import com.lishi.baijiaxing.view.MyGridView;

import java.util.List;

/**
 * Created by Administrator on 2016/7/12.
 */
public class CommentAdapter extends BaseAdapter {
    private Context mContext;
    private List<CommentBean> mCommentBeen;
    private LayoutInflater mLayoutInflater;

    public CommentAdapter(Context context, List<CommentBean> commentBeens) {
        mContext = context;
        mCommentBeen = commentBeens;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mCommentBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return mCommentBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommentViewHolder holder = null;
        if (convertView == null) {
            holder = new CommentViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.item_comment, null, false);
            holder.iv_user_photo = (ImageView) convertView.findViewById(R.id.iv_comment_photo);
            holder.iv_comment_level = (ImageView) convertView.findViewById(R.id.iv_comment_comment_level);
            holder.iv_user_level = (ImageView) convertView.findViewById(R.id.iv_comment_user_level);
            holder.tv_username = (TextView) convertView.findViewById(R.id.tv_comment_username);
            holder.tv_comment_date = (TextView) convertView.findViewById(R.id.tv_comment_commentdate);
            holder.tv_shopping_date = (TextView) convertView.findViewById(R.id.tv_comment_shoppingdate);
            holder.tv_text = (TextView) convertView.findViewById(R.id.tv_comment_text);
            holder.mMyGridView = (MyGridView) convertView.findViewById(R.id.gridview_comment);
            convertView.setTag(holder);
        } else {
            holder = (CommentViewHolder) convertView.getTag();
        }
        final CommentBean commentBean = mCommentBeen.get(position);
        if (commentBean.getImgs() == null || commentBean.getImgs().size() == 0) {
            holder.mMyGridView.setVisibility(View.GONE);
            Log.i("asdfa", "____________________没有图片");
        } else {
            Log.i("asdfa", "____________________有图片");
            holder.mMyGridView.setVisibility(View.VISIBLE);
            holder.mMyGridView.setAdapter(new BaseAdapter() {
                @Override
                public int getCount() {
                    return commentBean.getImgs().size();
                }

                @Override
                public Object getItem(int position) {
                    return commentBean.getImgs().get(position);
                }

                @Override
                public long getItemId(int position) {
                    return position;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    CommentPhotoViewHolder viewHolder = null;
                    if (convertView == null) {
                        convertView = mLayoutInflater.inflate(R.layout.item_comment_photo, null, false);
                        viewHolder = new CommentPhotoViewHolder();
                        viewHolder.iv_photo = (ImageView) convertView.findViewById(R.id.iv_comment_photo_photo);
                        convertView.setTag(viewHolder);
                    } else {
                        viewHolder = (CommentPhotoViewHolder) convertView.getTag();
                    }
                    Glide.with(mContext).load(commentBean.getImgs().get(position)).into(viewHolder.iv_photo).onStart();
//                    ImageLoader.getInstance().displayImage(commentBean.getImgs().get(position),viewHolder.iv_photo);
                    Log.i("asf", "_____url是" + commentBean.getImgs().get(position));

//                    viewHolder.iv_photo.setImageResource(R.drawable.classity_item);
                    return convertView;
                }

                class CommentPhotoViewHolder {
                    ImageView iv_photo;
                }
            });
        }

        holder.iv_user_photo.setImageResource(R.drawable.user_photo);
        holder.iv_comment_level.setImageResource(R.drawable.level);
        holder.iv_user_level.setImageResource(R.drawable.user_leve);
        holder.tv_username.setText(commentBean.getUser_name());
        holder.tv_comment_date.setText(commentBean.getComment_date());
        holder.tv_shopping_date.setText(commentBean.getShopping_date());
        holder.tv_text.setText(commentBean.getComment_text());

        return convertView;
    }

    class CommentViewHolder {
        ImageView iv_user_photo, iv_user_level, iv_comment_level;
        TextView tv_username, tv_comment_date, tv_text, tv_shopping_date;
        MyGridView mMyGridView;
    }
}
