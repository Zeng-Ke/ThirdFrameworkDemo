package com.zk.arouter_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;


@Route(path = "/test/service")
public class ServiceActivity extends AppCompatActivity {

    @Autowired(name = MyService.ROUTE_PATH)
    IService mService;

    public static final String ROUTE_PATH = "/test/service";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        ARouter.getInstance().inject(this);
        ((TextView) (findViewById(R.id.content))).setText("ServiceActivity");
        mService.sayHello("ServiceActivity hello");

    }
}
