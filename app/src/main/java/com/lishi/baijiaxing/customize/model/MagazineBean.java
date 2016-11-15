package com.lishi.baijiaxing.customize.model;

import com.lishi.baijiaxing.base.BaseModel;

/**
 * Created by Administrator on 2016/11/14.
 */
public class MagazineBean extends BaseModel {
    private String photoUrl;
    private String name;
    private String brief;

    public MagazineBean(String photoUrl, String name, String brief) {
        this.photoUrl = photoUrl;
        this.name = name;
        this.brief = brief;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
}
