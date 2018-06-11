package com.zk.arouter_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.nio.file.Path;


@Route(path = "/test/activity1")
public class Test1Activity extends AppCompatActivity {



    public static  final  String ROUTE_PATH = "/test/activity1";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

    }
}
