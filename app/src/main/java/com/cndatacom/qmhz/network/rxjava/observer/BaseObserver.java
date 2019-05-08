package com.cndatacom.qmhz.network.rxjava.observer;

import android.content.Context;
import android.net.ParseException;
import android.util.Log;
import android.widget.Toast;

import com.cndatacom.qmhz.network.rxjava.HttpResponseException;
import com.google.gson.JsonParseException;
import org.json.JSONException;
import java.io.Serializable;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;


public abstract class BaseObserver<T> implements Observer<T>,Serializable {
 private Context mContext;

    public BaseObserver(Context context, CompositeDisposable disposable) {
        mContext = context;
        mDisposable = disposable;
    }

    private CompositeDisposable mDisposable;
    public BaseObserver(Context context){
       mContext = context;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if(mDisposable != null){

            mDisposable.add(d);
        }
    }
    public void onTokenFailure() {
        Log.d("测试","onTokenFailure1");
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {
            Toast.makeText(mContext,"网络异常",Toast.LENGTH_SHORT).show();
        } else if (e instanceof UnknownHostException) {
            Toast.makeText(mContext,"网络访问异常",Toast.LENGTH_SHORT).show();
        } else if (e instanceof SocketTimeoutException) {
            Toast.makeText(mContext,"当前网络不给力",Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(mContext,"连接服务器异常",Toast.LENGTH_SHORT).show();
        } else if (e instanceof HttpResponseException) {
            //自定义异常 状态码等
            HttpResponseException responseException = (HttpResponseException) e;
            onFailure(responseException.getError(), responseException.getMessage());

        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            Toast.makeText(mContext,"HTTPS异常",Toast.LENGTH_SHORT).show();
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            Toast.makeText(mContext,"后台数据有误",Toast.LENGTH_SHORT).show();
        } else {
            e.printStackTrace();
        }
    }

    @Override
    public void onComplete() {
    }

    protected abstract void onSuccess(T bean);
    /**
     * 简单提示 服务器返回信息 若需要处理 则重写
     */
    protected void onFailure(int code, String msg) {
        if(code == 3001){
            onTokenFailure();
        }
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
    }
}
