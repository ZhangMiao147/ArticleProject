package com.zm.serviceproject;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ServiceConnection serviceConnection;

    private boolean mBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.start_service).setOnClickListener(this);
        findViewById(R.id.stop_service).setOnClickListener(this);
        findViewById(R.id.bind_service).setOnClickListener(this);
        findViewById(R.id.unbind_service).setOnClickListener(this);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "onServiceConnected");
                mBind = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "onServiceDisconnected");
                mBind = false;
            }
        };
    }

    @Override
    public void onClick(View v) {
        Intent serviceIntent = new Intent(this, MyService.class);
        switch (v.getId()) {
            case R.id.start_service:
                startService(serviceIntent);
                break;
            case R.id.stop_service:
                stopService(serviceIntent);
                break;
            case R.id.bind_service:
                bindService(serviceIntent, serviceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                if (mBind) {
                    unbindService(serviceConnection);
                    mBind = false;
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind) {
            unbindService(serviceConnection);
            mBind = false;
        }
    }
}
