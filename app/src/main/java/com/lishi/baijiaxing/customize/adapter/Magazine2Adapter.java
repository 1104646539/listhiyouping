package com.lishi.baijiaxing.customize.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.customize.model.MagazineBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */

public class Magazine2Adapter extends RecyclerView.Adapter implements View.OnClickListener {
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private final static int TYPE_ITEM1 = 0x001;
    private final static int TYPE_ITEM2 = 0x002;
    private final static int TYPE_ITEM3 = 0x003;
    private final static int TYPE_ITEM4 = 0x004;
    private final static int TYPE_ITEM5 = 0x005;

    private String uploadPath = "";

    private OnMagazine2ClickListener mOnMagazine2ClickListener;

    public Magazine2Adapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setOnMagazine2ClickListener(OnMagazine2ClickListener onMagazine2ClickListener) {
        mOnMagazine2ClickListener = onMagazine2ClickListener;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ITEM1:
                return new MagazineItem1ViewHolder(mLayoutInflater.inflate(R.layout.magazine2_item1, parent, false));
            case TYPE_ITEM2:
                return new MagazineItem2ViewHolder(mLayoutInflater.inflate(R.layout.magazine_item4, parent, false));
            case TYPE_ITEM3:
                return new MagazineItem3ViewHolder(mLayoutInflater.inflate(R.layout.magazine2_item2, parent, false));
            case TYPE_ITEM4:
                return new MagazineItem4ViewHolder(mLayoutInflater.inflate(R.layout.magazine2_item3, parent, false));
            case TYPE_ITEM5:
                return new MagazineItem5ViewHolder(mLayoutInflater.inflate(R.layout.magazine2_item4, parent, false));
        }
        return new MagazineItem5ViewHolder(mLayoutInflater.inflate(R.layout.magazine2_item4, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MagazineItem1ViewHolder) {
            MagazineItem1ViewHolder viewHolder = (MagazineItem1ViewHolder) holder;
        } else if (holder instanceof MagazineItem2ViewHolder) {
            MagazineItem2ViewHolder viewHolder = (MagazineItem2ViewHolder) holder;
            viewHolder.btn.setVisibility(View.GONE);
        } else if (holder instanceof MagazineItem3ViewHolder) {
            MagazineItem3ViewHolder viewHolder = (MagazineItem3ViewHolder) holder;
            viewHolder.upload.setOnClickListener(this);
            if (uploadPath == null || uploadPath.equals("")) {
                return;
            }
            Glide.with(mContext).load(uploadPath).into(viewHolder.mImageView);
        } else if (holder instanceof MagazineItem4ViewHolder) {
            MagazineItem4ViewHolder viewHolder = (MagazineItem4ViewHolder) holder;
            viewHolder.submit.setOnClickListener(this);
        } else if (holder instanceof MagazineItem5ViewHolder) {
            MagazineItem5ViewHolder viewHolder = (MagazineItem5ViewHolder) holder;
            viewHolder.service.setOnClickListener(this);
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ITEM1;
        } else if (position == 1) {
            return TYPE_ITEM2;
        } else if (position == 2) {
            return TYPE_ITEM3;
        } else if (position == 3) {
            return TYPE_ITEM4;
        } else {
            return TYPE_ITEM5;
        }
    }

    @Override
    public void onClick(View v) {
        if (mOnMagazine2ClickListener != null) {
            switch (v.getId()) {
                case R.id.magazine2_item2_upload:
                    mOnMagazine2ClickListener.onUpload();
                    break;
                case R.id.magazine2_item3_submit:
                    mOnMagazine2ClickListener.onSubmit();
                    break;
                case R.id.magazine2_item4_service:
                    mOnMagazine2ClickListener.onService();
                    break;
            }
        }
    }

    class MagazineItem1ViewHolder extends RecyclerView.ViewHolder {

        public MagazineItem1ViewHolder(View itemView) {
            super(itemView);
        }
    }

    class MagazineItem2ViewHolder extends RecyclerView.ViewHolder {
        TextView btn;

        public MagazineItem2ViewHolder(View itemView) {
            super(itemView);
            btn = (TextView) itemView.findViewById(R.id.magazine_item4_bottom);
        }
    }

    class MagazineItem3ViewHolder extends RecyclerView.ViewHolder {
        TextView upload;
        ImageView mImageView;

        public MagazineItem3ViewHolder(View itemView) {
            super(itemView);
            upload = (TextView) itemView.findViewById(R.id.magazine2_item2_upload);
            mImageView = (ImageView) itemView.findViewById(R.id.magazine2_item2_image);
        }
    }

    class MagazineItem4ViewHolder extends RecyclerView.ViewHolder {
        TextView submit;

        public MagazineItem4ViewHolder(View itemView) {
            super(itemView);
            submit = (TextView) itemView.findViewById(R.id.magazine2_item3_submit);
        }
    }

    class MagazineItem5ViewHolder extends RecyclerView.ViewHolder {
        TextView service;

        public MagazineItem5ViewHolder(View itemView) {
            super(itemView);
            service = (TextView) itemView.findViewById(R.id.magazine2_item4_service);
        }
    }

    public interface OnMagazine2ClickListener {
        /**
         * 上传
         */
        void onUpload();

        /**
         * 提交
         */
        void onSubmit();

        /**
         * 客服
         */
        void onService();
    }

}
