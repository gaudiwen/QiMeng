package com.cndatacom.qmhz.bean;


public class GoodsTypeBean {
    /**
     * sold : true
     * name : 1234
     * id : 7
     */

    private boolean sold;
    private String name;
    private int id;

    public GoodsTypeBean(boolean sold, String name, int id, boolean isSelector) {
        this.sold = sold;
        this.name = name;
        this.id = id;
        this.isSelector = isSelector;
    }

    public boolean isSelector() {
        return isSelector;
    }

    public void setSelector(boolean selector) {
        isSelector = selector;
    }

    private boolean isSelector;

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
