package com.cndatacom.qmhz.listener;

public interface HttpCallbackListener {
    void onFinish(String string);

    void onError(Exception e);
}
