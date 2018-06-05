package com.zk.dagger.compoment;

import com.zk.dagger.annotation.QualifilerA;
import com.zk.dagger.bean.Display;
import com.zk.dagger.module.DisplayModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * author: ZK.
 * date:   On 2018/6/4.
 */

@Singleton
@Component(modules = {DisplayModule.class})
public interface ApplicationCompoment {

    //void inject(Computer computer);

    @QualifilerA Display displayer();


}
