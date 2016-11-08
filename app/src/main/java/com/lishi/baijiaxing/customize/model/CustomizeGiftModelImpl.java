package com.lishi.baijiaxing.customize.model;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseBean;
import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.customize.CustomizeCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/27.
 */
public class CustomizeGiftModelImpl extends BaseModel implements CustomizeModel {
    @Override
    public void loadData(CustomizeCallback callback) {
        List<Integer> srcs = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            srcs.add(R.drawable.customize_gift_ivs);
        }
        CustomizeGiftBean mCustomizeGiftBean = new CustomizeGiftBean("", "", srcs);
        callback.onLoadSuccess(mCustomizeGiftBean);
    }
}