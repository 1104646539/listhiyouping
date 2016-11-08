package com.lishi.baijiaxing.userManager.model;

import com.lishi.baijiaxing.base.BaseBean;

/**
 * Created by Administrator on 2016/11/2.
 */
public class UserListBean extends BaseBean {
    /**
     * 会员名
     */
    private String name;
    /**
     * 头像
     */
    private String headPhoto;
    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private String sex;

    public UserListBean(String name, String headPhoto, String nickname, String sex) {
        this.name = name;
        this.headPhoto = headPhoto;
        this.nickname = nickname;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
