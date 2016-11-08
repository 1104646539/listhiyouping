package com.lishi.baijiaxing.integral.model;

import com.lishi.baijiaxing.base.BaseBean;

/**
 * Created by Administrator on 2016/11/2.
 */
public class IntegralBean extends BaseBean {
    private String photoUrl;

    private String name;
    /**
     * 需要的积分
     */
    private int needIntegral;

    public IntegralBean(String photoUrl, String name, int needIntegral) {
        this.photoUrl = photoUrl;
        this.name = name;
        this.needIntegral = needIntegral;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getNeedIntegral() {
        return needIntegral;
    }

    public void setNeedIntegral(int needIntegral) {
        this.needIntegral = needIntegral;
    }
}
