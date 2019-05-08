package com.cndatacom.qmhz.network.rxjava.observable;
import com.cndatacom.qmhz.network.rxjava.BaseResponse;
import com.cndatacom.qmhz.network.rxjava.HttpResponseException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


public class ResultTransformer {

    public static <T> ObservableTransformer<BaseResponse<T>, T> transformerYI() {
        return new ObservableTransformer<BaseResponse<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseResponse<T>> upstream) {
                if(upstream != null){
                    return upstream
                            .flatMap(ResultTransformer.<T>flatMap(1001))
                            .compose(SchedulerTransformer.<T>transformer());
                }else {
                    return null;
                }

            }
        };
    }

    /**
     * 正常格式流程
     * 已经切换线程
     */
    public static <T> ObservableTransformer<BaseResponse<T>, T> transformer() {
        return new ObservableTransformer<BaseResponse<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseResponse<T>> upstream) {
                return upstream
                        .flatMap(ResultTransformer.<T>flatMap())
                        .compose(SchedulerTransformer.<T>transformer());
            }
        };
    }

    /**
     * 正常格式流程
     * 已经切换线程
     */
    public static <T> ObservableTransformer<BaseResponse<T>, T> transformer(final int successCode) {
        return new ObservableTransformer<BaseResponse<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseResponse<T>> upstream) {
                return upstream
                        .flatMap(ResultTransformer.<T>flatMap(successCode))
                        .compose(SchedulerTransformer.<T>transformer());
            }
        };
    }


    private static <T> Function<BaseResponse<T>, ObservableSource<T>> flatMap(final int successCode) {
        return new Function<BaseResponse<T>, ObservableSource<T>>() {
            @Override
            public ObservableSource<T> apply(@NonNull final BaseResponse<T> tBaseResponse) throws Exception {
                return new Observable<T>() {
                    @Override
                    protected void subscribeActual(Observer<? super T> observer) {
                        if (tBaseResponse.isSuccess(successCode)) {
                            observer.onNext(tBaseResponse.getData());
                            observer.onComplete();
                        } else {
                            observer.onError(new HttpResponseException(tBaseResponse.getMsg(), tBaseResponse.getCode()));
                        }
                    }
                };
            }
        };
    }

    private static <T> Function<BaseResponse<T>, ObservableSource<T>> flatMap() {
        return new Function<BaseResponse<T>, ObservableSource<T>>() {
            @Override
            public ObservableSource<T> apply(@NonNull final BaseResponse<T> tBaseResponse) throws Exception {
                return new Observable<T>() {
                    @Override
                    protected void subscribeActual(Observer<? super T> observer) {
                        if (tBaseResponse.isSuccess()) {
                            observer.onNext(tBaseResponse.getData());
                            observer.onComplete();
                        } else {
                            observer.onError(new HttpResponseException(tBaseResponse.getMsg(), tBaseResponse.getCode()));
                            observer.onComplete();
                        }
                    }
                };
            }
        };
    }

    /**
     * 无data BaseResponse  也会预处理  处理后返回BaseResponse
     *
     * @param <
     * @return
     */
    public static ObservableTransformer<BaseResponse, BaseResponse> transformerNoData() {
        return new ObservableTransformer<BaseResponse, BaseResponse>() {

            @Override
            public ObservableSource<BaseResponse> apply(@NonNull final Observable<BaseResponse> upstream) {
                return upstream
                        .flatMap(ResultTransformer.<BaseResponse>mapNoDta())
                        .compose(SchedulerTransformer.<BaseResponse>transformer());
            }
        };
    }

    /**
     * 无data BaseResponse  也会预处理  处理后返回BaseResponse
     *
     * @param <
     * @return
     */
    public static ObservableTransformer<BaseResponse, BaseResponse> transformerNoData(final int successCode) {
        return new ObservableTransformer<BaseResponse, BaseResponse>() {

            @Override
            public ObservableSource<BaseResponse> apply(@NonNull final Observable<BaseResponse> upstream) {
                return upstream
                        .flatMap(ResultTransformer.<BaseResponse>mapNoDta(successCode))
                        .compose(SchedulerTransformer.<BaseResponse>transformer());
            }
        };
    }

    private static Function<BaseResponse, ObservableSource<BaseResponse>> mapNoDta() {
        return new Function<BaseResponse, ObservableSource<BaseResponse>>() {
            @Override
            public ObservableSource<BaseResponse> apply(@NonNull final BaseResponse baseResponse) throws Exception {
                return new Observable<BaseResponse>() {
                    @Override
                    protected void subscribeActual(Observer<? super BaseResponse> observer) {
                        if (baseResponse.isSuccess()) {
                            observer.onNext(baseResponse);
                            observer.onComplete();
                        } else {
                            observer.onError(new HttpResponseException(baseResponse.getMsg(), baseResponse.getCode()));
                        }
                    }
                };
            }
        };
    }

    private static Function<BaseResponse, ObservableSource<BaseResponse>> mapNoDta(final int successCode) {
        return new Function<BaseResponse, ObservableSource<BaseResponse>>() {
            @Override
            public ObservableSource<BaseResponse> apply(@NonNull final BaseResponse baseResponse) throws Exception {
                return new Observable<BaseResponse>() {
                    @Override
                    protected void subscribeActual(Observer<? super BaseResponse> observer) {
                        if (baseResponse.isSuccess(successCode)) {
                            observer.onNext(baseResponse);
                            observer.onComplete();
                        } else {
                            observer.onError(new HttpResponseException(baseResponse.getMsg(), baseResponse.getCode()));
                        }
                    }
                };
            }
        };
    }
}
