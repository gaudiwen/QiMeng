package com.cndatacom.qmhz.bean;

/**
 * 描述: Android Tv桌面参数配置文件
 * 邮箱：275634247@qq.com
 * Created by GaudiWen on 2019/5/16 9:34.
 */
public class LaucherConfigBean {

    public LaucherConfigBean() {
    }

    public LaucherConfigBean(int bgResId) {
        this.bgResId = bgResId;
    }

    private int bgResId;

    public int getBgResId() {
        return bgResId;
    }

    public void setBgResId(int bgResId) {
        this.bgResId = bgResId;
    }

}
