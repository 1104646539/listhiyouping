package com.lishi.baijiaxing.refund.model;

/**
 * Created by Administrator on 2016/11/18.
 */

public class RefundProgressBean {
    public static final int TYPE_DISPOSE_ING = 0X001;
    public static final int TYPE_DISPOSE_SUCCESS = 0X002;

    private int type;

    public RefundProgressBean(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
