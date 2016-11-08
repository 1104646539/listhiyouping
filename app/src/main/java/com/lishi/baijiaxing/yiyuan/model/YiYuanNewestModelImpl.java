package com.lishi.baijiaxing.yiyuan.model;

import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.yiyuan.YiYuanHotCallback;
import com.lishi.baijiaxing.yiyuan.YiYuanNewestCallback;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/24.
 */
public class YiYuanNewestModelImpl extends BaseModel implements YiYuanNewestModel {
    private ArrayList<YiYuanNewestBean> mYiYuanNewestBeans;


    @Override
    public void loadData(YiYuanNewestCallback callback) {
        mYiYuanNewestBeans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            YiYuanNewestBean yynb = new YiYuanNewestBean("", "【利世优品】万仟堂陶瓷同心 杯带盖过滤办公茶杯水杯包邮【利世优品】万仟堂陶瓷同心 " +
                    "杯带盖过滤办公茶杯水杯包邮", 30, "10012212", "mr wang");
            mYiYuanNewestBeans.add(yynb);
        }
        callback.onLoadSuccess(mYiYuanNewestBeans);
    }
}
