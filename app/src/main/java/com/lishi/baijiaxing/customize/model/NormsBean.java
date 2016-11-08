package com.lishi.baijiaxing.customize.model;

import com.lishi.baijiaxing.base.BaseBean;

import java.util.List;

/**
 * 产品详情规格分类
 * 颜色
 * 红 蓝 绿
 * Created by Administrator on 2016/10/27.
 */
public class NormsBean extends BaseBean {
    /**
     * 产品详情分类名
     */
    private String classifyName;
    /**
     * 产品详情分类
     */
    private List<String> classifys;

    /**
     * 选中的规格默认为第一个
     */
    private int checkableIndex;



    public NormsBean(String classifyName, List<String> classifys) {
        this.classifyName = classifyName;
        this.classifys = classifys;
        this.checkableIndex = 0;
    }

    public int getCheckableIndex() {
        return checkableIndex;
    }

    public void setCheckableIndex(int checkableIndex) {
        this.checkableIndex = checkableIndex;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public List<String> getClassifys() {
        return classifys;
    }

    public void setClassifys(List<String> classifys) {
        this.classifys = classifys;
    }
}
