package com.lishi.baijiaxing.bean;

/**
 * Created by Administrator on 2016/6/29.
 */
public class TopTeacherBean {
    private String teacherName;
    private int srcId;

    public TopTeacherBean(String teacherName, int srcId) {
        this.teacherName = teacherName;
        this.srcId = srcId;
    }

    public TopTeacherBean() {
    }   

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getSrcId() {
        return srcId;
    }

    public void setSrcId(int srcId) {
        this.srcId = srcId;
    }
}
