package com.lishi.baijiaxing.bean;

import java.util.List;

/**
 * 评论实体类
 * Created by Administrator on 2016/7/12.
 */
public class CommentBean {
    private List<String> imgs;//图片
    private int user_photo;//头像
    private String user_name;//评论用户名
    private int user_leve;//用户等级
    private String comment_date;//评论时间
    private int comment_star;//评论星级
    private String comment_text;//评论正文
    private String shopping_date;//购买日期

    public CommentBean(List<String> imgs, int user_photo, String user_name, int user_leve, String comment_date, int comment_star, String comment_text, String shopping_date) {
        this.imgs = imgs;
        this.user_photo = user_photo;
        this.user_name = user_name;
        this.user_leve = user_leve;
        this.comment_date = comment_date;
        this.comment_star = comment_star;
        this.comment_text = comment_text;
        this.shopping_date = shopping_date;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public int getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(int user_photo) {
        this.user_photo = user_photo;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_leve() {
        return user_leve;
    }

    public void setUser_leve(int user_leve) {
        this.user_leve = user_leve;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }

    public int getComment_star() {
        return comment_star;
    }

    public void setComment_star(int comment_star) {
        this.comment_star = comment_star;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public String getShopping_date() {
        return shopping_date;
    }

    public void setShopping_date(String shopping_date) {
        this.shopping_date = shopping_date;
    }
}
