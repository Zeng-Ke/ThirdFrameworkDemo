package com.zk.rxjava.rxjava_retrofit;


import com.zk.rxjava.rxjava_retrofit.bean.BaseBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: ZK.
 * date:   On 2018/5/22.
 */
public class BaseHttpService {

    public <T> void enqueue(Observable<BaseBean<T>> observable, MObserver<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<>(observer));
    }

    public <T> void execute(Observable<BaseBean<T>> observable, MObserver<T> observer) {
        observable.subscribe(new BaseObserver<>(observer));
    }

}
