package com.zk.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zk.rxjava.operator.ConditionOperator;
import com.zk.rxjava.rxjava_retrofit.MObserver;
import com.zk.rxjava.rxjava_retrofit.bean.CommonDataBean;
import com.zk.rxjava.rxjava_retrofit.bean.DoubleListBean;
import com.zk.rxjava.rxjava_retrofit.bean.HttpService;
import com.zk.rxjava.rxjava_retrofit.bean.PhoneInfoBean;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // CreateOperator.subscribe();
        //CreateOperator.just();
        //CreateOperator.fromIterable();
        //CreateOperator.fromArray();
        //CreateOperator.interval();
        // TransformOperator.concatMap();
        // TransformOperator.flatMap();
        //  FilterOperator.filter();
        // FilterOperator.take();
        // FilterOperator.distinct();
        // FilterOperator.buffer();
        //FilterOperator.skip();
        //FilterOperator.merge();
        //MergeOperator.zip();
        //MergeOperator.combineLatest();
        //MergeOperator.reduce();
        //MergeOperator.collect();
        // MergeOperator.startWith();
        //MergeOperator.count();
        //FilterOperator.elementAt();
        //FilterOperator.throttleFirst();

        //FilterOperator.ignoreElements();
        //FilterOperator.takeUntil();
        // FilterOperator.takeWhile();
        //FilterOperator.ofType();
        //ConditionOperator.repeat();
        // ConditionOperator.repeatUntil();
        //ConditionOperator.delay();
        //MergeOperator.concatDelayError();
        //FunctionOperator.dos();
        //FunctionOperator.onErrorReturn();
        //FunctionOperator.onExceptionResumeNext();
        // FunctionOperator.retry();
        //FunctionOperator.retryUntil();
        // FunctionOperator.retryWhen();

        //ConditionOperator.all();
        //ConditionOperator.takeWhile();
        //ConditionOperator.skipWhile();
        //ConditionOperator.takeUntil2();
        //ConditionOperator.sequenceEqual();
        // ConditionOperator.contains();
        //ConditionOperator.isEmpty();
        //ConditionOperator.amb();
        // ConditionOperator.defaultEmpty();

        final TextView textView = findViewById(R.id.tv);

        HttpService httpService = new HttpService();

        httpService.getPhoneNumber(new MObserver<List<PhoneInfoBean>>() {
            @Override
            public void onNext(List<PhoneInfoBean> o) {
                if (o != null && o.size() > 0)
                    textView.setText(o.get(0).location);
            }
        });

      /*  httpService.getAreaData(new MObserver<CommonDataBean<DoubleListBean>>() {
            @Override
            public void onNext(CommonDataBean<DoubleListBean> o) {
                Log.d("==========", o.v);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e("================", e.getMessage());
            }
        });*/

    }
}
