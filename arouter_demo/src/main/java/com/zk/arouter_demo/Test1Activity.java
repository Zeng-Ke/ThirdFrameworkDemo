package com.zk.arouter_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.nio.file.Path;


@Route(path = "/test/activity1")
public class Test1Activity extends AppCompatActivity {


    public static final String ROUTE_PATH = "/test/activity1";

    public static final String RESULT_KEY = "result_key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

        Intent intent = new Intent();
        intent.putExtra(RESULT_KEY, "Test1Activity's call back");
        setResult(RESULT_OK,intent);

    }


}
