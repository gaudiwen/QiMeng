package com.cndatacom.qmhz.network.Interceptor;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *Time:2019/2/20
 *Author:Gaodi.
 *Description:Token拦截器
 */
public class TokenHeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        /**本地缓存拿Token**/
        //String token ="本地缓存拿Token";
        String token ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1NTcwNjMwMzUsInN1YiI6IntcImJ1c0lkXCI6XCIyOTI0XCIsXCJyYW5kb21cIjpcIjY0NDQ3NVwiLFwiYnVzTmFtZVwiOlwiZGZ0eVwifSIsImlzcyI6ImR1b2ZyaWVuZCIsImF1ZCI6ImR1b2ZlbiIsImV4cCI6MTU4ODY5NDM5OSwibmJmIjoxNTU3MDYzMDM1fQ.KzLlN8E_jypwZUPlGTo3IBkft76US0tY5Npz8Cazkd8";
        //String token = LocalDataManager.getInstance().getToken();
        if (TextUtils.isEmpty(token)) {
            Request originalRequest = chain.request();
            return chain.proceed(originalRequest);
        }else {
            Request originalRequest = chain.request();
            Request updateRequest = originalRequest.newBuilder().header("Authorization", token).build();
            return chain.proceed(updateRequest);
        }
    }

}