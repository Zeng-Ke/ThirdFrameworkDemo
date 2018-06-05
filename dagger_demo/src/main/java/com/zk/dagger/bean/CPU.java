package com.zk.dagger.bean;

import android.util.Log;

/**
 * author: ZK.
 * date:   On 2018/6/4.
 */
public class CPU {

    private String brand;

    public CPU(String brand) {
        this.brand = brand;
        Log.d("======", "Create CPU");
    }

    public void run() {
        Log.d("======",  this.hashCode() +"==" +  brand + " CPU运行了");
    }
}
