package com.lishi.baijiaxing.free.model;

import com.lishi.baijiaxing.base.BaseBean;

import java.util.ArrayList;

/**
 * 免费领——评论
 * Created by Administrator on 2016/10/20.
 */
public class FreeCommentBean extends BaseBean {
    /**
     * 评论名
     */
    private String name;
    /**
     * 头像
     */
    private String headPortrait;
    /**
     * 评论时间
     */
    private String time;
    /**
     * 评论内容
     */
    private String info;
    /**
     * 评论回复
     */
    private ArrayList<ReplyBean> mReplyBeans;
    /**
     * 赞
     */
    private int zans;

    public FreeCommentBean(String name, String headPortrait, String time, String info, ArrayList<ReplyBean> replyBeans, int zans) {
        this.name = name;
        this.headPortrait = headPortrait;
        this.time = time;
        this.info = info;
        mReplyBeans = replyBeans;
        this.zans = zans;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public ArrayList<ReplyBean> getReplyBeans() {
        return mReplyBeans;
    }

    public void setReplyBeans(ArrayList<ReplyBean> replyBeans) {
        mReplyBeans = replyBeans;
    }

    public int getZans() {
        return zans;
    }

    public void setZans(int zans) {
        this.zans = zans;
    }
}
