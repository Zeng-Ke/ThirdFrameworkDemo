package com.zk.dagger.module;

import com.zk.dagger.bean.CPU;
import com.zk.dagger.annotation.CPUScope;
import com.zk.dagger.annotation.QualifilerAmd;
import com.zk.dagger.annotation.QualifilerIntel;

import dagger.Module;
import dagger.Provides;

/**
 * author: ZK.
 * date:   On 2018/6/4.
 */

@Module(includes = AdvancedCpuModule.class)
public class CpuModule {


    @QualifilerAmd
    @Provides
    public CPU provideAmdCPU() {
        return new CPU("Amd");
    }


    @QualifilerIntel
    @Provides
    public CPU provideIntelCPU() {
        return new CPU("Intel");
    }


    @CPUScope
    @Provides
    public CPU provideCPU() {
        return new CPU("Intel");

    }
}

