package com.zk.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zk.rxjava.operator.ConditionOperator;

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
        ConditionOperator.defaultEmpty();

    }
}
