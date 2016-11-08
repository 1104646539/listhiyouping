package com.lishi.baijiaxing.classify.model;

/**
 * 分类
 * 三级分类
 * Created by Administrator on 2016/6/12.
 */
public class ThreeClassify {
    private String name;//类名
    private String url;//图片url

    public ThreeClassify(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public ThreeClassify() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
