package com.zk.rxjava.operator;

import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * author: ZK.
 * date:   On 2018/5/7.
 */


/**
 * 过滤操作符：  用于将Observable发送的数据进行过滤和选择。让Observable返回我们所需要的数据。
 * <p>
 * 过滤操作符有buffer()，filter()，skip()，take()，skipLast()，takeLast()，throttleFirst()，distainctUntilChange()。
 */
public class FilterOperator {

    public static final String TAG = "==RXJAVA=";


    /**
     * ========================filter() 操作符 ======================================
     * <p>
     * 对被观察者发送的事件做过滤操作。只有符合筛选条件的事件才会被观察者所接收。
     * <p>
     * return true : 继续发送
     * <p>
     * return false ： 不发送
     */
    public static void filter() {

        Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        for (int i = 0; i < 3; i++) {
                            emitter.onNext(i);
                        }
                    }
                })
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        //return true : 继续发送
                        //return false ： 不发送
                        return integer != 2; // 本例子过滤了 等于2的情况
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG + "filter", String.valueOf(integer));
                    }
                });

    }

    /**
     * ========================distinct 操作符 ======================================
     * <p>
     * 简单地说就是去重。发射的数据包含重复的，会将重复的筛选掉。也就是，它只允许还没有被发射过的数据通过，被观察者接收。发射完数据会自动调用onComplete()方法
     * <p>
     * y以下代码：观察者只会接收到 ： 1,2,3,5 四个数值
     */
    public static void distinct() {
        Observable
                .just(1, 2, 3, 2, 3, 5)
                .distinct()
                .subscribe(
                        new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Integer integer) {
                                Log.d(TAG + "distinct", String.valueOf(integer));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                Log.d(TAG + "distinct", "onComplete");
                            }
                        });

    }

    /**
     * ========================distinctUntilChanged 操作符 ======================================
     * <p>
     * 过滤掉连续重复的事件
     * <p>
     * 以下代码：观察者只会接收到 ： 1,2,3,5 四个数值
     */
    public static void distinctUntilChanged() {
        Observable.just(1, 2, 3, 1, 2, 3, 3, 4, 4)
                .distinctUntilChanged()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, String.valueOf(integer));
                    }
                });
    }





    /**
     * ========================skip，skipLast======================================
     *
     *  skip 操作符是把Observable发射的数据过滤点掉前n项。而take操作符只能接收前n项。另外还有skipLast和takeLast则是从后往前进行过滤.接收完会调用onComplete()方法
     *
     */

    /**
     * 以下代码输出： 3,4,5
     */
    public static void skip() {
        Observable
                .just(1, 2, 3, 4, 5, 6, 7)
                .skip(2)  //过滤前2项
                .skipLast(3) //过滤后3项
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG + "skip", "根据顺序过滤" + String.valueOf(integer));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG + "skip", "onComplete");
                    }
                });

        Observable.intervalRange(1, 5, 0, 1, TimeUnit.SECONDS)
                .skip(1, TimeUnit.SECONDS)   //过滤地1s发送的数据
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.d(TAG + "skip", "根据事件过滤" + String.valueOf(aLong));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    /**
     * ====================== take 操作符===========================
     * 只能接收两个事件
     */
    public static void take() {
        Observable
                .just(1, 2, 3, 4, 5, 6, 7)
                .take(2)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG + "skip", String.valueOf(integer));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG + "skip", "onComplete");
                    }
                });
    }

    /**
     * =======================takeLast() 操作符 ======================
     * <p>
     * 只能接收被观察者发送的最后几个事件
     */
    public static void takeLast() {
        Observable
                .just(1, 2, 3, 4, 5, 6, 7)
                .take(2)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG + "skip", String.valueOf(integer));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG + "skip", "onComplete");
                    }
                });
    }


    /**
     * ========================elementAt() 操作符 ======================================
     * <p>
     * 只发射第n项数据.
     * 一个参数和两个参数时：
     * elementAt(第n项)
     * elementAt(第n项，第N项不存在时默认值)
     * <p>
     * n为负数时,报IndexOUtOfBoundExection。为正数但超过发射数据长度不会报异常会使用默认值代替
     */

    public static void elementAt() {

        Observable.range(1, 5)
                .elementAt(6, 10)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG + "elementAt", String.valueOf(integer));
                    }
                });
    }

    /**
     * ==============elementAtOrError()===================================================
     * <p>
         * 在elementAtError()的基础上，当出现越界情况(当获取位置的索引>事件序列的长度)，即抛出异常
     */
    public static void elementAtOrError() {
        Observable.range(1, 5)
                .elementAtOrError(6)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG + "elementAtOrErr", String.valueOf(integer));
                    }
                });
    }


    /**
     * ========================ignoreElements() 操作符 ======================================
     * <p>
     * 不管发射的数据.只希望在它完成时和遇到错误时收到通知
     */
    public static void ignoreElements() {

        Observable
                .range(0, 10)
                .ignoreElements()
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(TAG + "ignoreEles", "完成了");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG + "ignoreEles", "出错了");
                    }
                });
    }


    /**
     * ========================ofType() 操作符 ======================================
     * <p>
     * 通过数据的类型过滤数据，只发送指定类型数据。
     * <p>
     * 以下代码观察者只接收到： 1,2
     */
    public static void ofType() {

        Observable.just("哈哈", 1, "嘻嘻", 2, 3.5)
                .ofType(Integer.class)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG + "ofType", String.valueOf(integer));
                    }
                });
    }


    /**
     * ========================throttleFirst()/throttleLast() 操作符 ======================================
     * <p>
     * throttleFirst() 在某段时间内，只发送该段事件第一次事件
     * <p>
     * throttleLast()  在某段时间内，只发送该段事件最后一次事件
     */

    public static void throttleFirst() {
        Observable.interval(300, TimeUnit.MILLISECONDS) //每个0.3秒发送一个事件
                .throttleFirst(1, TimeUnit.SECONDS) //只接收每秒内发送的第一个数据
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG + "throttleFirst", String.valueOf(aLong));
                    }
                });
    }

    /**
     * ===================sample()=================================
     * <p>
     * 在某段时间内，只发送该段时间内最新(最后)1次事件
     * <p>
     * 与throttleLast类似
     */
    public static void sample() {
        Observable.interval(300, TimeUnit.MILLISECONDS) //每个0.3秒发送一个事件
                .sample(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG + "sample", String.valueOf(aLong));
                    }
                });
    }

    /**
     * ========================firstElement()/lastElement()==========================
     * <p>
     * 选取第一个元素/最后一个元素
     */
    public static void firstElement() {
        Observable.just(1, 2, 3)
                .firstElement()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG + "firstElement", String.valueOf(integer));
                    }
                });
    }

    public static void lastElement() {
        Observable
                .just(1, 2, 3)
                .lastElement()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG + "lastElement", String.valueOf(integer));
                    }
                });
    }


}
