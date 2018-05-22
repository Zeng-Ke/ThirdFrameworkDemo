package com.zk.rxjava.operator;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * author: ZK.
 * date:   On 2018/5/7.
 */


/**
 * 创建操作符： 创建被观察者（Observable）对象&发送事件
 * <p>
 * 包括 ： create(), just()，fromArray(),fromIterable(),timer(),interval(),intervalRange(),range(),rangeLong(),nerver(),empty(),defer()等。
 */
public class CreateOperator {

    public static final String TAG = "===RXJAVA==";


    public static void create() {

        /**
         *
         *  ==================基本用法========================================================
         *
         *  Observable 被观察者
         *
         *  ObservableOnSubscribe  观察者与被观察者的桥接(事件发射器)
         *
         *  ObServer 观察者
         *
         *   被观察者  -->  观察者与被观察者的桥接  --> 观察者
         *
         *   被观察者.create(观察者与观察者的桥接).subscribe(观察者)
         *
         */

        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        for (int i = 0; i < 10; i++) {
                            emitter.onNext(String.valueOf(i));
                        }
                        emitter.onComplete();
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "complete");
                    }
                });
    }


    /**
     * ====================just 操作符 ====================
     * <p>
     * 此操作符的作用是将传入的数据依次发送出去.最多可以传10个参数
     * <p>
     * 以下代码会依次把 1-10的字符串发送出去。执行10此观察者的onNext方法，最后默认执行onComplete方法
     */
    public static void just() {

        Observable
                .just("1", "2", "3", "4", "5",
                        "6", "7", "8", "9", "10")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG + "just", s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG + "just", "complete");
                    }
                });
    }


    /**
     * ====================fromIterable 操作符 ====================
     * <p>
     * 此操作符的作用是将传入的数组集合按脚标依次发送出去
     * <p>
     * 以下代码会依次把 0-9的字符串发送出去。执行10此观察者的onNext方法，最后默认执行onComplete方法
     */

    public static void fromIterable() {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }

        Observable
                .fromIterable(list)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG + "fromIterable", s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG + "fromIterable", "complete");
                    }
                });

    }

    /**
     * ==========================timer操作符 ==============================
     * <p>
     * 延迟指定时间发送一个0数值(Long类型)
     * <p>
     * timer操作符主要运行在一个新线程中，也可以自定义线程调度器(第三个参数)
     */
    public static void timer() {

        Observable
                .timer(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG + "timer", String.valueOf(aLong));
                    }
                });

    }


    /**
     * ====================fromArray 操作符============================
     * <p>
     * 对一个数组集合进行观察，把数组一次性发给观察者，只会执行一次观察者的onNext，最后默认执行onComplete方法
     */
    public static void fromArray() {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }
        Observable
                .fromArray(list)
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> strings) {
                        Log.d(TAG + "fromArray", strings.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG + "fromArray", "complete");
                    }
                });
    }

    /**
     * ====================interval  定时器====================
     * <p>
     * 这个相当于定时器，用它可以取代CountDownTimer。它会按照设定的间隔时间，每次发送一个事件，发送的事件序列：默认从0开始，无限递增的整数序列
     * <p>
     * 以下代码输出：   0 ----(5秒后)-----1-----(5秒后)------2---------(5秒后)--------3-------(5秒后)-----.......
     */
    public static void interval() {

        Observable
                .interval(5, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG + "interval", String.valueOf(aLong));//从0开始输出
                    }
                });
    }

    /**
     * intervalRange  操作符
     * <p>
     * 作用和interval相同，但可以指定发送数据的数量
     */
    public static void intervalRange() {


        /**
         *  参数1： 起始发送值
         *  参数2：发送数量
         *  参数3：首次发送延迟事件
         *  参数4：每次发送事件间隔
         *  参数5：时间单位
         *
         */
        Observable
                .intervalRange(2, 10, 3, 1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG + "intervalRan", String.valueOf(aLong));//从2开始输出
                    }
                });
    }


    /**
     * range  操作符
     * <p>
     * 作用发送指定范围的序列，可指定范围.作用类似intervalRange，但不同的是range是无延迟发送
     */
    public static void range() {

        Observable
                .range(2, 6)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG + "range", String.valueOf(integer));//从2开始输出
                    }
                });
    }


}
