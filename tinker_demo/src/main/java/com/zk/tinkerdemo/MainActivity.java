package com.zk.tinkerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.meituan.android.walle.WalleChannelReader;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvChannel = findViewById(R.id.tv_channel);
        tvChannel.setText(WalleChannelReader.getChannel(this, "default"));


        TextView tvTag = findViewById(R.id.tv_tag);
        tvChannel.setText(SampleApplicationLike.channel);


        StatService.start(this);


    }
}
