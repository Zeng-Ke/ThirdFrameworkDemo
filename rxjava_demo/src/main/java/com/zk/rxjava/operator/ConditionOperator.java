package com.zk.rxjava.operator;

/**
 * author: ZK.
 * date:   On 2018/5/8.
 */


import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * 条件操作符 ： 通过设置函数，判断被观察者(Observable) 发送的事件是否符合条件。
 * <p>
 * 包括： contains(),exist(),isEmpty(), amb(),all(),takeWhile(),takeUntil(),skipUntil(),skipWhile(),defaultEmpty(),sequenceEqual()
 */
public class ConditionOperator {

    public static final String TAG = "===RXJAVA==";


    /**
     * ================all() 操作符=================================================
     * <p>
     * 判断发送到数据是否都满足指定的条件
     */
    public static void all() {

        Observable
                .range(1, 5)
                .all(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer < 5;
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.d(TAG + "all", aBoolean ? "发送数据都小于5" : "发送的数据不满足全小于5");
                    }
                });
    }


    /**
     * ===================repeatUntil() 操作符=======================================
     * <p>
     * repeat操作符的升级版。可以动态控制是否继续重复发射事件序列。 return 则停止重复，return 则继续重复发射
     */

    static int count = 0;

    public static void repeatUntil() {
        Observable
                .just(1, 2, 3)
                .repeatUntil(new BooleanSupplier() {
                    @Override
                    public boolean getAsBoolean() throws Exception {
                        count++;
                        if (count >= 2)
                            return true;
                        return false;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG + "repeatUntil", String.valueOf(integer));
                    }
                });
    }


    /**
     * ========================takeUntil 操作符 ======================================
     * <p>
     * 发送complete的结束条件，当然发送结束之前也会包含这个值.  return true 时结束，false继续
     * <p>
     * 以下代码：观察者会接收到 0,1,2,3,4,5
     */
    public static void takeUntil() {

        Observable
                .range(0, 10)
                .takeUntil(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        if (integer.equals(5))
                            return true;
                        return false;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG + "takeUntil", String.valueOf(integer));
                    }
                });
    }

    /**
     * takeUntil 也能传入一个被观察者Observable,当该Obervable开始发送数据时(注意：观察者Observer不会接收事件)，那么原始的Observable则停止发送
     */
    public static void takeUntil2() {

        Observable
                .interval(1, TimeUnit.SECONDS)
                .takeUntil(Observable.timer(5, TimeUnit.SECONDS))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG + "takeUntil2", String.valueOf(aLong));
                    }
                });
    }


    /**
     * ========================takeWhile 操作符 ======================================
     * <p>
     * 不满足这个条件时会发送结束。 reture true 继续发送，return false 停止发送
     * <p>
     * 以下代码：观察者会接收到 0,1,2,3,4,5
     */
    public static void takeWhile() {

        Observable.range(0, 10)
                .takeWhile(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        if (integer < 6)
                            return true;
                        return false;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG + "takeWhile", String.valueOf(integer));
                    }
                });
    }

    /**
     * =====================skipWhile()================================
     * <p>
     * 判断发送的每项数据是否满足指定函数条件。直到该判断条件为false时，才开始发送observable的数据(前面的实际会丢弃)
     * <p>
     * 以下代码：从6开始接收
     */
    public static void skipWhile() {

        Observable
                .interval(1, TimeUnit.SECONDS)
                .skipWhile(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Exception {
                        if (aLong > 5)
                            return false;
                        else return true;
                    }
                })
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG + "skipWhile", String.valueOf(aLong));
                    }
                });
    }


    /**
     * =================sequenceEqual================================
     * <p>
     * 判断两个obervable需要发送的数据是否相等，如果相同则返回true，否则返回false
     */
    public static void sequenceEqual() {

        Observable
                .sequenceEqual(Observable.just(4, 5, 6), Observable.just(4, 5, 6))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.d(TAG + "sequenceEq", "两个Obervable是否相等：" + aBoolean);
                    }
                });
    }


    /**
     * =====================contains()=================================
     * <p>
     * 判断发送的数据是否包含指定数据
     */
    public static void contains() {

        Observable
                .just(1, 2, 3, 4, 5)
                .contains(3)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.d(TAG + "contains", "发送的数据是否包含3：" + (aBoolean ? "是" : "否"));
                    }
                });
    }


    /**
     * ==================isEmpty() =======================================
     * <p>
     * 判断被观察者发送的数据是否为空
     */
    public static void isEmpty() {
        Observable
                .just(1)
                .isEmpty()
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.d(TAG + "isEmpty", "发送的数据是否为空：" + (aBoolean ? "是" : "否"));
                    }
                });
    }


    /**
     * =================amb() 操作符=====================================
     * <p>
     * 当需要发送多个Observable时，只发送  先发送数据的Observerable 的数据 ，其余Observable会被丢弃
     */
    public static void amb() {

        List<ObservableSource<Integer>> list = new ArrayList<>();

        Observable observable1 = Observable.just(1, 2, 3);
        Observable observable2 = Observable.just(4, 5, 6).delay(2, TimeUnit.SECONDS);

        list.add(observable1);
        list.add(observable2);

        Observable
                .amb(list)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        //只能接收到observable1 的发送的数据，而observable2会被丢弃
                        Log.d(TAG + "amb", String.valueOf(integer));
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
     * ==========================defaultEmpty() ===============================
     * <p>
     * 在不发送一个有效事件(next事件)、仅发送了complete事件的前提下，发送一个默认值
     */
    public static void defaultEmpty() {

        Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onComplete();
                    }
                })
                .defaultIfEmpty(6)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG + "defaultIfEmp", String.valueOf(integer));
                    }
                });
    }


}
