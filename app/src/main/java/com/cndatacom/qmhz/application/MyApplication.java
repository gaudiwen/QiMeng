package com.cndatacom.qmhz.application;

import android.app.Application;
import android.content.Context;

import com.cndatacom.qmhz.BuildConfig;
import com.cndatacom.qmhz.utils.Utils;
import com.orhanobut.hawk.Hawk;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

/**
 * 描述: 描述一下类的作用
 * 邮箱：275634247@qq.com
 * Created by GaudiWen on 2019/4/22 10:18.
 */
public class MyApplication extends Application {

    private static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext  =getApplicationContext();
        Utils.init(applicationContext);
        Hawk.init(this).build();

        Fragmentation.builder()
                // 设置 栈视图 模式为 （默认）悬浮球模式   SHAKE: 摇一摇唤出  NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                    }
                })
                .install();
    }

    public static Context getContext() {
        return applicationContext;
    }
}
