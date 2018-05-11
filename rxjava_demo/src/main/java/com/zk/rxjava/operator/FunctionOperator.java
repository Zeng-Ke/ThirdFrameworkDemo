package com.zk.rxjava.operator;

/**
 * author: ZK.
 * date:   On 2018/5/9.
 */


import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * 功能操作符： 辅助被观察者(Observable) 发送事件时实现一些功能性需求，如错误处理，线程调度
 */
public class FunctionOperator {

    public static final String TAG = "==RXJAVA=";

    /**
     * ==================subscribe 操作符===========================
     *
     *  连接被观察者和观察者
     */
    public static void subscribe() {

        //创建被观察者
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                emitter.onNext("事件");
            }
        });

        //创建观察者
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG + "subscribe", "开始连接");
            }

            @Override
            public void onNext(Object o) {
                Log.d(TAG + "subscribe", "收到事件");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        //通过subscribe 进行 被观察者(Observable)与观察者(Observer)的连接
        observable.subscribe(observer);

    }


    /**
     * ==================delay 操作符=======================================
     * <p>
     * 延迟发送事件
     * <p>
     * delay有多个重载方法：
     * <p>
     * delay(long delay,TimeUnit unit) :指定延迟时间。 参数一：时间 ； 参数二：时间单位
     * <p>
     * delay(long delay, TimeUnit unit, Scheduler scheduler)  指定延迟时间&线程调度器。参数一：时间 ； 参数二：时间单位；参数三： 线程调度器
     * <p>
     * delay(long delay, TimeUnit unit, boolean delayError)  指定延迟时间&线程调度器。参数一：时间 ； 参数二：时间单位；参数三： 是否错误延迟
     * <p>
     * delay(long delay, TimeUnit unit, Scheduler scheduler, boolean delayError)  指定延迟时间&线程调度器&错误延迟。参数一：时间 ； 参数二：时间单位；
     * 参数三： 线程调度器; 参数四：是否错误延迟(若中间发生错误，是否如常执行，执行完在执行onError())
     */
    public static void delay() {

        Observable
                .just(1, 2)
                .delay(10, TimeUnit.SECONDS)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG + "delay", String.valueOf(integer));
                    }
                });
    }


    /**
     * ========================do 系列操作符 =======================================
     * <p>
     * 在事件发送&接收的整个周期过程中进行操作。
     * <p>
     * 如发送事件前的操作，发送事件后的回调请求
     * <p>
     * do系列操作符包含以下：
     * <p>
     * doOnEach() :当Observable每发送一次事件就会调用一次(包含onNext()，onError()，onComplete())
     * doOnNext(): 执行 onNext()前调用
     * doAfterNext()： 执行onNext()后调用
     * doOnComplete()：执行onComplete()前调用
     * doOnError():执行 onError()前调用
     * doOnTerminate(): 执行终止(无论正常发送完毕/异常终止)
     * doFinally(): 最后执行
     * doOnSubscribe() ：观察者订阅是调用
     * doOnUnScbscribe()： 观察者取消订阅时调用
     */

    public static void dos() {
        Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1);
                        emitter.onNext(2);
                        emitter.onNext(3);
                        emitter.onError(new NullPointerException());
                    }
                })
                .doOnEach(new Consumer<Notification<Integer>>() {
                    @Override
                    public void accept(Notification<Integer> integerNotification) throws Exception {
                        Log.d(TAG + "doOnEach", "doOnEach:  " + String.valueOf(integerNotification.getValue()));
                    }
                })
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG + "doOnNext", "doOnNext:  " + String.valueOf(integer));
                    }
                })
                .doAfterNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG + "doAfterNext", "doAfterNext:  " + String.valueOf(integer));
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(TAG + "doOnComplete", "doOnComplete");
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG + "doOnError", "doOnError");
                    }
                })
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(TAG + "doOnTerminate", "doOnTerminate");
                    }
                })
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(TAG + "doAfterTermi", "doAfterTerminate");
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.d(TAG + "doOnSubscribe", "doOnSubscribe");
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(TAG + "doFinally", "doFinally");
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "收到的数据：  " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });

    }


    /**
     * ====================onErrorReturn() 操作符 ======================
     * <p>
     * 可以捕获错误。遇到错误时，发送一个特殊事件，并且正常终止.注意后面的事件不会再发送
     */
    public static void onErrorReturn() {

        Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1);
                        emitter.onNext(2);
                        emitter.onError(new Throwable("Throwable"));
                        emitter.onNext(3);

                    }
                })
                .onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable) throws Exception {
                        Log.e(TAG, "发生了错误：  " + throwable.getMessage());
                        return 404;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, String.valueOf(integer));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });

    }


    /**
     * ====================onExceptionResumeNext()/onErrorResumeNext() 操作符 ======================
     * <p>
     * 遇到错误时发送一个新的Observable 。并且正常终止.注意原Observable后面的事件不会再发送
     * <p>
     * 如果捕获Exception的话使用onExceptionResumeNext() ，捕获错误的用onErrorResumeNext()
     */
    public static void onExceptionResumeNext() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new NullPointerException("NullPointerException"));
                emitter.onNext(3);
            }
        }).onExceptionResumeNext(new Observable<Integer>() {
            @Override
            protected void subscribeActual(Observer<? super Integer> observer) {
                observer.onNext(4);
                observer.onNext(5);

            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, String.valueOf(integer));
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });
    }


    /**
     * ====================retry() 操作符 ======================
     * <p>
     * 作用是：出现错误时，让被观察者重新发送数据
     * 注：若发送错误，则一直重新发送
     * <p>
     * 有几个重载方法：
     * retry() ： 出现错误时，让被观察者重新发送数据。若错误一直发生，则一直重新发送
     * <p>
     * retry(long time)：与retry不同的书，若错误一直发生，被观察者则一直重新发送数据，但这持续重新发送有次数限制
     * <p>
     * retry(Predicate predicate) ：  出现错误时，根据指定逻辑(可以捕获到发生的错误)决定是否让被观察者重新发送数据
     * <p>
     * retry(new BiPredicate<Integer, Throwable>)：出现错误时，根据指定逻辑(可以捕获重发的次数和发生的错误)决定是否让被观察者重新发送数据
     * <p>
     * retry（long time,Predicate predicate） ： 出现错误时，根据指定逻辑(可以捕获到发生的错误)决定是否让被观察者重新发送数据。并且有持续重发的次数限制
     */
    public static void retry() {

        Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1);
                        emitter.onNext(2);
                        emitter.onError(new Throwable("发生错误了"));
                        emitter.onNext(3);
                    }
                })
                .retry(new BiPredicate<Integer, Throwable>() {
                    @Override
                    public boolean test(Integer integer, Throwable throwable) throws Exception {

                        // interger 为重试次数 ，throwable 为捕获到的异常

                        Log.e(TAG + "retry", throwable.getMessage());
                        Log.e(TAG + "integer", "重试次数： " + integer);

                        //return true ： 重新发送请求(若持续遇到错误，就持续重新发送)
                        //return false ：    不重新发送数据 并且调用观察者的onError()方法结束

                        if (integer > 2)
                            return false;
                        return true;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e(TAG + "retry", String.valueOf(integer));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }


    /**
     * ===================retryUntil() 操作符============================
     * <p>
     * 发送事件遇到错误，指定规则是否重新发送。retry(Predicate predicate)。
     * <p>
     * return true ： 重新发送请求(若持续遇到错误，就持续重新发送)
     * return false ：    不重新发送数据 并且调用观察者的onError()方法结束
     */
    public static void retryUntil() {

        Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1);
                        emitter.onNext(2);
                        emitter.onError(new Throwable("发生错误了"));
                        emitter.onNext(3);
                    }
                })
                .retryUntil(new BooleanSupplier() {
                    @Override
                    public boolean getAsBoolean() throws Exception {

                        //return true ： 重新发送请求(若持续遇到错误，就持续重新发送)
                        // return false ：不重新发送数据 并且调用观察者的onError()方法结束
                        return false;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e(TAG + "retryUntil", String.valueOf(integer));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG + "retryUntil", "onComplete");
                    }
                });
    }


    /**
     * ===================retryWhen() 操作符============================
     * <p>
     * 遇到错误时，将发生的错误传递给一个新的被观察者(Observable)，并决定是否需要重新订阅原始被观察者(Observable) &  发送事件
     */
    public static void retryWhen() {
        Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1);
                        emitter.onNext(2);
                        emitter.onError(new Throwable("发送了错误"));
                        emitter.onNext(3);
                    }
                })
                //遇到Error时会回调
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Throwable throwable) throws Exception {

                                //1、若返回的Observable发送的事件 = Error ，则原始的Observable则不重新发送事件。该异常信息可在观察者的onError中获得
                                //return Observable.error(throwable);

                                //2、若返回的Observable发送的事件= Next事件(和next的内容无关)，则原始的Observable重新发送事件(若持续遇到错误，则持续发送)
                                return Observable.just(5); //仅仅是作为一个触发重新订阅原被观察者的通知，什么数据并不重要，只有不是onComplete/onError事件
                            }
                        });

                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG + "retryWhen", String.valueOf(integer));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG + "retryWhen", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG + "retryWhen", "onComplete");
                    }
                });
    }


    /**
     * ===============repeat() 操作符==============
     * <p>
     * repeat操作符的作用是重复发射 observable的数据序列，可以使无限次也可以是指定次数.不传时为重复无限次
     */
    public static void repeat() {

        Observable
                .just(1, 2, 3)
                .repeat(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG + "repeat", String.valueOf(integer));
                    }
                });
    }


    /**
     *  ===============repeatWhen() 操作符==============
     *
     * 将原始 Observable 停止发送事件的标识（Complete（） / Error（））转换成1个 Object 类型数据传递给1个新被观察者（Observable）
     * ，以此决定是否重新订阅 & 发送原来的 Observable
     */
    public static void repeatWhen() {

        Observable
                .just(1, 2, 4)
                .repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                        return  objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Object o) throws Exception {

                                //若新被观察者（Observable）返回1个Complete()/  Error()事件，则不重新订阅 & 发送原来的 Observable
                                //Observable.empty() = 发送Complete事件，但不会回调观察者的onComplete（）
                                return Observable.empty();

                                // return Observable.error(new Throwable("不再重新订阅事件"));
                                // 返回Error事件 = 回调onError（）事件，并接收传过去的错误信息。

                                // 情况2：若新被观察者（Observable）返回其余事件，则重新订阅 & 发送原来的 Observable
                                // return Observable.just(1);
                                // 仅仅是作为1个触发重新订阅被观察者的通知，发送的是什么数据并不重要，只要不是Complete（） /  Error（）事件
                            }
                        });
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }





}
