package com.zk.dagger.module;

import com.zk.dagger.bean.DiskBrand;
import com.zk.dagger.annotation.QualifilerA;
import com.zk.dagger.annotation.QualifilerB;

import dagger.Module;
import dagger.Provides;

/**
 * author: ZK.
 * date:   On 2018/6/5.
 */
@Module
public class DiskBrandModule {

    @QualifilerA
    @Provides
    DiskBrand provideDiskBrand1() {
        return new DiskBrand("三星");
    }


    @QualifilerB
    @Provides
    DiskBrand provideDiskBrand2() {
        return new DiskBrand("希捷");
    }

}
