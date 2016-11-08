package com.lishi.baijiaxing.yiyuan.model;

import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.yiyuan.YiYuanHotCallback;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/24.
 */
public class YiYuanHotModelImpl extends BaseModel implements YiYuanHotModel {
    private ArrayList<YiYuanHotBean> mYiYuanHotBeans;

    @Override
    public void loadData(YiYuanHotCallback callback) {
        mYiYuanHotBeans = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            YiYuanHotBean yyhb = new YiYuanHotBean("",
                    "【利世优品】万仟堂陶瓷同心 杯带盖过滤办公茶杯水杯包邮【利世优品】万仟堂陶瓷同心 杯带盖过滤办公茶杯水杯包邮", 300, 20);
            mYiYuanHotBeans.add(yyhb);
        }
        callback.onLoadSuccess(mYiYuanHotBeans);
    }
}
