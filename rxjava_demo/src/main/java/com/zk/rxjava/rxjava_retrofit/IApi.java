package com.zk.rxjava.rxjava_retrofit;


import com.zk.rxjava.rxjava_retrofit.bean.BaseBean;
import com.zk.rxjava.rxjava_retrofit.bean.CommonDataBean;
import com.zk.rxjava.rxjava_retrofit.bean.DoubleListBean;
import com.zk.rxjava.rxjava_retrofit.bean.PhoneInfoBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * author: ZK.
 * date:   On 2018/5/11.
 */
public interface IApi {

    @GET("/phone_number_ascription")
    Observable<BaseBean<List<PhoneInfoBean>>> getPhoneInfo(
            @Query("phoneNumber") String phoneNumber,
            @Query("apikey") String apikey
    );


    @GET("/CommonData/Area")
    Observable<BaseBean<CommonDataBean<DoubleListBean>>> getAreaData1(
            @Query("deep") long deep,
            @Query("parent") String parent
    );

}
