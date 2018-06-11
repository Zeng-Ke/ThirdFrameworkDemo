package com.zk.arouter_demo;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * author: ZK.
 * date:   On 2018/6/8.
 */
public class MApplication extends Application {

    public static final boolean isDebug = BuildConfig.DEBUG;

    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebug) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);

    }
}
