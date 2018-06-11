package com.zk.arouter_demo;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * author: ZK.
 * date:   On 2018/6/8.
 */

/**
 * 自定义拦截器需要实现IInterceptor接口，并且添加@Interceptor的注解，其中priority为拦截器的优先级，值越小，优先级越高；然后实现pocess()和init()方法。
 */
@Interceptor(priority = 5)
public class LoginInterceptor implements IInterceptor {


    @Override
    public void process(Postcard postcard, InterceptorCallback interceptorCallback) {

        int extra = postcard.getExtra();
        if (extra == ArouterExtras.NEED_LOGIN) {
            int a = new Random().nextInt(10);
            if (a > 5) {
                interceptorCallback.onInterrupt(new Throwable("a = " + a + "  ,  需要a > 5  "));
                Log.d("=================", postcard.getPath() + "   onInterrupt");
            } else {
                interceptorCallback.onContinue(postcard);
                Log.d("=================", postcard.getPath() + "   onContinue");
            }
        } else interceptorCallback.onContinue(postcard);


    }

    //应用创建时初始化
    @Override
    public void init(Context context) {
        Log.d("==========MMMMMMM=====", context.getClass().getName());

    }
}
