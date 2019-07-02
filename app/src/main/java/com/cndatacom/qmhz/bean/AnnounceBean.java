package com.cndatacom.qmhz.bean;

/**
 * 描述: 描述一下类的作用
 * 邮箱：275634247@qq.com
 * Created by GaudiWen on 2019/6/26 16:36.
 */
public class AnnounceBean {

    /**
     * 标题
     */
    private String title;
    /**
     * 图片地址
     */
    private String url;

    public AnnounceBean(String title, String url) {
        this.title = title;
        this.url = url;
    }
    public AnnounceBean( String url) {
        this.url = url;
    }

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
}
