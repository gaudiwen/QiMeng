package com.cndatacom.qmhz.network.retrofit;

import android.util.Log;

import com.cndatacom.qmhz.BuildConfig;
import com.cndatacom.qmhz.application.MyApplication;
import com.cndatacom.qmhz.network.Interceptor.CacheControlInterceptor;
import com.cndatacom.qmhz.network.api.NeiHanService;
import com.cndatacom.qmhz.utils.NetWorkUtil;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

 /**
  * @date:  2019/04/24
  * @author: GaudiWen
  */
public class RetrofitFactory {

    private static final String TAG = "RetrofitFactory";
    private volatile static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (RetrofitFactory.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(NeiHanService.BASEURL)
                            .client(myokhttpclient())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }

     /**
      * @method
      * @description 描置缓存路径以及缓存大小，其中addInterceptor是添加拦截器
      * @date:
      * @author: GaudiWen(cmt+回车)
      * @param
      * @return
      */
    private static OkHttpClient myokhttpclient() {
        //设置缓存路径和缓存大小 50M
        Cache cache = new Cache(new File(MyApplication.getContext().getCacheDir(), "okhttpCache"), 50 * 1024 * 1024);
        Log.e(TAG, "MyOkHttpClient:缓存目录 "+MyApplication.getContext().getCacheDir());
        // Cookie 持久化
        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(MyApplication.getContext()));

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .cache(cache)
                .connectTimeout(15 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                .writeTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(false)
                // 添加拦截器,可添加多个
                .addInterceptor(httpLoggingInterceptor())
                .addInterceptor(new CacheControlInterceptor());
                // .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)

        return client.build();

    }

    /*** 自定义日志拦截器*/
    private static  Interceptor MY_LOGGING_INTERCEPTOR = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            okhttp3.Response response = chain.proceed(chain.request());
            long t2 = System.nanoTime();
            okhttp3.MediaType mediaType = response.body().contentType();
            Log.e(TAG, "intercept:返回的类型为： " + mediaType);
            String content = response.body().string();
            Log.e(TAG, "-----LoggingInterceptor----- :\nrequest url:" + request.url() + "\ntime:" + (t2 - t1) / 1e6d + "\nbody:" + content + "\n");
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();
        }
    };

    /*** HttpLoggingInterceptor日志拦截器*/
    public static  HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        if (BuildConfig.SWITCH_LOG){
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return httpLoggingInterceptor;
    }

}
