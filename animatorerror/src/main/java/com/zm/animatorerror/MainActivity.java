package com.zm.animatorerror;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv_image;
    private TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        iv_image = (ImageView) findViewById(R.id.activity_main_image_iv);
        tv_text = (TextView) findViewById(R.id.activity_main_text_tv);
        iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_text.setVisibility(View.VISIBLE);
                AnimatorSet inAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivity.this, R.animator.rotate_in_anim);
                AnimatorSet outAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivity.this, R.animator.rotate_out_anim);
                int distance = 16000;
                float scale = getResources().getDisplayMetrics().density * distance;
                iv_image.setCameraDistance(scale);
                tv_text.setCameraDistance(scale);
                outAnimator.setTarget(iv_image);
                inAnimator.setTarget(tv_text);
                outAnimator.start();
                inAnimator.start();
                outAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        iv_image.setVisibility(View.GONE);
                        iv_image.setAlpha(1.0f);
                        iv_image.setRotationY(0.0f);
                    }
                });
            }
        });
        tv_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_text.setVisibility(View.GONE);
                iv_image.setVisibility(View.VISIBLE);
            }
        });
    }
}
