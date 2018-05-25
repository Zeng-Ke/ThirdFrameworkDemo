package com.zk.rxbinding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class CountDownActivity extends AppCompatActivity {

    private TextView mBtnCountDown;
    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        mBtnCountDown = findViewById(R.id.btn_countdown);
    }


    public void countdown(View view) {
        final int count = 10;
        Observable observable = Observable.intervalRange(1, count, 0, 1, TimeUnit.SECONDS);
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mBtnCountDown.setEnabled(false);
                    }
                })
                .map(new Function() {
                    @Override
                    public Object apply(Object o) throws Exception {
                        if (o instanceof Long) {
                            long n = (long) o;
                            return count - n;
                        }
                        return o;
                    }
                })
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        mDisposable = disposable;
                    }

                    @Override
                    public void onNext(Object o) {
                        mBtnCountDown.setText(o.toString() + "秒");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        mBtnCountDown.setEnabled(true);
                        mBtnCountDown.setText("重新获取");
                    }

                    @Override
                    public void onComplete() {
                        mBtnCountDown.setEnabled(true);
                        mBtnCountDown.setText("重新获取");
                    }
                });


    }

    public void cancle(View view) {
        if (mDisposable != null && mDisposable.isDisposed()) {
            mDisposable.dispose();
            mDisposable = null;
        }
    }

}
