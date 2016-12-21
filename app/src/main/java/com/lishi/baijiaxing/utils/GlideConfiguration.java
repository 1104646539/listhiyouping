package com.lishi.baijiaxing.utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.stream.HttpUrlGlideUrlLoader;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.request.target.ViewTarget;
import com.lishi.baijiaxing.R;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/11/28.
 */

public class GlideConfiguration implements GlideModule {
    @Override
    public void applyOptions(final Context context, final GlideBuilder builder) {
        ViewTarget.setTagId(R.id.glide_tag_id);
        builder.setDiskCache(new DiskCache.Factory() {
            @Override
            public DiskCache build() {
                File cacheLocation = new File(context.getExternalCacheDir(), "image_cache");
                cacheLocation.mkdirs();
                return DiskLruCacheWrapper.get(cacheLocation, 10 * 1024 * 1024);
            }
        });
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(GlideUrl.class, InputStream.class, new HttpUrlGlideUrlLoader.Factory());
    }
}