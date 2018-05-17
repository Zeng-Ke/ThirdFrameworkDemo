package com.zk.tinkerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.baidu.mobstat.StatService;

public class MainActivity extends AppCompatActivity {

    int[] a = new int[]{1, 2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //int b = a[3];

        TextView tvChannel = findViewById(R.id.tv_channel);
        TextView tvTag = findViewById(R.id.tv_tag);
        tvChannel.setText(SampleApplicationLike.channel);



        StatService.start(this);



    }
}
