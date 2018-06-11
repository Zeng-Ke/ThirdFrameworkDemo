package com.zk.arouter_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

// 为每一个参数声明一个字段，并使用 @Autowired 标注
// URL中不能传递Parcelable类型数据，通过ARouter api可以传递Parcelable对象

@Route(path = "/test/url")
public class UrlActivity extends AppCompatActivity {

    public static final String ROUTE_PATH = "/test/url";

    @Autowired
    String name;

    @Autowired
    int age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        Log.d("========","UrlActivity oncreate");
        ARouter.getInstance().inject(this);
        ((TextView) (findViewById(R.id.content))).setText("name: " + name + "  age: " + age);
    }
}
