package com.zk.dagger;

import android.app.Application;

import com.zk.dagger.bean.CPU;
import com.zk.dagger.compoment.ApplicationCompoment;
import com.zk.dagger.compoment.DaggerApplicationCompoment;

import javax.inject.Inject;

/**
 * author: ZK.
 * date:   On 2018/6/4.
 */
public class MApplication extends Application {

    private static ApplicationCompoment mApplicationCompoment;

    @Inject
    CPU mCPU;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationCompoment = DaggerApplicationCompoment.builder().build();
    }


    public static ApplicationCompoment getApplicationCompoment() {
        return mApplicationCompoment;
    }


}
