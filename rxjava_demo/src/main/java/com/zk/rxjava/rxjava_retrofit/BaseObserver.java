package com.zk.rxjava.rxjava_retrofit;

import android.util.Log;

import com.zk.rxjava.rxjava_retrofit.bean.BaseBean;
import com.zk.rxjava.rxjava_retrofit.bean.CommonDataBean;
import com.zk.rxjava.rxjava_retrofit.bean.DoubleListBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * author: ZK.
 * date:   On 2018/5/14.
 */
public class BaseObserver<T> extends DisposableObserver<BaseBean<T>> {

    private MObserver<T> mObserver;

    public BaseObserver(MObserver<T> observer) {
        mObserver = observer;
    }

    @Override
    public void onNext(BaseBean<T> tBaseBean) {
        if (mObserver != null)
            mObserver.onNext(tBaseBean.data);


    }

    @Override
    public void onError(Throwable e) {

        Log.d("=================", e.getMessage());
    }

    @Override
    public void onComplete() {

    }
}
