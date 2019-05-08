package com.cndatacom.qmhz.network.retrofit;

import android.annotation.SuppressLint;

import com.cndatacom.qmhz.BuildConfig;
import com.cndatacom.qmhz.network.string.StringConverterFactory;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class HttpCall {

    private static String token;
    private static String baseUrl;
    private static String signKey = "";
    private static Class myService;

    public static <T> T createApiService(Class<T> cls, String baseUrl,Interceptor... interceptors){

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(RetrofitFactory.httpLoggingInterceptor()) //日志拦截器，打印请求日志
                .connectTimeout(60, TimeUnit.SECONDS)   // 连接超时时间
                .writeTimeout(120, TimeUnit.SECONDS)    // 写入服务器超时时间
                .readTimeout(120, TimeUnit.SECONDS);     // 读取数据超时时间
     if(interceptors != null){
         for (Interceptor interceptor : interceptors) {
             builder.addInterceptor(interceptor);
         }
     }
        OkHttpClient okHttpClient = builder.build();

        /**
         * 注：addConverterFactory是有先后顺序的，如果有多个ConverterFactory都支持同一种类型，那么就是只有第一个才会被使用，
         * 而GsonConverterFactory是不判断是否支持的，所以这里交换了顺序还会有一个异常抛出，原因是类型不匹配。
         */
        Retrofit retrofit = new Retrofit
                .Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  // RxJava回调适配器
                .addConverterFactory(new StringConverterFactory())      // String转换器
                .addConverterFactory(GsonConverterFactory.create())     // Gson转换器
                .build();
        return retrofit.create(cls);
    }


    public static <T> T createApiService(Class<T> cls, String baseUrl){
      return  createApiService(cls,baseUrl,null);

    }


    public static <T> T getApiService(final Class<T> service, String url, final String sign) {
        Retrofit retrofit = null;
        try {
            if ( !baseUrl.equals(url) || !signKey.equals(sign) ||
                    service.getField("type") != myService.getField("type")) {
                Interceptor signInterceptor = null;
                if (!sign.isEmpty()) {
                    signInterceptor = new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();

                            Request request = original.newBuilder()
                                    .header("sign", sign)
                                    .method(original.method(), original.body())
                                    .build();
                            return chain.proceed(request);
                        }
                    };
                    signKey = sign;
                }
                OkHttpClient okHttpClient;
                if (signInterceptor != null) {
                    okHttpClient = new OkHttpClient.Builder()
                            .retryOnConnectionFailure(true)
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .addInterceptor(signInterceptor)
                            .addInterceptor(RetrofitFactory.httpLoggingInterceptor())
                            .sslSocketFactory(createSSLSocketFactory())
                            .build();
                } else {
                    okHttpClient = new OkHttpClient.Builder()
                            .retryOnConnectionFailure(true)
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .addInterceptor(RetrofitFactory.httpLoggingInterceptor())
                            .sslSocketFactory(createSSLSocketFactory())
                            .build();
                }
                retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .client(okHttpClient)
                        .addConverterFactory(StringConverterFactory.create()) //String 转换
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .validateEagerly(true)
                        .build();
                baseUrl = url;
                myService = service;
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return retrofit.create(service);
    }

    @SuppressLint("TrulyRandom")
    private static SSLSocketFactory createSSLSocketFactory() {

        SSLSocketFactory sSLSocketFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllManager()},
                    new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return sSLSocketFactory;
    }

    private static class TrustAllManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {

        }


        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }


}
