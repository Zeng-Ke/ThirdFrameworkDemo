package com.zk.arouter_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;


@Route(path = "/test/activity2")
public class Test2Activity extends AppCompatActivity {

    public static final String ROUTE_PATH = "arouter://m.tgnet.com/test/activity2";
    public static final String KEY_NAME = "key_name";
    public static final String KEY_NUMBER = "key_number";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        Intent intent = getIntent();
        String name = intent.getStringExtra(KEY_NAME);
        int number = intent.getIntExtra(KEY_NUMBER, 0);
        ((TextView) (findViewById(R.id.content))).setText("name: " + name + "number: " + number);



    }
}
