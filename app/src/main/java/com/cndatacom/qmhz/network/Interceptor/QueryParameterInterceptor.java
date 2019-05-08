package com.cndatacom.qmhz.network.Interceptor;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 描述: 拦截公共参数
 * 邮箱：275634247@qq.com
 * Created by GaudiWen on 2019/4/24 15:06.
 */
public class QueryParameterInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();
        Request request;
        HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                .addQueryParameter("uid", "864678036850608")
                .addQueryParameter("devid", "864678036850608")
                .addQueryParameter("proid", "ifengnews")
                .addQueryParameter("vt", "5")
                .addQueryParameter("publishid", "6103")
                .addQueryParameter("screen", "1080x1920")
                .addQueryParameter("df", "androidphone")
                .addQueryParameter("os", "android_27")
                .addQueryParameter("nw", "wifi")
                .build();
        request = originalRequest.newBuilder().url(modifiedUrl).build();
        return chain.proceed(request);
    }
}
