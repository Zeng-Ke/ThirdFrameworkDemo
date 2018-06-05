package com.zk.dagger.module;

import com.zk.dagger.bean.AdvanedCPU;

import dagger.Module;
import dagger.Provides;

/**
 * author: ZK.
 * date:   On 2018/6/4.
 */

@Module
public class AdvancedCpuModule {

    @Provides
    AdvanedCPU provideCpu() {
        return new AdvanedCPU("Indel i9");
    }

}
