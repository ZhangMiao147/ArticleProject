package com.zm.bitmaplarget;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

/**
 * 测试在何种情况下会出现Bitmap too large to be uploaded into a texture错误
 * 结论：canvas.drawBitmap,android:src="",android:background=""在设置大尺寸图片时都会有这种问题。
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DrawImageView imageView = (DrawImageView) findViewById(R.id.image);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.larget_image);
        Log.d(TAG, "bmp.getWidth():" + bmp.getWidth() + ",bmp.getHeight():" + bmp.getHeight());
//        imageView.setBitmap(bmp);
    }
}
