package com.lishi.baijiaxing.myfree.model;

import com.lishi.baijiaxing.base.BaseBean;

/**
 * Created by Administrator on 2016/10/28.
 */
public class MyFreeBean extends BaseBean {
    /**
     * 免费领产品状态
     */
    private int type;
    private String photoUrl;
    private String name;
    private int participationNum;

    public MyFreeBean() {

    }

    public MyFreeBean(int type, String photoUrl, String name, int participationNum) {
        this.type = type;
        this.photoUrl = photoUrl;
        this.name = name;
        this.participationNum = participationNum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public int getParticipationNum() {
        return participationNum;
    }

    public void setParticipationNum(int participationNum) {
        this.participationNum = participationNum;
    }
}
