package com.zk.dagger.bean;

import android.util.Log;

/**
 * author: ZK.
 * date:   On 2018/6/4.
 */
public class RAM {

    private  String brand;

    public RAM(String brand) {
        this.brand = brand;
    }



    public void run() {
        Log.d("======",  this.hashCode() +"==" +  brand + " RAM运转了");
    }
}
