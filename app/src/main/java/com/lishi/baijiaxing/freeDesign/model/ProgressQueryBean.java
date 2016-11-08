package com.lishi.baijiaxing.freeDesign.model;

import com.lishi.baijiaxing.base.BaseBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */
public class ProgressQueryBean extends BaseBean {
    public static final int TYPE_SUBSCRIBESU_SUCCESS = 0X001;
    public static final int TYPE_DESIGN_ING = 0X002;
    public static final int TYPE_DESIGN_SUCCESS = 0X003;

    private int type;

    public ProgressQueryBean(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
