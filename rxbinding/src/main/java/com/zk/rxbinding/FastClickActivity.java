package com.zk.rxbinding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class FastClickActivity extends AppCompatActivity {

    int fastClickCount = 0;
    int notFastClickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_click);

        final TextView btnNoFastClick = findViewById(R.id.btn_not_fast);

        RxView.clicks(btnNoFastClick)
                .throttleLast(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        notFastClickCount++;
                        btnNoFastClick.setText(String.format("（防抖）点击次数：%1$s", notFastClickCount));
                    }
                });
    }

    public void fastClick(View view) {
        fastClickCount++;
        ((TextView) view).setText(String.format("（不防抖）点击次数：%1$s", fastClickCount));

    }



}
