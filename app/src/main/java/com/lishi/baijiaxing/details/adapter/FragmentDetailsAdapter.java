package com.lishi.baijiaxing.details.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.details.model.CommodityDetails;
import com.lishi.baijiaxing.utils.PhotoPathUtil;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

/**
 * Created by Administrator on 2016/10/31.
 */
public class FragmentDetailsAdapter extends RecyclerView.Adapter {
    private CommodityDetails.DataBean mCommodityDetailsBeen;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private YiYuanHotAdapter.OnItemClickListener mOnItemClick;

    private final static int TYPE_ITEM1 = 0X001;
    private final static int TYPE_ITEM2 = 0X002;
    private final static int TYPE_ITEM3 = 0X003;
    private final static int TYPE_ITEM4 = 0X004;
    private int OtherVALUE = 3;
    private int screenWidth;

    private String number = "";

    public void setNumber(String number) {
        this.number = number;
    }

    private BitmapRequestBuilder<GlideUrl, Bitmap> requestBuidler;

    public FragmentDetailsAdapter(Context context, CommodityDetails.DataBean commodityDetailsBeen) {
        this.mCommodityDetailsBeen = commodityDetailsBeen;
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        requestBuidler = Glide.with(mContext).from(GlideUrl.class)
                .asBitmap().dontAnimate().diskCacheStrategy(DiskCacheStrategy.SOURCE).skipMemoryCache(false);
        screenWidth = ((Activity) mContext).getWindowManager().getDefaultDisplay().getWidth();
    }

    public void setOnItemClick(YiYuanHotAdapter.OnItemClickListener onItemClick) {
        mOnItemClick = onItemClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ITEM1:
                return new FragmentDetails1ViewHolder(mLayoutInflater.inflate(R.layout.commodity_details1, parent, false));
            case TYPE_ITEM2:
                return new FragmentDetails2ViewHolder(mLayoutInflater.inflate(R.layout.commodity_details2, parent, false));
            case TYPE_ITEM3:
                return new FragmentDetails3ViewHolder(mLayoutInflater.inflate(R.layout.commodity_details3, parent, false));
            case TYPE_ITEM4:
                return new FragmentDetails4ViewHolder(mLayoutInflater.inflate(R.layout.commodity_details4, parent, false));
        }
        return new FragmentDetails4ViewHolder(mLayoutInflater.inflate(R.layout.commodity_details4, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (mCommodityDetailsBeen.getCommodityUrls() != null && mCommodityDetailsBeen.getCommodityUrls().size() > 0) {
            if (holder instanceof FragmentDetails1ViewHolder) {
                final FragmentDetails1ViewHolder viewHolder = (FragmentDetails1ViewHolder) holder;
//                requestBuidler.load(new GlideUrl(mCommodityDetailsBeen.getCommodityUrls().get(0).getPhotoUrl())).dontAnimate()
//                        .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
//
//                            @Override
//                            public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
//                                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) viewHolder.photo.getLayoutParams();
//                                int bWidth = bitmap.getWidth();
//                                int bHeight = bitmap.getHeight();
//
//                                int height = (int) (screenWidth * 1.0F / bWidth * 1.0F * bHeight);
//                                lp.width = screenWidth;
//                                lp.height = height;
//                                viewHolder.photo.setLayoutParams(lp);
//                                viewHolder.photo.setImageBitmap(bitmap);
//                                Log.i("onResourceReady", "screenWidth:" + screenWidth
//                                        + "bWidth:" + bWidth + "bHeight" + bHeight + "height:" + height);
//                            }
//                        });
                String photoUrl = mCommodityDetailsBeen.getCommodityUrls().get(0).getPhotoUrl();
                if (!photoUrl.equals("")) {
                    photoUrl += PhotoPathUtil.getInstance().WIDTH_640;
                }
                Glide.with(mContext).load(photoUrl).placeholder(R.drawable.details_720x700)
                        .into(viewHolder.photo);
                
            } else if (holder instanceof FragmentDetails2ViewHolder) {
                FragmentDetails2ViewHolder viewHolder = (FragmentDetails2ViewHolder) holder;
                viewHolder.name.setText(mCommodityDetailsBeen.getName());
                viewHolder.price.setText("￥" + mCommodityDetailsBeen.getNowPrice());
            } else if (holder instanceof FragmentDetails3ViewHolder) {
                FragmentDetails3ViewHolder viewHolder = (FragmentDetails3ViewHolder) holder;
                if (number.equals("")) {
                    viewHolder.norms.setText("规格:");
                    viewHolder.number.setText("1件");
                } else {
                    viewHolder.norms.setText("规格:");
                    viewHolder.number.setText(number + "件");
                }
            } else if (holder instanceof FragmentDetails4ViewHolder) {
                final FragmentDetails4ViewHolder viewHolder = (FragmentDetails4ViewHolder) holder;
//            ImageLoader.getInstance().displayImage(mCommodityDetailsBeen.getBriefUrls().get(position - OtherVALUE), viewHolder.brief);
//            Glide.with(mContext).load(mCommodityDetailsBeen.getBriefUrls().get(position - OtherVALUE)).asBitmap().dontAnimate()
//                    .placeholder(R.drawable.default_ptr_rotate).into(new MyBitmapImageViewTarget(viewHolder.brief));
                if (position >= getItemCount()) {
                    return;
                }
                String photoUrl = mCommodityDetailsBeen.getBriefUrls().get(position - OtherVALUE);
                if (!photoUrl.equals("")) {
                    photoUrl += PhotoPathUtil.getInstance().WIDTH_640;
                }
                Glide.with(mContext).load(photoUrl).placeholder(R.drawable.details_720x700)
                        .into(viewHolder.brief);
                
//                requestBuidler.load(new GlideUrl(mCommodityDetailsBeen.getBriefUrls().get(position - OtherVALUE))).placeholder(R.drawable.details_720x700)
//                        .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
//
//                            @Override
//                            public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
//                                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) viewHolder.brief.getLayoutParams();
//                                int bWidth = bitmap.getWidth();
//                                int bHeight = bitmap.getHeight();
//
//                                int height = (int) (screenWidth * 1.0F / bWidth * 1.0F * bHeight);
//                                lp.width = screenWidth;
//                                lp.height = height;
//                                viewHolder.brief.setLayoutParams(lp);
//                                viewHolder.brief.setImageBitmap(bitmap);
//                                Log.i("onResourceReady", "screenWidth:" + screenWidth
//                                        + "bWidth:" + bWidth + "bHeight" + bHeight + "height:" + height);
//
//                            }
//
//                        });
                Log.i("onBindViewHolder", "url:" + photoUrl);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mCommodityDetailsBeen == null || mCommodityDetailsBeen.getBriefUrls() == null) {
            return 0;
        }
        Log.i("getItemCount", "size:" + mCommodityDetailsBeen.getBriefUrls().size() + "gid:" + mCommodityDetailsBeen.getCid());
        return mCommodityDetailsBeen.getBriefUrls().size() + OtherVALUE;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ITEM1;
        } else if (position == 1) {
            return TYPE_ITEM2;
        } else if (position == 2) {
            return TYPE_ITEM3;
        } else if (position >= 3) {
            return TYPE_ITEM4;
        }
        return TYPE_ITEM4;
    }

    class FragmentDetails1ViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;

        public FragmentDetails1ViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.commodity_details_photo);
        }
    }

    class FragmentDetails2ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;

        public FragmentDetails2ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.commodity_details_name);
            price = (TextView) itemView.findViewById(R.id.commodity_details_price);
        }
    }

    class FragmentDetails3ViewHolder extends RecyclerView.ViewHolder {
        TextView norms, number;

        public FragmentDetails3ViewHolder(View itemView) {
            super(itemView);
            norms = (TextView) itemView.findViewById(R.id.commodity_details_norms);
            number = (TextView) itemView.findViewById(R.id.commodity_details_number);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClick != null) {
                        mOnItemClick.onClickListener(v, getPosition());
                    }
                }
            });
        }
    }

    class FragmentDetails4ViewHolder extends RecyclerView.ViewHolder {
        ImageView brief;

        public FragmentDetails4ViewHolder(View itemView) {
            super(itemView);
            brief = (ImageView) itemView.findViewById(R.id.commodity_details_brief);
        }
    }

}
