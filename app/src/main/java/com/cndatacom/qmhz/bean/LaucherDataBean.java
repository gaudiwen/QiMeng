package com.cndatacom.qmhz.bean;

import java.util.List;

/**
 * 描述: 描述一下类的作用
 * 邮箱：275634247@qq.com
 * Created by GaudiWen on 2019/7/23 17:01.
 */
public class LaucherDataBean {

    /**
     * epgBgImg : /img/testBg2.b28e2089.jpg
     * name : rect
     * index : 0
     * show : true
     * bgColor : rgb(245, 197, 84)
     * bgImg :
     * left : 21px
     * top : 30px
     * height : 200px
     * width : 300px
     * zIndex : 0
     * linkName :
     * linkType :
     * videoSrc : https://www.runoob.com/try/demo_source/movie.mp4
     * title : 公告标题
     * rows : 5
     * color : #fff
     * fontSize : 14px
     * value : 我是文本
     * interval : 3000
     * autoplay : true
     * images : [{"imgSrc":"http://192.168.137.225/group1/M00/00/03/wKiJ4V0ccLyAUO_xAAb79jXSgCg498.jpg","linkName":"","linkType":""},{"imgSrc":"http://192.168.137.225/group1/M00/00/03/wKiJ4V0ccLyALrO6AAg6I_dG3is930.jpg","linkName":"","linkType":""},{"imgSrc":"http://192.168.137.225/group1/M00/00/03/wKiJ4V0ccLyACorQAAgek8sjp-A076.jpg","linkName":"","linkType":""}]
     */

    private String epgBgImg;
    private String name;
    private int index;
    private boolean show;
    private String bgColor;
    private String bgImg;
    private String left;
    private String top;
    private String height;
    private String width;
    private int zIndex;
    private String linkName;
    private String linkType;
    private String videoSrc;
    private String title;
    private int rows;
    private String color;
    private String fontSize;
    private String value;
    private int interval;
    private boolean autoplay;
    private List<ImagesBean> images;

    public String getEpgBgImg() {
        return epgBgImg;
    }

    public void setEpgBgImg(String epgBgImg) {
        this.epgBgImg = epgBgImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getBgImg() {
        return bgImg;
    }

    public void setBgImg(String bgImg) {
        this.bgImg = bgImg;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public int getZIndex() {
        return zIndex;
    }

    public void setZIndex(int zIndex) {
        this.zIndex = zIndex;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getVideoSrc() {
        return videoSrc;
    }

    public void setVideoSrc(String videoSrc) {
        this.videoSrc = videoSrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public boolean isAutoplay() {
        return autoplay;
    }

    public void setAutoplay(boolean autoplay) {
        this.autoplay = autoplay;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public static class ImagesBean {
        /**
         * imgSrc : http://192.168.137.225/group1/M00/00/03/wKiJ4V0ccLyAUO_xAAb79jXSgCg498.jpg
         * linkName :
         * linkType :
         */

        private String imgSrc;
        private String linkName;
        private String linkType;

        public String getImgSrc() {
            return imgSrc;
        }

        public void setImgSrc(String imgSrc) {
            this.imgSrc = imgSrc;
        }

        public String getLinkName() {
            return linkName;
        }

        public void setLinkName(String linkName) {
            this.linkName = linkName;
        }

        public String getLinkType() {
            return linkType;
        }

        public void setLinkType(String linkType) {
            this.linkType = linkType;
        }
    }
}
