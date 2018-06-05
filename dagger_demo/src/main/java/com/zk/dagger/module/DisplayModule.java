package com.zk.dagger.module;

import com.zk.dagger.annotation.QualifilerA;
import com.zk.dagger.annotation.QualifilerB;
import com.zk.dagger.bean.Display;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * author: ZK.
 * date:   On 2018/6/5.
 */


@Module
public class DisplayModule {

    @QualifilerA
    @Singleton
    @Provides
    Display provideDisplayA() {
        return new Display(27, "Dell");
    }

    @QualifilerB
    @Singleton
    @Provides
    Display provideDisplayB() {
        return new Display(22, "Philips");
    }


}
