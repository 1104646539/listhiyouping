package com.lishi.baijiaxing.free.model;

import com.lishi.baijiaxing.base.BaseBean;

import java.util.jar.Attributes;

/**
 * 回复评论
 * Created by Administrator on 2016/10/20.
 */
public class ReplyBean extends BaseBean {
    /**
     * 回复人昵称
     */
    private String name;
    /**
     * 恢复人帐号
     */
    private String id;
    /**
     * 回复信息
     */
    private String info;

    public ReplyBean() {
    }

    public ReplyBean(String name, String id, String info) {
        this.name = name;
        this.id = id;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
