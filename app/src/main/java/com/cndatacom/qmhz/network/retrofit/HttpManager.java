package com.cndatacom.qmhz.network.retrofit;
import com.cndatacom.qmhz.network.Interceptor.TokenHeaderInterceptor;
import com.cndatacom.qmhz.network.api.ApiService;

/**
 * Created by GaudiWen
 * on 2019-04-24.
 */

public class HttpManager {

    private ApiService mApiService;
    private static HttpManager mHttpManager;

    private HttpManager() {

        String baseUrl = "https://shop.duofriend.com/";
        mApiService = HttpCall.createApiService(ApiService.class,baseUrl,new TokenHeaderInterceptor());
    }

    public static HttpManager getInstance() {
        if (mHttpManager == null) {
            synchronized (HttpManager.class) {
                if (mHttpManager == null) {
                    mHttpManager = new HttpManager();
                }
            }
        }
        return mHttpManager;
    }
    public static ApiService getService(){
        return ApiHolder.instance.mApiService;
    }

    private static class ApiHolder{
        private final static HttpManager instance = new  HttpManager();
    }


}
