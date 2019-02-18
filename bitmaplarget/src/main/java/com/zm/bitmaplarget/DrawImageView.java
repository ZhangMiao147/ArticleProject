package com.zm.bitmaplarget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Author: zhangmiao
 * Date: 2018/12/4
 */
public class DrawImageView extends ImageView {

    private static final String TAG = DrawImageView.class.getSimpleName();

    private Bitmap mBitmap;

    private Paint mBitmapPaint;

    public DrawImageView(Context context) {
        super(context);
    }

    public DrawImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            if (mBitmapPaint == null) {
                mBitmapPaint = new Paint(Paint.DITHER_FLAG);
                mBitmapPaint.setAntiAlias(true);
                mBitmapPaint.setDither(true);
                mBitmapPaint.setFilterBitmap(true);
            }
            mBitmap = bitmap;
            Log.d(TAG, "setBitmap mBitmap.getWidth():" + mBitmap.getWidth() + ",mBitmap.getHeight():" + mBitmap.getHeight());
            invalidate();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (mBitmap != null) {
            canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        }
    }
}
