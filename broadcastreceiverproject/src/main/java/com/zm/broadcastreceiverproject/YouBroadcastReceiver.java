package com.zm.broadcastreceiverproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Author: zhangmiao
 * Date: 2019/5/10
 */
public class YouBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = YouBroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive context:" + context);
    }
}
