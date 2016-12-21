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

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.utils.PhotoPathUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 */
public class FragmentBriefAdapter extends RecyclerView.Adapter {
    private final int screenWidth;
    private Context mContext;
    private List<String> photoUrls;
    private LayoutInflater mLayoutInflater;

    private BitmapRequestBuilder<GlideUrl, Bitmap> requestBuidler;

    public FragmentBriefAdapter(Context context, List<String> srcs) {
        this.mContext = context;
        this.photoUrls = srcs;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        requestBuidler = Glide.with(mContext).from(GlideUrl.class)
                .asBitmap().dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL);
        screenWidth = ((Activity) mContext).getWindowManager().getDefaultDisplay().getWidth();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FragmentBriefViewHolder(mLayoutInflater.inflate(R.layout.commodity_details4, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final FragmentBriefViewHolder viewHolder = (FragmentBriefViewHolder) holder;
//        viewHolder.brief.setImageResource(photoUrls.get(position));
//        requestBuidler.load(new GlideUrl(photoUrls.get(position)))
//                .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
//
//                    @Override
//                    public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
//                        // Do something with bitmap here.
//
//                        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) viewHolder.brief.getLayoutParams();
//                        int bWidth = bitmap.getWidth();
//                        int bHeight = bitmap.getHeight();
//
//                        int height = (int) (screenWidth * 1.0F / bWidth * 1.0F * bHeight);
//                        lp.width = screenWidth;
//                        lp.height = height;
//                        viewHolder.brief.setLayoutParams(lp);
//                        viewHolder.brief.setImageBitmap(bitmap);
//                        Log.i("onResourceReady", "screenWidth:" + screenWidth
//                                + "bWidth:" + bWidth + "bHeight" + bHeight + "height:" + height);
//
//                    }
//
//                });
        String photoUrl = photoUrls.get(position);
        if (!photoUrl.equals("")) {
            photoUrl += PhotoPathUtil.getInstance().WIDTH_640;
        }
        Glide.with(mContext).load(photoUrl).placeholder(R.drawable.details_720x700)
                .into(viewHolder.brief);
    }

    @Override
    public int getItemCount() {
        return photoUrls.size();
    }

    class FragmentBriefViewHolder extends RecyclerView.ViewHolder {
        ImageView brief;

        public FragmentBriefViewHolder(View itemView) {
            super(itemView);
            brief = (ImageView) itemView.findViewById(R.id.commodity_details_brief);
        }
    }
}
