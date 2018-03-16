package com.vit.vitwanandroid.widget.brvah;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.vit.vitwanandroid.utils.imageload.GlideLoader;

/**
 * @author kewz
 * @date 2018/3/16
 */

public class VitViewHolder extends BaseViewHolder {

    public VitViewHolder(View view) {
        super(view);
    }

    @Override
    public VitViewHolder setText(int viewId, CharSequence value) {
        return (VitViewHolder)super.setText(viewId, value);
    }

    @Override
    public VitViewHolder setText(int viewId, int strId) {
        return (VitViewHolder)super.setText(viewId, strId);
    }

    @Override
    public VitViewHolder setImageResource(int viewId, int imageResId) {
        return (VitViewHolder)super.setImageResource(viewId, imageResId);
    }

    @Override
    public VitViewHolder setBackgroundColor(int viewId, int color) {
        return (VitViewHolder)super.setBackgroundColor(viewId, color);
    }

    @Override
    public VitViewHolder setBackgroundRes(int viewId, int backgroundRes) {
        return (VitViewHolder)super.setBackgroundRes(viewId, backgroundRes);
    }

    @Override
    public VitViewHolder setTextColor(int viewId, int textColor) {
        return (VitViewHolder)super.setTextColor(viewId, textColor);
    }

    @Override
    public VitViewHolder setImageDrawable(int viewId, Drawable drawable) {
        return (VitViewHolder)super.setImageDrawable(viewId, drawable);
    }

    @Override
    public VitViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        return (VitViewHolder)super.setImageBitmap(viewId, bitmap);
    }

    @Override
    public VitViewHolder setAlpha(int viewId, float value) {
        return (VitViewHolder)super.setAlpha(viewId, value);
    }

    @Override
    public VitViewHolder setGone(int viewId, boolean visible) {
        return (VitViewHolder)super.setGone(viewId, visible);
    }

    @Override
    public VitViewHolder setVisible(int viewId, boolean visible) {
        return (VitViewHolder)super.setVisible(viewId, visible);
    }

    /**
     *
     * @param context
     * @param viewId
     * @param imagePaths
     * @return
     */
    public VitViewHolder setImageResource(Context context, int viewId, String imagePaths) {
        GlideLoader.loadImage(context, imagePaths, (ImageView)getView(viewId));
        return this;
    }

}
