package com.zk.dagger.bean;

import android.util.Log;

/**
 * author: ZK.
 * date:   On 2018/6/5.
 */
public class Disk {


    private DiskBrand mDiskBrand;

    public Disk(DiskBrand diskBrand) {
        mDiskBrand = diskBrand;
    }

    public void run() {
        Log.d("======", this.hashCode() + "==" + mDiskBrand.brand + " 硬盘运转了");
    }
}
