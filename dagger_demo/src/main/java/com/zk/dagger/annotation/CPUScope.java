package com.zk.dagger.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * author: ZK.
 * date:   On 2018/6/4.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface CPUScope {
}
