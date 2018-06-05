package com.zk.dagger.bean;

import android.util.Log;

/**
 * author: ZK.
 * date:   On 2018/6/5.
 */
public class Display {

    public int size;
    public String brand;

    public Display(int size, String brand) {
        this.size = size;
        this.brand = brand;
    }

    public void run() {
        Log.d("======", this.hashCode() + "==" + size + "寸" + brand + "显示器");
    }
}
