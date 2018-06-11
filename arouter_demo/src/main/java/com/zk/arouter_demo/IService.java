package com.zk.arouter_demo;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * author: ZK.
 * date:   On 2018/6/11.
 */
public interface IService extends IProvider {

    void sayHello(String str);


}
