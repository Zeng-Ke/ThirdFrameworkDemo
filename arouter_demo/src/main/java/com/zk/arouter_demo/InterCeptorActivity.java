package com.zk.arouter_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.utils.Consts;


@Route(path = "/test/interceptor",extras = ArouterExtras.NEED_LOGIN)
public class InterCeptorActivity extends AppCompatActivity {


    public static final String ROUTE_PATH = "/test/interceptor";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        ((TextView) findViewById(R.id.content)).setText(getClass().getSimpleName());

    }
}
