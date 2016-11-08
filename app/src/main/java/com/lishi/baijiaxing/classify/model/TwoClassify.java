package com.lishi.baijiaxing.classify.model;

import java.util.List;

/**
 * 分类
 * 二级分类
 * Created by Administrator on 2016/6/12.
 */
public class TwoClassify {
    private String classname;//类名
    private List<ThreeClassify> classDatas;//三级分类数据

    public TwoClassify(String classname, List<ThreeClassify> classDatas) {
        this.classname = classname;
        this.classDatas = classDatas;
    }
    
    public TwoClassify() {

    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public List<ThreeClassify> getClassDatas() {
        return classDatas;
    }

    public void setClassDatas(List<ThreeClassify> classDatas) {
        this.classDatas = classDatas;
    }
}
