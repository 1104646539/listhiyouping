package com.lishi.baijiaxing.weeklyChoiceness.model;

import com.lishi.baijiaxing.base.BaseBean;

/**
 * Created by Administrator on 2016/11/2.
 */
public class WeeklyBean extends BaseBean {
    private String photoUrl;

    private String name;
    private int price;

    public WeeklyBean(String photoUrl, String name, int price) {
        this.photoUrl = photoUrl;
        this.name = name;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPhotoUrl(String photoUrl) {
        
        this.photoUrl = photoUrl;
    }

}
