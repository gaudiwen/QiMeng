package com.cndatacom.qmhz.network.rxjava;


import java.util.List;

/**
 * 接口数据类型
 */
public class BaseListResponse<T> {
    private int code;
    private String msg;
    private List<T> data;

    public int getCode() {
        return code;
    }

    public boolean isSuccess(){
        return code==1001;
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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
