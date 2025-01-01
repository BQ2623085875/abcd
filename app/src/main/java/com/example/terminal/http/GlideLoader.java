package com.example.terminal.http;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.terminal.R;
import com.example.terminal.global.MainApplication;
import com.example.terminal.util.CommonUtils;

import java.io.File;
import java.security.MessageDigest;

/**
 * @author : YanKing
 * @date : 2019/3/22  16:17
 * @Description :
 */
public class GlideLoader {

    public static final int BORN_CODE = 0x00;//圆型图
    public static final int CIRCLE_CODE = 0x01;//圆型图
    public static final int ROUND_CODE = 0x02; //圆角图

    /**
     * 加载正常图片
     *
     * @param urlPath
     * @param mImageView
     */
    public static void loader(String urlPath, ImageView mImageView) {
        loader(urlPath, mImageView, R.mipmap.image_defualt, BORN_CODE);
    }

    /**
     * 加载缩略图片
     *
     * @param urlPath
     * @param mImageView
     */
    public static void ThumbnailLoader(String urlPath, ImageView mImageView) {
        if (TextUtils.isEmpty(urlPath)) {
            return;
        }

        Bitmap thumbnail = CommonUtils.createThumbnail(urlPath, 80, 80);

        Glide.with(mImageView.getContext())
                .load(thumbnail)
                .apply(getRequestOptions(R.mipmap.image_defualt, BORN_CODE))
                .into(mImageView);
    }


    public static void loader(String urlPath, ImageView mImageView, int resource) {
        loader(urlPath, mImageView, resource, BORN_CODE);
    }

    /**
     * 加载资源文件
     *
     * @param resPath
     * @param mImageView
     */
    public static void loader(int resPath, ImageView mImageView) {
        RequestOptions options = new RequestOptions().centerCrop();
        Glide.with(MainApplication.getContext())
                .load(resPath)
                .apply(options)
                .into(mImageView);
    }

    /**
     * 加载圆形图片
     *
     * @param urlPath
     * @param mImageView
     */
    public static void loaderCircle(String urlPath, ImageView mImageView, int resource) {
        loader(urlPath, mImageView, resource, CIRCLE_CODE);
    }

    /**
     * 加载圆形图片
     *
     * @param urlPath
     * @param mImageView
     */
    public static void loaderCircle(String urlPath, ImageView mImageView) {
        loader(urlPath, mImageView, R.mipmap.image_defualt, CIRCLE_CODE);
    }

    /**
     * 本地资源
     *
     * @param drawable
     * @param mImageView
     */
    public static void loaderCircle(Drawable drawable, ImageView mImageView) {
        loader(drawable, mImageView, R.mipmap.image_defualt, CIRCLE_CODE);
    }

    /**
     * 文件
     *
     * @param file
     * @param mImageView
     */
    public static void loaderCircle(File file, ImageView mImageView) {
        loader(file, mImageView, R.mipmap.image_defualt, CIRCLE_CODE);
    }

    /**
     * 文件
     *
     * @param file
     * @param mImageView
     */
    public static void loader(File file, ImageView mImageView) {
        loader(file, mImageView, R.mipmap.image_defualt, BORN_CODE);
    }

    /**
     * 加载圆形边角图片
     *
     * @param urlPath
     * @param mImageView
     * @param resource
     */
    public static void loaderRound(String urlPath, ImageView mImageView, int resource) {
        loader(urlPath, mImageView, resource, ROUND_CODE);
    }

    /**
     * 联网获取
     *
     * @param urlPath
     * @param mImageView
     */
    public static void loaderRound(String urlPath, ImageView mImageView) {
        loader(urlPath, mImageView, R.mipmap.image_defualt, ROUND_CODE);
    }

    /**
     * 文件
     *
     * @param file
     * @param mImageView
     */
    public static void loaderRound(File file, ImageView mImageView) {
        loader(file, mImageView, R.mipmap.image_defualt, ROUND_CODE);
    }

    /**
     * 本地资源
     *
     * @param drawable
     * @param mImageView
     */
    public static void loaderRound(Drawable drawable, ImageView mImageView) {
        loader(drawable, mImageView, R.mipmap.image_defualt, ROUND_CODE);
    }


    /**
     * 设置背景
     *
     * @param urlPath
     * @param mImageView
     */
    public static void loaderBackground(String urlPath, final ImageView mImageView) {
        Glide.with(mImageView.getContext())
                .load(urlPath)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        mImageView.setBackground(resource);
                    }
                });
    }

    /**
     * 加载网络图片
     *
     * @param urlPath
     * @param mImageView
     * @param resource
     * @param imageType
     */
    private static void loader(String urlPath, ImageView mImageView, int resource, int imageType) {
        if (TextUtils.isEmpty(urlPath))
            return;

        Glide.with(mImageView.getContext())
                .load(urlPath)
                .apply(getRequestOptions(resource, imageType))
                .into(mImageView);
    }

    /**
     * 加载本地资源文件
     *
     * @param drawable
     * @param mImageView
     * @param resource
     * @param imageType
     */
    private static void loader(Drawable drawable, ImageView mImageView, int resource, int imageType) {
        Glide.with(mImageView.getContext())
                .load(drawable)
                .apply(getRequestOptions(resource, imageType))
                .into(mImageView);
    }

    /**
     * 加载文件图片
     *
     * @param file
     * @param mImageView
     * @param resource
     * @param imageType
     */
    private static void loader(File file, ImageView mImageView, int resource, int imageType) {

        Glide.with(mImageView.getContext())
                .load(file)
                .apply(getRequestOptions(resource, imageType))
                .into(mImageView);
    }

    private static RequestOptions getRequestOptions(int resource, int imageType) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.seize_seat)
                .skipMemoryCache(true)
                .dontAnimate()
                .error(resource);
        if (imageType == ROUND_CODE || imageType == CIRCLE_CODE)
            options.transform(new GlideRoundTransform(imageType));
        return options;
    }


    static class GlideRoundTransform extends BitmapTransformation {

        private float radius = Resources.getSystem().getDisplayMetrics().density * 8;
        private int type;

        public GlideRoundTransform(int type) {
            this.type = type;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {

            return initCrop(pool, toTransform);
        }

        private Bitmap initCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;
            Bitmap result = null;

            if (type == ROUND_CODE) {
                result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);

                if (result == null) {
                    result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
                }

                Canvas canvas = new Canvas(result);
                Paint paint = new Paint();
                paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
                paint.setAntiAlias(true);
                RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
                canvas.drawRoundRect(rectF, radius, radius, paint);
            } else if (type == CIRCLE_CODE) {
                int size = Math.min(source.getWidth(), source.getHeight());
                int x = (source.getWidth() - size) / 2;
                int y = (source.getHeight() - size) / 2;

                result = pool.get(size, size, Bitmap.Config.ARGB_8888);

                // TODO this could be acquired from the pool too
                Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

                if (result == null) {
                    result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
                }

                Canvas canvas = new Canvas(result);
                Paint paint = new Paint();
                paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
                paint.setAntiAlias(true);
                float r = size / 2f;
                canvas.drawCircle(r, r, r, paint);
            }

            return result;
        }

        @Override
        public void updateDiskCacheKey(MessageDigest messageDigest) {
        }
    }


}


