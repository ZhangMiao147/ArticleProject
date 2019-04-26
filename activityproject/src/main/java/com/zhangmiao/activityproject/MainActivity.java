package com.zhangmiao.activityproject;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView tv_message;
    private EditText et_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_message = (TextView) findViewById(R.id.main_activity_message_tv);
        et_input = (EditText) findViewById(R.id.main_activity_input_et);
        findViewById(R.id.goto_main_activity).setOnClickListener(this);
        findViewById(R.id.goto_first_activity).setOnClickListener(this);
        findViewById(R.id.goto_second_activity).setOnClickListener(this);
        findViewById(R.id.show_standard_alert_dialog).setOnClickListener(this);
        findViewById(R.id.show_full_alert_dialog).setOnClickListener(this);
        findViewById(R.id.goto_dialog_activity).setOnClickListener(this);
        Log.d(TAG, "onCreate() ");
//        Log.d(TAG, "onCreate() TextView的文本内容：" + tv_message.getText());
//        Log.d(TAG, "onCreate() EditText的内容为：" + et_input.getText());
        if (savedInstanceState != null) {
            String message = savedInstanceState.getString("message");
            Log.d(TAG, "onCreate message:" + message);
        }
        Intent intent = getIntent();
        String hex = Integer.toHexString(intent.getFlags());
        Log.d(TAG, "intent.getFlags():" + hex);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart()");
//        Log.d(TAG, "onRestart() EditText的内容为：" + et_input.getText());
//        Log.d(TAG, "onRestart() TextView的文本内容：" + tv_message.getText());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
//        Log.d(TAG, "onStart() EditText的内容为：" + et_input.getText());
//        Log.d(TAG, "onStart() TextView的文本内容：" + tv_message.getText());
        Intent intent = getIntent();
        String hex = Integer.toHexString(intent.getFlags());
        Log.d(TAG, "intent.getFlags():" + hex);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
//        Log.d(TAG, "onResume() EditText的内容为：" + et_input.getText());
//        Log.d(TAG, "onResume() TextView的文本内容：" + tv_message.getText());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
//        Log.d(TAG, "onPause EditText的内容为：" + et_input.getText());
//        Log.d(TAG, "onPause TextView的内容为：" + tv_message.getText());
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
                Intent mainIntent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(mainIntent);
                break;
            case R.id.goto_first_activity:
                Intent firstIntent = new Intent(MainActivity.this, FirstActivity.class);
//                ComponentName componentName = new ComponentName(this,FirstActivity.class);
//                Intent firstIntent = new Intent();
//                firstIntent.setComponent(componentName);
//                firstIntent.setClass(this,FirstActivity.class);
//                firstIntent.setClassName(this,"com.zhangmiao.activityproject.FirstActivity");

//                firstIntent.putExtra("from", "MainActivity");
//                firstIntent.setFlags(FLAG_ACTIVITY_NO_HISTORY);
//                firstIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                firstIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                firstIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                firstIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//                firstIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
//                firstIntent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
//                Bundle bundle = new Bundle();
//                bundle.putString("from", "MainActivity");
//                bundle.putInt("count", 1);
//                firstIntent.putExtras(bundle);
                startActivity(firstIntent);
//                startActivityForResult(firstIntent, 10001);
                break;
            case R.id.goto_second_activity:
                Intent secondIntent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(secondIntent);
                break;
            case R.id.show_standard_alert_dialog:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("这是一个标准的AlertDialog");
                tv_message.setText("弹出了标准的AlertDialog");
                Log.d(TAG, "修改TextView的文本为弹出了标准的AlertDialog");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            case R.id.show_full_alert_dialog:
                AlertDialog.Builder fullBuilder = new AlertDialog.Builder(MainActivity.this, R.style.Dialog_Fullscreen);
                fullBuilder.setTitle("这是一个全屏的AlertDialog");
                fullBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog fullAlertDialog = fullBuilder.create();
                fullAlertDialog.show();
                break;
            case R.id.goto_dialog_activity:
                Intent DialogIntent = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(DialogIntent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState()");
        super.onSaveInstanceState(outState);
        outState.putString("message", "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState()");
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            String message = savedInstanceState.getString("message");
            Log.d(TAG, "onRestoreInstanceState message:" + message);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult requestCode:" + requestCode + ",resultCode:" + resultCode);
        if (requestCode == 10001 && resultCode == 20001) {
            if (data != null) {
                String result = data.getStringExtra("result");
            }
        }
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Log.d(TAG, "onUserLeaveHint");
    }
}
