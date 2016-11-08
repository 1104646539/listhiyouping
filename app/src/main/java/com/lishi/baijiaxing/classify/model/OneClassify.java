package com.lishi.baijiaxing.classify.model;

import com.lishi.baijiaxing.classify.model.TwoClassify;

import java.util.List;

/**
 * Created by Administrator on 2016/6/12.
 */
public class OneClassify {
    private String url;//广告
    private List<TwoClassify> twodatas;//二级分类数据
    
    public OneClassify(String url, List<TwoClassify> onedatas) {
        this.url = url;
        this.twodatas = onedatas;
    }
    
    public OneClassify() {
    
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<TwoClassify> getTwodatas() {
        return twodatas;
    }

    public void setTwodatasdatas(List<TwoClassify> onedatas) {
        this.twodatas = onedatas;
    }
}
