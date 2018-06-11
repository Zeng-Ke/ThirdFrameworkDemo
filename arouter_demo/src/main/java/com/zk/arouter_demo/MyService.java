package com.zk.arouter_demo;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * author: ZK.
 * date:   On 2018/6/11.
 */

@Route(path = "/service/hello")
public class MyService implements IService {

    private Context mContext;

    public static final String ROUTE_PATH = "/service/hello";

    @Override
    public void sayHello(String str) {
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void init(Context context) {
        mContext = context;
    }
}
