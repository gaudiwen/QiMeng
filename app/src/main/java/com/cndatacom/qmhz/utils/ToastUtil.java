package com.cndatacom.qmhz.utils;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.cndatacom.qmhz.application.MyApplication;


public class ToastUtil {
    private static volatile ToastUtil sToastUtil = null;

    private Toast mToast = null;

    /**
     * 获取实例
     *
     * @return
     */
    public static ToastUtil getInstance() {
        if (sToastUtil == null) {
            synchronized (ToastUtil.class) {
                if (sToastUtil == null) {
                    sToastUtil = new ToastUtil();
                }
            }
        }
        return sToastUtil;
    }

    protected Handler handler = new Handler(Looper.getMainLooper());

    public void showToast(final int tips){
        showToast(tips, Toast.LENGTH_SHORT);
    }

    public void showToast(final int tips, final int duration) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(MyApplication.getContext(), tips, duration);
                    mToast.show();
                } else {
                    mToast.setText(tips);
                    mToast.setDuration(duration);
                    mToast.show();
                }
            }
        });
    }

    public  void showNewShort(final String msg){
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
