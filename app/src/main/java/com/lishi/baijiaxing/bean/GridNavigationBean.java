package com.lishi.baijiaxing.bean;

/**首页8大导航
 * Created by Administrator on 2016/6/2.
 */
public class GridNavigationBean {
    private int srcId;
    private String text;

    public GridNavigationBean(int srcId, String text) {
        this.srcId = srcId;
        this.text = text;
    }

    public int getSrcId() {
        return srcId;
    }

    public void setSrcId(int srcId) {
        this.srcId = srcId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
