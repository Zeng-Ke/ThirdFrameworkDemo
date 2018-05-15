package com.zk.rxjava.rxjava_retrofit.bean;

import java.util.List;

public class CommonDataBean<T extends  SingleListBean> {
    public String v;
    public List<T> items;
}