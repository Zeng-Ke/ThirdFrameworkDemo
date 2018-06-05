package com.zk.dagger.module;

import com.zk.dagger.bean.Disk;
import com.zk.dagger.bean.DiskBrand;
import com.zk.dagger.annotation.QualifilerB;

import dagger.Module;
import dagger.Provides;

/**
 * author: ZK.
 * date:   On 2018/6/5.
 */

@Module(includes = DiskBrandModule.class)
public class DiskModule {

    @Provides
    Disk provideDisk(@QualifilerB DiskBrand diskBrand) {
        return new Disk(diskBrand);
    }

}
