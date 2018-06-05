package com.zk.dagger.compoment;

import com.zk.dagger.bean.Computer;
import com.zk.dagger.annotation.CPUScope;
import com.zk.dagger.module.CpuModule;
import com.zk.dagger.module.DiskModule;
import com.zk.dagger.module.RamModule;

import dagger.Component;

/**
 * author: ZK.
 * date:   On 2018/6/2.
 */


@CPUScope
@Component(modules = {CpuModule.class, RamModule.class, DiskModule.class}, dependencies = ApplicationCompoment.class)
public interface ComputerComponent {

    void inject(Computer computer);

}
