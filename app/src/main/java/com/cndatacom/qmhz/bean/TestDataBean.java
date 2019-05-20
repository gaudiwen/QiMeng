package com.cndatacom.qmhz.bean;

public class TestDataBean {

    /**
     * 标题
     */
    private String title;
    /**
     * 图片地址
     */
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TestDataBean(String title, String url) {
        this.title = title;
        this.url = url;
    }
}