package com.zk.rxjava.rxjava_retrofit.bean;

import android.util.Log;

import com.zk.rxjava.rxjava_retrofit.BaseObserver;
import com.zk.rxjava.rxjava_retrofit.HttpCreator;
import com.zk.rxjava.rxjava_retrofit.IApi;
import com.zk.rxjava.rxjava_retrofit.MObserver;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * author: ZK.
 * date:   On 2018/5/11.
 */
public class HttpService {


    public void getPhoneNumber(MObserver<List<PhoneInfoBean>> observer) {

        Observable<BaseBean<List<PhoneInfoBean>>> observable = HttpCreator.createApi(IApi.class).getPhoneInfo("13610100000",
                "JQ4iONjs1LBg60Ghgj842cKvjgVE7dDRXfBpxsvWTrgP16hY5RtOaVgqy1Wky7MT");
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<>(observer));

    }

    public void getAreaData(MObserver<CommonDataBean<DoubleListBean>> observer) {

        Observable<BaseBean<CommonDataBean<DoubleListBean>>> observable = HttpCreator.createApi(IApi.class).getAreaData1(2, "0001");
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<>(observer));
    }


}
