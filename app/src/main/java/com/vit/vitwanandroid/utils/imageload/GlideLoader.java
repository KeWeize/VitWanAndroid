package com.vit.vitwanandroid.utils.imageload;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.request.RequestOptions;
import com.vit.vitwanandroid.R;

/**
 * @author kewz
 * @date 2018/3/16
 */

public class GlideLoader {

    private static final int DEFAULT_ERROR_ID = R.drawable.common_show_default_photo;
    private static final int DEFAULT_PLACEHOLDER_ID = R.drawable.common_show_default_photo;

    /**
     * 初始化Glide配置
     * @param context
     * @param cacheSizeInM
     * @param memoryCategory
     * @param isInternalCD
     */
    public static void init(Context context, int cacheSizeInM, MemoryCategory memoryCategory, boolean isInternalCD) {
        Glide.get(context).setMemoryCategory(memoryCategory);
        GlideBuilder builder = new GlideBuilder();
        if (isInternalCD) {
            //内部缓存
            builder.setDiskCache(new InternalCacheDiskCacheFactory(context, cacheSizeInM * 1024 * 1024));
        } else {
            //外部缓存
            builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, cacheSizeInM * 1024 * 1024));
        }
    }


    public static void loadImage(Context context, String url, ImageView imageView) {
        loadImage(context, url, imageView, 0, 0);
    }

    public static void loadImage(Context context, String url, ImageView imageView, int placeholderId, int errorId) {

        RequestOptions requestOptions = new RequestOptions();
        placeholderId = placeholderId > 0 ? placeholderId : DEFAULT_PLACEHOLDER_ID;
        errorId = errorId > 0 ? errorId : DEFAULT_ERROR_ID;
        requestOptions.error(errorId);
        requestOptions.placeholder(placeholderId);

        Glide.with(context).load(url).apply(requestOptions).into(imageView);
    }

}
