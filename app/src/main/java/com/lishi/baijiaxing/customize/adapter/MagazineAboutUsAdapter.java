package com.lishi.baijiaxing.customize.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;

/**
 * Created by Administrator on 2016/11/14.
 */

public class MagazineAboutUsAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private final static int TYPE_PHOTO = 0X001;
    private final static int TYPE_CUSTOMIZE1 = 0X002;
    private final static int TYPE_CUSTOMIZE2 = 0X003;

    private OnCustomizeClickListener mOnCustomizeClickListener;

    public void setOnCustomizeClickListener(OnCustomizeClickListener onCustomizeClickListener) {
        mOnCustomizeClickListener = onCustomizeClickListener;
    }

    public MagazineAboutUsAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_PHOTO:
                return new MagazineAboutUsViewHolder(mLayoutInflater.inflate(R.layout.magazine_about_us_iv, parent, false));
            case TYPE_CUSTOMIZE1:
                return new MagazineAboutUsCustomize1ViewHolder(mLayoutInflater.inflate(R.layout.magazine_about_us_customize1, parent, false));
            case TYPE_CUSTOMIZE2:
                return new MagazineAboutUsCustomize2ViewHolder(mLayoutInflater.inflate(R.layout.magazine_about_us_customize2, parent, false));
        }
        return new MagazineAboutUsCustomize2ViewHolder(mLayoutInflater.inflate(R.layout.magazine_about_us_customize2, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MagazineAboutUsViewHolder) {
            MagazineAboutUsViewHolder viewHolder = (MagazineAboutUsViewHolder) holder;
            switchPhoto(position, viewHolder);
        } else if (holder instanceof MagazineAboutUsCustomize1ViewHolder) {
            MagazineAboutUsCustomize1ViewHolder viewHolder = (MagazineAboutUsCustomize1ViewHolder) holder;
            viewHolder.photo.setOnClickListener(this);
        } else {
            MagazineAboutUsCustomize2ViewHolder viewHolder = (MagazineAboutUsCustomize2ViewHolder) holder;
            viewHolder.photo.setOnClickListener(this);
        }
    }

    private void switchPhoto(int position, MagazineAboutUsViewHolder viewHolder) {
        switch (position) {
            case 0:
                viewHolder.photo.setImageResource(R.drawable.magazine_about_us2);
                break;
            case 1:
                viewHolder.photo.setImageResource(R.drawable.magazine_about_us3);
                break;
            case 3:
                viewHolder.photo.setImageResource(R.drawable.magazine_about_us5);
                break;
            case 4:
                viewHolder.photo.setImageResource(R.drawable.magazine_about_us6);
                break;
            case 5:
                viewHolder.photo.setImageResource(R.drawable.magazine_about_us7);
                break;
            case 6:
                viewHolder.photo.setImageResource(R.drawable.magazine_about_us8);
                break;
            case 7:
                viewHolder.photo.setImageResource(R.drawable.magazine_about_us9);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 2) {
            return TYPE_CUSTOMIZE1;
        } else if (position == getItemCount() - 1) {
            return TYPE_CUSTOMIZE2;
        } else {
            return TYPE_PHOTO;
        }
    }

    @Override
    public void onClick(View v) {
        if (mOnCustomizeClickListener != null) {
            mOnCustomizeClickListener.onCustomizeClickListener();
        }
    }

    class MagazineAboutUsViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;

        public MagazineAboutUsViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.magazine_about_us_photo);
        }
    }

    class MagazineAboutUsCustomize1ViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;

        public MagazineAboutUsCustomize1ViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.magazine_about_us_customize1);
        }
    }

    class MagazineAboutUsCustomize2ViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;

        public MagazineAboutUsCustomize2ViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.magazine_about_us_customize2);
        }
    }

    public interface OnCustomizeClickListener {
        void onCustomizeClickListener();
    }
}
