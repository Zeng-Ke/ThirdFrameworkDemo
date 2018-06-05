package com.zk.dagger.module;

import com.zk.dagger.bean.RAM;

import dagger.Module;
import dagger.Provides;

/**
 * author: ZK.
 * date:   On 2018/6/4.
 */

@Module
public class RamModule {


    @Provides
    RAM provideRam() {
        return new RAM("金士顿");
    }



}
