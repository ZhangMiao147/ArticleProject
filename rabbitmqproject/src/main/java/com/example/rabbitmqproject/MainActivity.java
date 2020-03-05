package com.example.rabbitmqproject;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * 参考文章地址：https://blog.csdn.net/qq_31150503/article/details/86694030
 */
public class MainActivity extends AppCompatActivity {


    private TextView tv_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_message = (TextView) findViewById(R.id.message_tv);
    }

}
