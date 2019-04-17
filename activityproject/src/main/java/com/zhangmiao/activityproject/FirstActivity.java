package com.zhangmiao.activityproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = FirstActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        findViewById(R.id.goto_main_activity).setOnClickListener(this);
        findViewById(R.id.goto_first_activity).setOnClickListener(this);
        findViewById(R.id.goto_second_activity).setOnClickListener(this);
        Log.d(TAG, "onCreate()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.goto_main_activity:
                Intent mainIntent = new Intent(FirstActivity.this, MainActivity.class);
                startActivity(mainIntent);
                break;
            case R.id.goto_first_activity:
                Intent firstIntent = new Intent(FirstActivity.this, FirstActivity.class);
                firstIntent.putExtra("from", "FirstActivity");
                startActivity(firstIntent);
                break;
            case R.id.goto_second_activity:
                Intent secondIntent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(secondIntent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        String from = intent.getStringExtra("from");
        Log.d(TAG, "onNewIntent from:" + from);
        super.onNewIntent(intent);
    }
}
