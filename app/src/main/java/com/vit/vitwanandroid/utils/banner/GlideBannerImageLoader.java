package com.vit.vitwanandroid.utils.banner;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vit.vitwanandroid.utils.imageload.GlideLoader;
import com.youth.banner.loader.ImageLoader;

/**
 * @author kewz
 * @date 2018/3/16
 */

public class GlideBannerImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }

}
