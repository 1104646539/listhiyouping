package com.lishi.baijiaxing.bean;

/**
 * Created by Administrator on 2016/6/30.
 */
public class NotInvoluntaryBean {
    private String name;
    private String info;
    private int srcId;

    public int getSrcId() {
        return srcId;
    }

    public void setSrcId(int srcId) {
        this.srcId = srcId;
    }

    public NotInvoluntaryBean(String name, String info, int srcId) {
        this.name = name;
        this.info = info;
        this.srcId = srcId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
