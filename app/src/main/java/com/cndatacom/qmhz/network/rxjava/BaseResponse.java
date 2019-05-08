package com.cndatacom.qmhz.network.rxjava;


import java.io.Serializable;

/**
 * 接口数据类型
 */
public class BaseResponse<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public boolean isSuccess() {
        return code == 0;
    }

    public boolean isSuccess(int successCode) {
        return code == successCode;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
