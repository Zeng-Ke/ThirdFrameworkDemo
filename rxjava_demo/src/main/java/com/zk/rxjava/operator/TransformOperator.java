package com.zk.rxjava.operator;

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
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * author: ZK.
 * date:   On 2018/5/7.
 */


/**
 * 转换操作符：  变换被观察者(Observable)发送的事件。将Observable发送的数据按照一定的规则做一些变换，然后再将变换的数据发射出去。
 * 变换的操作符有map,flatMap，concatMap,switchMap,buffer,groupBy等等。
 * <p>
 * 应用场景：嵌套回调
 */
public class TransformOperator {

    public static final String TAG = "===RXJAVA==";


    /**
     * ======================map============================
     * <p>
     * map操作符，可以说是的被观察者转换器。 通过指定一个Funcation对象，将被观察者(Observable)转换成新的被观察者(Observable)对象并发射，观察者会收到新的被观察者并处理
     * <p>
     * <p>
     * 本来发射的数据是 数字1，然后观察者接收到的是 “ 这是新的观察数据===： 1”
     * <p>
     * 流程：  被观察者.create(事件发射器).map(转换器).subscribe(观察者)
     */

    public static void map() {
        Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        for (int i = 0; i < 10; i++) {
                            emitter.onNext(i);
                        }
                        emitter.onComplete();
                    }
                })
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return "这是新的观察数据===：" + integer;

                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG + "map", s);
                    }
                });
    }


    /**
     * ======================flatMap============================
     * <p>
     * flatMap操作符， 将Observable每一次发射的事件都转换成一个Observable，也就是说把Observable的发射事件集合转换成Observable集合。
     * 然后观察者Observer最终观察的是Observable集合。但是观察者不能保证接收到这Observable集合发送事件的顺序。
     * <p>
     * 是不是很抽象？ 先来看看这一个流程：  观察者.create(事件发射器).flatMap(转换器).subscribe(观察者)
     * <p>
     * 再来看看例子 ： 下面的代码，一开始Observable通过发射器的onNext发送了0-9这10个事件发送出去，正常来说Observer接收到就是 0 - 9 这10个数据
     * 然而中间经过了flatMap的转换。这 10个事件都分别在Funcation转换器上新的Observable。而最终观察者接收的就是这10个新的Observable的发送事件。
     */

    public static void flatMap() {

        Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        for (int i = 0; i < 10; i++) {
                            emitter.onNext(i);
                        }
                        emitter.onComplete();
                    }
                })
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        List<String> list = new ArrayList<>();
                        list.add(String.valueOf(0));
                        list.add(String.valueOf(1));
                        return Observable.fromIterable(list).delay(100, TimeUnit.MILLISECONDS);
                    }
                })
                .subscribe(
                        new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(String s) {
                                Log.d(TAG + "flatMap", s);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                Log.d(TAG + "flatMap", "complete");
                            }
                        });
    }

    /**
     * ======================concatMap============================
     * <p>
     * 与上面的flatMap作用基本一样，与flatMap唯一不同的是concat能保证Observer接收到Observable集合发送事件的顺序
     */
    public static void concatMap() {

        Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        for (int i = 0; i < 10; i++) {
                            emitter.onNext(i);
                        }
                        emitter.onComplete();
                    }
                })
                .concatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        List<String> list = new ArrayList<>();
                        list.add(String.valueOf(0));
                        list.add(String.valueOf(1));
                        return Observable.fromIterable(list).delay(100, TimeUnit.MILLISECONDS);
                    }
                })
                .subscribe(
                        new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(String s) {
                                Log.d(TAG + "concatMap", s);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                Log.d(TAG + "concatMap", "complete");
                            }
                        });
    }


    /**
     * ========================buffer 操作符 ======================================
     * <p>
     * 把发射数据按照一定间隔分成若干段。按每段的数据转换成新的Observable，这个Observable把一段数据一次性发射出去。
     * 可以简单地理解为把一组数据分成若干小组发射出去，而不是单个单个地发射出去
     */
    public static void buffer() {

        Observable
                .just(1, 2, 3, 4, 5, 6)
                .buffer(2)
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        for (Integer integer : integers) {
                            Log.d(TAG + "buffer", String.valueOf(integer));
                        }
                        Log.d(TAG + "buffer", "============================");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG + "buffer", "onComplete");
                    }
                });
    }


}
