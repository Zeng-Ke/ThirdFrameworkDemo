package com.zk.dagger.bean;

import com.zk.dagger.MApplication;
import com.zk.dagger.annotation.QualifilerA;
import com.zk.dagger.annotation.QualifilerAmd;
import com.zk.dagger.annotation.QualifilerIntel;
import com.zk.dagger.compoment.DaggerComputerComponent;

import javax.inject.Inject;

/**
 * author: ZK.
 * date:   On 2018/6/2.
 */
public class Computer {

    @QualifilerIntel
    @Inject
    CPU mCPUIntel1;


    @QualifilerAmd
    @Inject
    CPU mCPUAmd;

    @Inject
    CPU cpu1;

    @Inject
    CPU cpu2;

    @Inject
    RAM mRAM;

    @Inject
    AdvanedCPU mAdvanedCPU;


    @Inject
    Disk mDisk;

    @QualifilerA
    @Inject
    Display mDisplayer;

    /*@QualifilerB
    @Inject
    Display mDisplayer1;
*/




    public Computer() {
        DaggerComputerComponent
                .builder()
                .applicationCompoment(MApplication.getApplicationCompoment())
                .build()
                .inject(this);
        //MApplication.getApplicationCompoment().inject(this);


    }

    public void run() {
        cpu1.run();
        // cpu2.run();
        mRAM.run();
        mAdvanedCPU.run();
        mDisk.run();
        mDisplayer.run();

    }

}
