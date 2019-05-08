package com.cndatacom.qmhz.network.rxjava.observable;


import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import retrofit2.HttpException;

/**
 * 每一次使用都要重新生成
 */
public class RetryWhenTransformer {
    private Integer retryCount;
    private int delayTimeMills;
    private int unitDelayTime = 500;
    private int retryCountConstant;

    public RetryWhenTransformer(int retryCount) {
        this.retryCount = retryCount;
        this.retryCountConstant = retryCount;
    }

    public RetryWhenTransformer(int retryCount, int unitDelayTime) {
        this.retryCount = retryCount;
        this.unitDelayTime = unitDelayTime;
    }

    public Function<Observable<Throwable>, ObservableSource<?>> transformer() {
        return new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable e) throws Exception {
                        synchronized (retryCount) {
                            if (e instanceof HttpException || e instanceof UnknownHostException || e instanceof SocketTimeoutException && retryCount >= 1) {//网络不好
                                retryCount--;
                                delayTimeMills += (retryCountConstant - retryCount) * unitDelayTime;
                                return Observable.just("").delay(delayTimeMills, TimeUnit.MILLISECONDS);

                            }
                        }
                        return Observable.error(e);
                    }
                });
            }
        };
    }

}
