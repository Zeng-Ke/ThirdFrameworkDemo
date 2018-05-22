package com.zk.rxjava.operator;

import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * author: ZK.
 * date:   On 2018/5/8.
 */

/**
 * 合并操作符 : 组合多个被观察者(Observable)&合并需要发送的事件。
 * <p>
 * 包含：concatMap(),concat(), merge(),mergeArray(),concateArray(),reduce(),collect(),startWith(),zip(),count()
 */
public class MergeOperator {

    public static final String TAG = "===RXJAVA==";

    /**
     * ========================merge，concat 操作符 ======================================
     * <p>
     * merge操作符是把多个Observable合并成一个进行发射。merge可能会让合并到Observable的数据顺序发生错乱(组合被观察者数量<=4个)(并行无序)
     * mergeArray操作符和merge作用一样，但不同的是组合被观察者数量>4个)(并行无序)
     * <p>
     * concat操作符也是把多个Observable合并成一个进行发射。但concat则保证合并的每个Observable的事件按顺序发射出去。(组合被观察者数量<=4个)(串行有序)
     * concatArray操作符和concat作用一样，但不同的是组合被观察者数量>4个)(串行有序)
     */
    public static void merge() {
        Observable observable1 = Observable.just(1, 2, 3);
        Observable observable2 = Observable.just("哈哈", "嘻嘻", "啊啊");

        Observable
                .merge(observable1, observable2).delay(1, TimeUnit.SECONDS)
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        Log.d(TAG + "merge", o.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG + "merge", "onComplete");
                    }
                });
    }

    /**
     * ========================concatDelayError()/mergeDelayError() 操作符 ======================================
     * <p>
     * 这两个操作符的作用是： 使用concat()和merge()操作符时，若其中一个被观察者发送onError事件，则会马上终止其它被观察者继续发送事件。所以呐，这时使用concatError()/
     * mergeDelayError()事件可以使onError事件推迟到其它被观察者发送事件结束后在再触发
     */
    public static void concatDelayError() {

        Observable
                .concatArrayDelayError(Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1);
                        emitter.onNext(2);
                        emitter.onError(new NullPointerException());
                        emitter.onNext(3);
                        emitter.onNext(4);
                    }
                }), Observable.just(5, 6))


                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG + "cDelayError", String.valueOf(integer));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG + "cDelayError", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG + "cDelayError", "onComplete");
                    }
                });
    }


    /**
     * ========================zip 操作符 ======================================
     * <p>
     * 把多个Observable合并后，并且把这些Observable的数据进行转换再发射出去。转换之后的数据数目由最短数据长度的那个Observable决定。发射完最终会自动调用观察者的onComplete方法()
     * <p>
     * 如以下代码： 数据长度为4的observable1和数据长度为3的observable2进行合并转换后，观察者只接收到3个数据
     */

    public static void zip() {

        Observable observable1 = Observable.just(1, 2, 3, 4);
        Observable observable2 = Observable.just("哈哈", "嘻嘻", "啊啊");


        Observable
                .zip(observable1, observable2, new BiFunction<Integer, String, String>() {

                    @Override
                    public String apply(Integer integer, String s) throws Exception {
                        return s + integer;
                    }
                })
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        Log.d(TAG + "zip", o.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG + "merge", "onComplete");
                    }
                });
    }

    /**
     * ========================combineLatest 操作符 ======================================
     * <p>
     * 当两个Observable 中的任何一个发送了数据，将先发送了数据的Observable的最新（最后）一个数据和另一个Observable发送的每个数据结合，最终基于该结合的结果发送数据
     * <p>
     * 与zip()的区别： zip()是按个数合并，即1对1合并；而combineLatest()是基于时间合并，，即在同一时间点上合并
     */
    public static void combineLatest() {

        Observable
                .combineLatest(Observable.just(1, 2, 3)
                        , Observable.intervalRange(1, 4, 2, 1, TimeUnit.SECONDS)
                        , new BiFunction<Integer, Long, String>() {
                            @Override
                            public String apply(Integer integer, Long aLong) throws Exception {
                                return "合并后的数据为：" + integer + aLong;
                            }
                        })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG + "combineLatest", s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG + "combineLatest", "onComplete");
                    }
                });
    }


    /**
     *
     *  ======================combineLatestDelayError =================================
     *
     *  作用类似于concatDelayError() / mergeDelayError(),用于错误处理
     */


    /**
     * ======================reduce  操作符=================================
     * <p>
     * 把被观察者需要发送的数据按照指定规则聚合成一个数据发送
     * <p>
     * 聚合的规则需要我们编写，内部流程是前两个数据按照我们的规则合并后，再与后面的数据按规则合并，依次类推。这样说有点抽象，看下面的例子。
     */
    public static void reduce() {

        Observable
                .just(1, 2, 3, 4, 5)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        Log.d(TAG + "reduce", "本次合并的过程是：  " + integer + "+" + integer2);
                        return integer + integer2;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG + "reduce", "最终计算的结果是 :  " + integer);
                    }
                });
    }


    /**
     * ========================collect 操作符=================================
     * <p>
     * 作用是把 Observable(被观察者)发送的事件收集到一个数据结构中
     */
    public static void collect() {

        Observable
                .just(1, 2, 3, 4, 5)
                .collect(new Callable<ArrayList<Integer>>() {
                    @Override
                    public ArrayList<Integer> call() throws Exception {
                        return new ArrayList<>();
                    }
                }, new BiConsumer<ArrayList<Integer>, Integer>() {
                    @Override
                    public void accept(ArrayList<Integer> integers, Integer integer) throws Exception {
                        integers.add(integer);
                    }
                })
                .subscribe(new Consumer<ArrayList<Integer>>() {
                    @Override
                    public void accept(ArrayList<Integer> integers) throws Exception {
                        Log.d(TAG + "collect", integers.toString());
                    }
                });
    }


    /**
     * ========================startWith/startWithArray 操作符=================================
     * <p>
     * 在一个被观察者发送时间前，追加发送一些数据/一个新的被观察者
     */
    public static void startWith() {

        Observable.just(7, 8, 9)
                .startWith(6)   //在发送序列去追加单个数据
                .startWithArray(4, 5)  //在发送序列去追加多个数据
                .startWith(Observable.just(1, 2, 3))  //在发送序列去追加单个被观察者
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG + "startWith", String.valueOf(integer));
                    }
                });
    }


    /**
     * ========================count 操作符=================================
     * <p>
     * 统计被观察者发送事件数量
     */
    public static void count() {
        Observable
                .just(1, 2, 3, 4)
                .count()
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG + "count", "发送事件的数量 ： " + aLong);
                    }
                });
    }


}
