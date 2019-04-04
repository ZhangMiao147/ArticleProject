package com.zm.playaudiotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_playStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_playStatus = (TextView) findViewById(R.id.play_status_tv);
        findViewById(R.id.play_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://rbebag-zy-test.strongwind.cn/soeasy.mp3";
                tv_playStatus.setText("加载中...");
                AudioPlayManager.getInstance().start(url, new AudioPlayManager.PlayerListener() {
                    @Override
                    public void onPrepared() {
                        tv_playStatus.setText("准备播放...");
                    }

                    @Override
                    public void onCompleted() {
                        tv_playStatus.setText("播放完毕...");
                    }

                    @Override
                    public void onError() {
                        tv_playStatus.setText("准备播放...");
                    }
                });
            }
        });
    }
}
