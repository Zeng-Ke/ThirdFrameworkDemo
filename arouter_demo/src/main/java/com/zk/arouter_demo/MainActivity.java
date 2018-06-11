package com.zk.arouter_demo;

import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;

import java.net.URL;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("========", "MainActivity oncreate");
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sample_launch:
                ARouter.getInstance().build(Test1Activity.ROUTE_PATH).navigation();
                break;
            case R.id.argument_launch:
                ARouter.getInstance().build(Uri.parse(Test2Activity.ROUTE_PATH))
                        .withString(Test2Activity.KEY_NAME, "张三")
                        .withInt(Test2Activity.KEY_NUMBER, 20)
                        .navigation();
                break;
            case R.id.webview_launch:
                ARouter.getInstance().build(WebViewActivity.ROUTE_PATH).navigation();
                break;
            case R.id.iinterceptor_launch:
                ARouter.getInstance().build(InterCeptorActivity.ROUTE_PATH).navigation(this, new NavCallback() {
                    @Override
                    public void onArrival(Postcard postcard) {
                        Log.d("=======", "到达了,当前线程为： " + Thread.currentThread().getName());
                        Toast.makeText(MainActivity.this, "到达了", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        Log.d("=======", "被拦截了,当前线程为： " + Thread.currentThread().getName());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "被拦截了", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        Log.d("=======", "被丢弃了,当前线程为： " + Thread.currentThread().getName());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "被丢弃了", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                    @Override
                    public void onFound(Postcard postcard) {
                        Log.d("=======", "发现了,当前线程为： " + Thread.currentThread().getName());
                    }
                });
                break;
            case R.id.oldanim_launch:
                ARouter.getInstance()
                        .build(Test1Activity.ROUTE_PATH)
                        .withTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom)
                        .navigation(this);
                break;
            case R.id.anim_launch:
                if (Build.VERSION.SDK_INT >= 16) {
                    ActivityOptionsCompat compat = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight()
                            / 2, 0, 0);
                    ARouter.getInstance()
                            .build(Test1Activity.ROUTE_PATH)
                            .withOptionsCompat(compat)
                            .navigation();
                } else {
                    Toast.makeText(this, "API < 16,不支持新版本动画", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.service_activity:
                ARouter.getInstance().build(ServiceActivity.ROUTE_PATH).navigation();
                break;


        }
    }

}
