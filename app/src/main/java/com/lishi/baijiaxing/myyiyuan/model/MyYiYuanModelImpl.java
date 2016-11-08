package com.lishi.baijiaxing.myyiyuan.model;

import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.myyiyuan.MyYiYuanCallback;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/28.
 */
public class MyYiYuanModelImpl extends BaseModel implements MyYiYuanModel {
    private ArrayList<MyYiYuanBean> mYiYuanBeans;

    @Override
    public void loadData(MyYiYuanCallback callback) {
        mYiYuanBeans = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            MyYiYuanBean myYiYuan = new MyYiYuanBean(200, "", "【利世优品】万仟堂陶瓷同心杯 带盖过滤办公茶杯水杯包邮", 250, 30, 1);
            mYiYuanBeans.add(myYiYuan);
        }
        callback.onLoadSuccess(mYiYuanBeans);
    }
}
