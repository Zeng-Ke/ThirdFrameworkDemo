package com.zk.rxjava.rxjava_retrofit.bean;

import java.util.List;

/**
 * author: ZK.
 * date:   On 2018/5/11.
 */
public class BaseBean<T> {


    /**
     * pageToken : null
     * hasNext : false
     * dataType : tools
     * retcode : 000000
     * data : [{"id":"13610173029","zipCode":"510000","cardType":"移动神州行卡","areaCode":"020","location":"广东 广州市"}]
     * appCode : phone_number_ascription
     */

   public Object pageToken;
   public boolean hasNext;
   public String dataType;
   public String retcode;
   public String appCode;
   public T data;



}
