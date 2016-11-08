package com.lishi.baijiaxing.free.model;

import android.widget.TextView;

import com.lishi.baijiaxing.base.BaseBean;

/**
 * 中奖名单
 * Created by Administrator on 2016/10/21.
 */
public class FreeWinningBean extends BaseBean {
    private String name;
    private String address;
    private String head;

    public FreeWinningBean(String name, String address, String head) {
        this.name = name;
        this.address = address;
        this.head = head;
    }

    public FreeWinningBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}
