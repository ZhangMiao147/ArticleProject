package com.zm.broadcastreceiverproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    MyBroadcastReceiver myBroadcastReceiver;
    YouBroadcastReceiver youBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("zhang.miao.broadcast");
        registerReceiver(myBroadcastReceiver, intentFilter);

        youBroadcastReceiver = new YouBroadcastReceiver();
        registerReceiver(youBroadcastReceiver, intentFilter);

        Intent intent = new Intent();
        intent.setAction("zhang.miao.broadcast");
        sendBroadcast(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myBroadcastReceiver);
        unregisterReceiver(youBroadcastReceiver);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive context:" + context);
        }
    }
}
